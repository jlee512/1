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
    private int deltaY;
    private int midX;
    private int midY;
    public static final int INITIAL_DELTA_Y = 20;
    public static final int JUMP_HEIGHT = 60;
    public static final int GRAVITY = 4;

    public Person(int deltaX, int deltaY) {
        this.width = 10;
        this.height = 15;
        this.x = 190;
        this.y = 785;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public boolean initialiseJump (int midY, int midX) {
        if ((midX >= 10 || midX <= 50) && (midY == 780 || midY == 800) && (deltaY <= 0)) {
            return true;
        }
        return false;
    }

    public void jump (){
        //Set speed at time click
        if(initialiseJump(getMidX(), getMidY())){
            //If beginning to jump set initial speed to 20
            deltaY = INITIAL_DELTA_Y;
            deltaX = 0;
        } else {
            //Set to reduced speed
            deltaY -= GRAVITY;
            deltaX = 0;
        }

        //Calculate next position at time click
        int nextX = x + deltaX;
        int nextY = y + deltaY;

        //Set position for next time click
        setX(nextX);
        setY(nextY);
    }
}
