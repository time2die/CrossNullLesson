import java.awt.*;
import java.awt.geom.Point2D;

public interface Player {
    int getNextX() ;
    int getNextY() ;
    Point2d getNextStep() ;
    String getName();

    char getChar();
}
