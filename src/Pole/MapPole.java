package Pole;

import data.Point2d;

import java.util.HashMap;

public class MapPole extends AbstractPole {
    HashMap<Point2d, Character> poleMap;

    public MapPole(int pole_size, int lineSize) {
        super(pole_size, lineSize);

        for (int i = 0; i < pole_size; i++) {
            for (int j = 0; j < pole_size; j++) {
                Point2d point = new Point2d(i, j);
                poleMap.put(point, '*');
                // 0:0 - * , 0:1 - * , 1:0 - * , 1:1 - *
            }
        }
    }

    @Override
    protected char getCell(Point2d coordinate) {
        return poleMap.get(coordinate);
    }

    @Override
    public boolean hasCleanCell() {
        for (Character iter : poleMap.values()) {
            if (iter == '*') {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMakeStep(Point2d step) {
        return poleMap.get(step) == '*';
    }

    @Override
    public void step(Point2d step, char aChar) {
        poleMap.put(step, aChar);
    }

    @Override
    public void printPole() {
        for (Point2d iter : poleMap.keySet()) {
            System.out.println(iter.getX() + ":" + iter.getY() + ":" + poleMap.get(iter));
        }
    }
}
