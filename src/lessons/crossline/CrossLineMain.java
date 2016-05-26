package lessons.crossline;

import lessons.crossline.Pole.ArrayCharPole;
import lessons.crossline.Pole.Pole;
import lessons.crossline.data.Point2d;

import java.util.Scanner;

/**
 * Created by time2die on 24.05.16.
 */
public class CrossLineMain {


    Player p1;
    Player p2;
    Pole gameArrayCharPole;

    public void play() {
        init();
        boolean gameEnd = false;

        while (!gameEnd) {
            makeStep(p1, gameArrayCharPole);
            if (!gameArrayCharPole.hasCleanCell()) {
                System.out.println("nobody win");
                System.exit(0);
            }
            makeStep(p2, gameArrayCharPole);
            if (!gameArrayCharPole.hasCleanCell()) {
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

        gameArrayCharPole = new ArrayCharPole(POLE_SIZE, lineSize);
    }

    private void makeStep(Player player, Pole gameArrayCharPole) {
        boolean pMakeStep = false;
        while (!pMakeStep) {
            Point2d step = player.getNextStep();
            pMakeStep = gameArrayCharPole.canMakeStep(step);
            if (pMakeStep) {
                gameArrayCharPole.step(step, player.getChar());
                gameArrayCharPole.printPole();
                if (gameArrayCharPole.isAnobodyWin(player.getChar())) {
                    System.out.println("winner is: " + player.getName() + "_" + player.getChar());
                    System.exit(0);
                }
            }

        }
    }
}
