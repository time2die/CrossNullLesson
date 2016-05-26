package lessons.crossline;

import lessons.crossline.data.Point2d;

public interface Player {
    int getNextX();

    int getNextY();

    Point2d getNextStep();

    String getName();

    void setChar(char aChar);

    char getChar();
}
