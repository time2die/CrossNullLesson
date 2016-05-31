package lessons.crossline.data;

/**
 * Created by time2die on 10.05.2016.
 */
public class Point2d {

    public Point2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof Point2d){
            Point2d another = (Point2d) obj ;
            if( this.getX() == another.getX() && this.y == another.getY()){
                return  true ;
            }
        }
        return  false ;
    }


    @Override
    public int hashCode(){
      return x*y ;
    }
}
