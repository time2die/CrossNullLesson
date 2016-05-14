public class Pole {

    private char[][] pole;
    private boolean anobodyWin;
    private int POLE_SIZE = 0 ;
    private int lineSize = 0;

    public Pole(int pole_size) {
        this.POLE_SIZE = pole_size ;
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
