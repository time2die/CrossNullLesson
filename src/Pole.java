import java.util.Arrays;

public class Pole {

    private char[][] pole;
    private boolean anobodyWin;
    private int POLE_SIZE = 0 ;
    private int lineSize = 0;

    public Pole(int pole_size, int lineSize) {
        this.POLE_SIZE = pole_size ;
        this.lineSize = lineSize;
        createPole();
    }

    // создание игрового поля
    public void createPole() {
        pole = new char[POLE_SIZE][POLE_SIZE];
        for (int i = 0; i < POLE_SIZE; i++) {
            for (int j = 0; j < POLE_SIZE; j++) {
                pole[i][j] = '*';
            }
        }
    }

    // есть ли свободные клетки
    public boolean hasCleanCell() {
        for(int i = 0; i < POLE_SIZE; i++){
            for(int j = 0; j < POLE_SIZE; j++){
                if(pole[i][j] == '*'){
                    return true;
                }
            }
        }
        return false;
    }

    // проверка на корректность координат
    public boolean canMakeStep(Point2d step) {
        boolean isEmpty = true;
        int str = step.getY();
        int col = step.getX();

        // Проверка на ...
        if(str<0 || col<0){// ... отрицательные индексы
            //System.out.println("Координаты не могут быть меньше или равны 0! ");
            isEmpty = false;
        }else if(str>pole[0].length-1 || col>pole.length-1){// ... выход за пределы поля
            //System.out.println("Координаты не могут быть больше размера игрового поля! ");
            isEmpty = false;
        }else if(pole[str][col] != '*'){// ... свободная ячейка
            //System.out.println("Ячейка занята! ");
            isEmpty = false;
        }

        return isEmpty;
    }

    public void step(Point2d step, char aChar) {
        int str = step.getY();
        int col = step.getX();

        pole[str][col] = aChar;
    }

    // горизонтальный паттерн
    public boolean lineHorizontal(char[][] arr, int sizePat, char aChar){

        boolean isLine = false;
        int sizeLine;

        for(int i=0; i<arr.length; i++){
            sizeLine = 0;// при переходе на другую строку - обнулить
            for(int j=0; j<arr[0].length; j++){// в пределах одной строки
                if(arr[i][j] == aChar){// если "наша" ячейка
                    sizeLine++;// увеличить счетчик
                }else sizeLine = 0;// если не "наша" ячейка - обнулить счетчик
                if(sizeLine == sizePat) break;// если достигли нужного размера - конец цикла
            }
            if(sizeLine == sizePat) {// если достигли нужного размера
                isLine = true;// возврат из функции
                break;// конец цикла
            }
        }

        return isLine;
    }

    // диагональный паттерн "прямой"
    public boolean lineDiagonal_1(int sizePat, char aChar){

        boolean isLine = false;

        // на скольких линиях игрового поля можно встретить диагональный паттерн
        int magicNum = pole.length - sizePat; // важная переменная в расчетах :-)
        // "массив прогресса": массив для записи прогресса каждой линии "диагонального числа" см. ниже
        int[][] pD1arr1 = new int[3][magicNum * 2 + 1];
        int numLine=-1;

        // заполняем (даем имя) первую линию массива прогресса "диагональным числом" см. ниже
        for(int i=0; i<pD1arr1[0].length; i++){
            pD1arr1[0][i] = -1*(magicNum) + i;
        }

        // Диагональный паттерн "прямой"
        for(int i=0; i<pole.length; i++){
            for(int j=0; j<pole[0].length; j++){
                // прямое "диагональное число" ячейки: повторяется только по диагоналям
                // 0 -1 -2
                // 1  0	-1
                // 2  1	 0
                int nDiag = i - j;
                // если "диагональное число" ячейки игрового поля находится в искомом диапазоне
                if(nDiag > -1*(magicNum+1) && nDiag < magicNum+1) {
                    // ищем одноименную ячейку в "массиве прогресса"
                    numLine = Arrays.binarySearch(pD1arr1[0], nDiag);
                    if (pole[i][j] == aChar) {// если ячейка игрового поля "наша"
                        // по найденному адресу "массива прогресса" увеличиваем значение на 1
                        pD1arr1[1][numLine] = pD1arr1[1][numLine] + 1;
                    }else pD1arr1[1][numLine] = 0;// если ячейка "не наша"
                    // если выигрыш - прерывание внутреннего цикла
                    if(pD1arr1[1][numLine] == sizePat) break;
                }
            }
            // если выигрыш - прерывание внешнего цикла
            if(pD1arr1[1][numLine] == sizePat) {
                isLine = true;
                break;
            }
        }

        return isLine;
    }

