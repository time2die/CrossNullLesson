package lessons.crossline;

/**
 * Created by time2die on 16.05.2016.
 */
public abstract class AbstractPlayer implements Player {
    protected char aChar = 'H';

    @Override
    public void setChar(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public char getChar() {
        return aChar;
    }

}
