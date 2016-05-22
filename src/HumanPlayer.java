import java.util.Scanner;

public class HumanPlayer implements Player {
    private Scanner sc = new Scanner(System.in);

    private String playerName;
    private String humanName ="";

    public HumanPlayer (String humanName){
        this.playerName = humanName;
    }

    @Override
    public int getNextX() {
        System.out.println("Введите координаты x");
        return sc.nextInt();
    }

    @Override
    public int getNextY() {
        System.out.println("Введите координаты y");
        return sc.nextInt();
    }

    @Override
    public Point2d getNextStep() {
        return new Point2d(getNextX(),getNextY());
    }

    @Override
    public String getName() {
        System.out.println( "Введите свое имя");
        Scanner sc = new Scanner(System.in);
        String humanName = sc.next();
        return  humanName ;
    }

    @Override
    public char getChar() {
        return 'H';
    }


    @Override
    public void setChar(char aChar) {

    }
}