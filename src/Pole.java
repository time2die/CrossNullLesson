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

    public boolean isAnobodyWin() {
        return anobodyWin;
    }

    public void printPole() {

    }
}
