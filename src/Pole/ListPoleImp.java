package Pole;

import data.Point2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ListPoleImp extends AbstractPole {

    List<List<Character>> pole;

    public ListPoleImp(int pole_size, int lineSize) {
        super(pole_size, lineSize);

        pole = new ArrayList<List<Character>>(pole_size);

        for (int i = 0; i < pole_size; i++) {
            List<Character> iterator = new ArrayList<Character>(pole_size);
            Collections.fill(iterator, '*');
            pole.add(iterator);
        }
    }

    //21.09 - 21.24 ;

    @Override
    public boolean hasCleanCell() {
       //todo
        return false;
    }

    @Override
    public boolean canMakeStep(Point2d step) {
        char cell = getCell(step);
        return cell == '*';
    }

    @Override
    public void step(Point2d step, char aChar) {
        pole.get(step.getX()).set(step.getY(), aChar);
    }

    @Override
    public void printPole() {
        for (List<Character> iter : pole) {
            System.out.print(iter);
        }
    }

    @Override
    protected char getCell(Point2d coordinate) {
        List<Character> iterator = pole.get(coordinate.getX());
        char cell = iterator.get(coordinate.getY());
        return cell;
    }
}
