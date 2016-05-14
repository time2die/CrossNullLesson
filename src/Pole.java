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

    public boolean hasCleanCell() {
        return false;
    }

    public boolean canMakeStep(Point2d step) {
        return false;
    }

    public void step(Point2d step, char aChar) {

    }

    public boolean isAnobodyWin() {
        return anobodyWin;
    }

    public void printPole() {

    }
}
