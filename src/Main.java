
public class Main {
    public static void main(String[] args) {
        new Main().play();
    }

    private int POLE_SIZE = 3;
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
        String humanName = "";
        p1 = new HumanPlayer(humanName);
        p2 = new DumpAiPlayer(POLE_SIZE);
        gamePole = new Pole(POLE_SIZE);
    }

    private void makeStep(Player player, Pole gamePole) {
        boolean pMakeStep = false;
        while (!pMakeStep) {
            Point2d step = player.getNextStep();
            pMakeStep = gamePole.canMakeStep(step);
            if (pMakeStep) {
                gamePole.step(step,player.getChar());
                gamePole.printPole();
                if (gamePole.isAnobodyWin()) {
                    System.out.println("winner is: " + player.getName());
                    System.exit(0);
                }
            }

        }
    }
}