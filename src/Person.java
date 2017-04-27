import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.List;

/**
 * Created by jlee512 on 21/04/2017.
 */
public class Person {

    //Geometry of the person
    private int width;
    private int height;
    private int x;
    private int y;
    private int i =0;

    //Set initial speeds when person is created
    private int deltaX = 0;
    private int deltaY = INITIAL_DELTA_Y;

    //Constants (initial vertical speed and gravity
    public static final int INITIAL_DELTA_Y = 25;
    public static final int GRAVITY = 4;


    //Constructor
    public Person() {
        this.width = 10;
        this.height = 15;
        this.x = 195;
        this.y = 785;
    }


    //Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMidX() {
        return (x + width / 2);
    }

    public int getMidY() {
        return (y + height);
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(int deltaY){
        this.deltaY = deltaY;
    }

    public void changeDeltaY(int acceleration) {
        this.deltaY -= acceleration;
    }


    //Methods describing what the person can do! Jumping/falling/platforms
    public int platformBelow(List<Plateform> platforms) {
        for (int i = 0; i < (platforms.size() - 1); i++) {
            //This function confirms which two platforms the person is currently between
            if (platforms.get(i).getY() >= this.getMidY() && platforms.get(i + 1).getY() < this.getMidY()) {
                return i;
            }
            else if (platforms.get(platforms.size() - 1).getY() >= this.getMidY()){
                return (platforms.size() - 1);
            }
        }
        return (0);
    }

    public int platformAbove(List<Plateform> platforms){
        for (int i = (platforms.size() - 1); i > 0 ; i--) {
            if (platforms.get(i).getY() < this.getMidY() && platforms.get(i - 1).getY() >= this.getMidY()) {
                return i;
            }
            else if (platforms.get(0).getY() <= this.getMidY()){
                return 0;
            }
        }
        return (platforms.size() - 1);
    }

    public void paint (GraphicsPainter painter){
        painter.fillRect(this.x, this.y, this.width, this.height);
    }

    public static void main(String[] args) {

    }
}
