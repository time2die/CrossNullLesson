import java.util.Random;

public class DumpAiPlayer extends AiPlayer {
    private Random rnd = new Random() ;

    private int POLE_SIZE = 0 ;
    private char aChar = 'C';

    public DumpAiPlayer(int pole_size) {
        this.POLE_SIZE = pole_size ;
    }

    public int getRndInt(){
        return rnd.nextInt(POLE_SIZE) ;
    }

    public void setChar(char aChar){
        this.aChar = aChar;
    }

    @Override
    public int getNextX() {
        return getRndInt() ;
    }

    @Override
    public int getNextY() {
        return getRndInt() ;
    }

    @Override
    public Point2d getNextStep() {
        return new Point2d(getRndInt(),getRndInt());
    }

    @Override
    public char getChar() {
        return aChar;
    }
}
