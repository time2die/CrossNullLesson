/**
 * Created by home on 17.05.2016.
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
