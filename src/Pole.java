import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.omg.PortableServer.POA;

import java.util.Scanner;

public class Pole   {
    private boolean anobodyWin;
    private char[][] gamepole; // массив
    private int POLE_SIZE = 0;




    public Pole(int pole_size) {
        this.POLE_SIZE = pole_size;


        System.out.println("Игра началась!");
        System.out.println("====================");

    }

    //наличие свободных клеток
    public boolean hasCleanCell() {
        for (int i = 0; i < POLE_SIZE; i++) {
            for (int j = 0; j < POLE_SIZE; j++) {

                if (gamepole[i][j] == '*') {
                    return true;
                }
            }
        }
        return false;
    }



    public void step(Point2d step, char aChar) {   // Ход  - проверка на корректность кооднират и установка фигуры
        int x = step.getX();
        int y = step.getY();

        if (step.getX() < 0 || step.getX() > POLE_SIZE || step.getY() < 0 || step.getY() > POLE_SIZE)
            throw new ArrayIndexOutOfBoundsException("Не удалось установить координаты");

        else {
                gamepole[x][y] = aChar;

        }
    }


/* public  char checkWin( ) { // проверка кто выиграл 1
    // проверка строк по  горизонтали
        gamepole

    char winner = ' ';//
    if (winner == ' ')
        for (int i = 0; i < gamepole.length; i++) {
        if (gamepole[i][POLE_SIZE] == gamepole[i][1] && gamepole[i][1] == map[i][2]
                && map[i][0] != '#') {
            winner = map[i][0];
            break;
        }
    }

    // проверка по верикали
    if (winner == ' ') {
        for (int i = 0; i < 3; i++) {
            if (map[0][i] == map[1][i] && map[1][i] == map[2][i]
                    && map[0][i] != '#') {
                winner = map[0][i];
                break;
            }
        }
    }
    // проверка по диагонали 1
    if (winner == ' ') {
        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]
                && map[0][0] != '#') {
            winner = map[0][0];
        }
    }
// проверка по диагонали 2
    if (winner == ' ') {
        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]
                && map[0][2] != '#') {
            winner = map[0][0];
        }
    }

    return winner;
}

*/

    public boolean checkLine(int x, int y , int vx, int vy, int POLE_SIZE, char aChar) {

        for (int i = 0; i < POLE_SIZE; i++) {

            if ( gamepole[x + i * vx][y  + i * vy] != aChar) return false;
        }
        return true;
    }


    public boolean canMakeStep(Point2d step) { // проверка на ввод координат на на возможность походить
        boolean emptyCell;
        int x = step.getX();
        int y = step.getY();
        if (step.getX() < 0 || step.getX() > POLE_SIZE || step.getY() < 0 || step.getY() > POLE_SIZE)
            throw new ArrayIndexOutOfBoundsException("Не удалось установить координаты");
        emptyCell = false;
        if ( gamepole [x][y] != '*') ;
        emptyCell=  false;
        return emptyCell;
    }

    public boolean checkWin( char aChar) {

        for (int i = 0; i < gamepole.length; i++) {
            if (checkLine(0, i, 0, 1, POLE_SIZE, aChar)) return true;  // горизонтальные линии
            if (checkLine(i, 0, 0, 1, POLE_SIZE, aChar)) return true;
        }
        if (checkLine(0, 0, 1, 1, POLE_SIZE, aChar)) return true; // диагонали
        if (checkLine(0, 2, 1, -1, POLE_SIZE, aChar)) return true; //диагонали
        return false;
    }


    public boolean isCellEmpty(Point2d step) {
        if (step.getX() < 0 || step.getX() > POLE_SIZE || step.getY() < 0 || step.getY() > POLE_SIZE)
            return false;
        // проверка на ввод координат вне поля и на отриц значения
        if (gamepole[POLE_SIZE][POLE_SIZE] != '*') return false;
        return true;
    }

    // печатаю поле
    public void printPole() {  // печатаю игровое поле
        System.out.printf("%5s", "X - координаты строки");
        System.out.println();
        for (int i = 0; i < POLE_SIZE; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < POLE_SIZE; j++) {
                System.out.print(gamepole[i][j] + "\t");
            }
            System.out.println();
        }

    }




    public void setFigure(int x, int y, char figure) { // если вдруг не будет координат на вход компьютеру
        if (x < 0 || y < 0) {
            throw new ArrayIndexOutOfBoundsException("Не удалось установить координаты");
        } else {
            gamepole[x][y] = figure;
        }
    }


    // инициализирую поле
    public void createPole() {
        gamepole = new char[POLE_SIZE][POLE_SIZE];
        for (int i = 0; i < POLE_SIZE; i++) {
            for (int j = 0; j < POLE_SIZE; j++) {
                gamepole[i][j] = '*';
            }
        }

    }
}
