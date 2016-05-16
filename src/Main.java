import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().play();
    }


    Player p1;
    Player p2;
    Pole gamePole;

    private void play() {
        init();
        boolean gameEnd = false;

        while (!gameEnd) {
            makeStep(p1, gamePole);
            if (!gamePole.hasCleanCell()) {
                System.out.println("nobody win");
                System.exit(0);
            }
            makeStep(p2, gamePole);
            if (!gamePole.hasCleanCell()) {
                System.out.println("nobody win");
                System.exit(0);
            }
        }
    }

    private void init() {
        // read POLE SIZE FROM IN
        // decide who will play
        Scanner sc = new Scanner(System.in);

        // поле
        System.out.println("input pole size");
        int POLE_SIZE = sc.nextInt();

        // линия для победы
        System.out.println("input line size");
        int lineSize = sc.nextInt();

        // вариант игры
        System.out.println("select game: 1 - human vs human, " +
                "2 - human vs computer, " +
                "3 - computer vs computer");
        int select = sc.nextInt();

        sc.nextLine();

        switch (select) {
            case 1:// human vs human
                System.out.println("input name one");
                String humanName1 = sc.nextLine();
                p1 = new HumanPlayer(humanName1);
                p1.setChar('X');
                System.out.println("input name two");
                String humanName2 = sc.nextLine();
                p2 = new HumanPlayer(humanName2);
                p2.setChar('O');
                break;
            case 2:// human vs computer
                System.out.println("input name");
                String humanName = sc.nextLine();
                p1 = new HumanPlayer(humanName);
                p2 = new DumpAiPlayer(POLE_SIZE);
                break;
            case 3:// computer vs computer
                p1 = new DumpAiPlayer(POLE_SIZE);
                p2 = new DumpAiPlayer(POLE_SIZE);
                p1.setChar('X');
                p2.setChar('O');
                break;
        }

        gamePole = new Pole(POLE_SIZE, lineSize);
    }

    private void makeStep(Player player, Pole gamePole) {
        boolean pMakeStep = false;
        while (!pMakeStep) {
            Point2d step = player.getNextStep();
            pMakeStep = gamePole.canMakeStep(step);
            if (pMakeStep) {
                gamePole.step(step, player.getChar());
                gamePole.printPole();
                if (gamePole.isAnobodyWin(player.getChar())) {
                    System.out.println("winner is: " + player.getName() + "_" + player.getChar());
                    System.exit(0);
                }
            }

        }
    }
}