    // диагональный паттерн "обратный"
    public boolean lineDiagonalAnother(int sizePat, char aChar){

        boolean isLine = false;

        // на скольких линиях игрового поля можно встретить диагональный паттерн
        int magicNum = pole.length - sizePat; // важная переменная в расчетах :-)
        // "массив прогресса": массив для записи прогресса каждой линии "диагонального числа" см. ниже
        int[][] pD1arr1 = new int[2][magicNum * 2 + 1];
        int numLine=-1;

        // заполняем (даем имя) первую линию массива прогресса "диагональным числом" см. ниже
        for(int i=0; i<pD1arr1[0].length; i++){
            pD1arr1[0][i] = -1*(magicNum) + i;
        }

        // Диагональный паттерн "обратный"
        for(int i=0; i<pole.length; i++){
            for(int j=0; j<pole[0].length; j++){
                // обратное "диагональное число" ячейки: повторяется только по диагоналям
                //2  1  0
                //1	 0 -1
                //0	-1 -2
                int nDiag = -1*(i-(pole[0].length-1)) - j;
                // если "диагональное число" ячейки игрового поля находится в искомом диапазоне
                if(nDiag > -1*(magicNum+1) && nDiag < magicNum+1) {
                    // ищем одноименную ячейку в "массиве прогресса"
                    numLine = Arrays.binarySearch(pD1arr1[0], nDiag);
                    if (pole[i][j] == aChar) {// если ячейка "наша"
                        // по найденному адресу "массива прогресса" увеличиваем значение на 1
                        pD1arr1[1][numLine] = pD1arr1[1][numLine] + 1;
                    }else pD1arr1[1][numLine] = 0;// если ячейка "не наша"
                    // если выигрыш - прерывание внутреннего цикла
                    if(pD1arr1[1][numLine] == sizePat) break;
                }
            }
            // если выигрыш - прерывание внешнего цикла
            if(pD1arr1[1][numLine] == sizePat) {
                isLine = true;
                break;
            }
        }

        return isLine;
    }

    // транспонирование
    public char[][] transArray(){
        char[][] outArr = new char[pole[0].length][pole.length];

        for(int i = 0; i< pole[0].length; i++ ){
            for(int j = 0; j< pole.length; j++ ){
                outArr[i][j] =  pole[j][i];
            }
        }
        return outArr;
    }

    // победитель
    public boolean isAnobodyWin(char aChar) {

        int pattern=0;

        // Диагональный паттерн "прямой"
        if(lineDiagonal_1(lineSize, aChar)){pattern = 1;}

        // Диагональный паттерн "обратный"
        if(lineDiagonalAnother(lineSize, aChar)){pattern = 2;}

        // Горизонтальный паттерн
        if(lineHorizontal(pole, lineSize, aChar)){pattern = 3;}

        // Вертикальный паттерн
        char[][] boardT =  transArray();
        if(lineHorizontal(boardT, lineSize, aChar)){pattern = 4;}

        if(pattern > 0) anobodyWin = true;

        return anobodyWin;
    }

    // печать игрового поля в консоли
    public void printPole() {

        int strLength = pole.length;
        int colLength = pole[0].length;

        for(int i = 0; i < strLength + 1; i++){
            System.out.println();
            for(int j = 0; j < colLength + 1; j++){

                if(i == 0 && j == 0){// не заполняемый угол
                    System.out.printf("%5s","");
                }else if(i == 0){// нумерация столбцов
                    System.out.printf("%5s", j);
                }else if(j == 0){// нумерация строк
                    System.out.printf("%5s", i);
                }else {
                    System.out.printf("%5s", pole[i - 1][j - 1]);
                }
            }
            System.out.println();
        }
        System.out.print("\n\n");
    }
}
