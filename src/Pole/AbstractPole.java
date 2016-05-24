package Pole;

import data.Point2d;

/**
 * Created by time2die on 16.05.2016.
 */
public abstract class AbstractPole implements Pole {
    public AbstractPole(int pole_size, int lineSize) {

    }

    @Override
    public boolean isAnobodyWin(char aChar) {
        return false ;
    }

    abstract protected char getCell(Point2d coordinate) ;

}
