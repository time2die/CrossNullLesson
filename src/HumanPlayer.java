import java.util.Scanner;

public class HumanPlayer implements Player {

    private Scanner sc = new Scanner(System.in) ;
    private String playerName ;
    private char aChar = 'H';

    public HumanPlayer(String humanName) {
        this.playerName = humanName ;
    }

    @Override
    public int getNextX() {
        System.out.println("input x");
        return sc.nextInt() ;
    }

    @Override
    public int getNextY() {
        System.out.println("input y");
        return sc.nextInt() ;
    }

    @Override
    public Point2d getNextStep() {
        return new Point2d(getNextX(), getNextY());
    }

    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public void setChar(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public char getChar() {
        return aChar;
    }
}
