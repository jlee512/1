import com.sun.corba.se.impl.orbutil.graph.Graph;

/**
 * Created by jlee512 on 21/04/2017.
 */
public class Person {

    private int width;
    private int height;
    private int x;
    private int y;
    //Set deltaX to zero initially for testing simplicity
    private int deltaX = 0;
    private int deltaY = 0;
    public static final int INITIAL_DELTA_Y = 20;
    public static final int JUMP_HEIGHT = 60;
    public static final int GRAVITY = 4;

    public Person() {
        this.width = 10;
        this.height = 15;
        this.x = 25;
        this.y = 785;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public boolean initialiseJump (int platformX1, int platformX2, int platformTopY) {
        if ((getMidX() >= platformX1 && getMidX() <= platformX2) && (getMidY() == platformTopY) && (deltaY <= 0)) {
            return true;
        }
        return false;
    }

    public void jump (int platformX1, int platformX2, int platformTopY){
        //Set speed at time click
        if(initialiseJump(platformX1, platformX2, platformTopY)){
            //If beginning to jump set initial speed to 20
            deltaY = INITIAL_DELTA_Y;
            deltaX = 0;
        } else {
            //Set to reduced speed
            deltaY -= GRAVITY;
            deltaX = 0;
        }

        //Calculate next position at time click
        int nextX = x - deltaX;
        int nextY = y - deltaY;
        if(nextY > platformTopY){
            nextY = platformTopY;
        }

        //Set position for next time click
        setX(nextX);
        setY(nextY);
    }

    public static void main(String[] args) {
        //Person mid point calculation works
        Person guy = new Person();

        //Test jump to apex
        for (int i = 0; i < 10; i++) {
            guy.jump(10, 50, 800);
            System.out.println("Jump completed, positions:");
            System.out.println(guy.getMidX());
            System.out.println(guy.getMidY());
            System.out.println(guy.initialiseJump(10, 50,780));
        }
    }

    public void paint (GraphicsPainter painter){
        painter.drawRect(this.getX(), this.getY(),this.getWidth(), this.getHeight());
    }
}
