import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().play();
    }

    private int POLE_SIZE = 5;
    private int idi;


    Player p1;
    Player p2;
    Pole gm = new Pole(POLE_SIZE);
    Game gameType;


    private void play() {
        TypeofGame(idi);
        init();

        while (true) {
            makeStep(p1, gm);
            if (!gm.hasCleanCell()) {
                System.out.println("nobody win");
                System.exit(0);
            }
            makeStep(p2, gm);
            if (!gm.hasCleanCell()) {
                System.out.println("nobody win");
                System.exit(0);
            }
        }
    }

    private void init() {
        // read POLE SIZE FROM IN
        // decide who will play
        Scanner sc = new Scanner(System.in);
        if (gameType.getId() == 0) {
            String humanName = "";
            System.out.println("Введите имя Игрока1");
            String humanName1 = sc.nextLine();
            p1 = new HumanPlayer(humanName1);
            p1.setChar('X') ;
            System.out.println("==============================");
            System.out.println("Введите имя Игрока2");
            String humanName2 = sc.nextLine();
            p2 = new HumanPlayer(humanName2);
            p2.setChar('O');


        }
        if (gameType.getId() == 1) {
            String humanName = "";
            System.out.println("Введите имя Игрока1");
            String humanName1 = sc.nextLine();
            p1 = new HumanPlayer(humanName1);
            System.out.println("==============================");
            p2 = new DumpAiPlayer(POLE_SIZE);
        }

        if (gameType.getId() == 2) {
            p1 = new DumpAiPlayer(POLE_SIZE);
            System.out.println("==============================");
            p2 = new DumpAiPlayer(POLE_SIZE);

        }
        gm.createPole();
        gm.printPole();
    }


    public void TypeofGame(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите тип игры :  " +
                "0 - Человек против человека, 1 - Человек против Компьютера, 2- Компьютер против Компьютера ");
        int idi = sc.nextInt();
        gameType = Game.values()[idi];
        System.out.println("Выбран тип игры: " + Game.values()[idi]);
    }


    private void makeStep(Player player, Pole gm) {
        boolean pMakeStep = true;
        Point2d step = player.getNextStep();
        pMakeStep = gm.canMakeStep(step);

        if (!pMakeStep) {

            gm.step(step, player.getChar());
            gm.printPole();

            if (gm.checkWin('H')) {
                System.out.println("winner is: " + player.getName());
                if (gm.checkWin('C')) {
                    System.out.println("winner is: " + player.getName());
                }
            }
        }
    }
}



