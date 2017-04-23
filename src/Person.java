import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by jlee512 on 21/04/2017.
 */
public class Person {

    private int width;
    private int height;
    private int x;
    private int y;
    private int i =0;
    //Set deltaX to zero initially for testing simplicity
    private int deltaX = 1;
    private int deltaY = INITIAL_DELTA_Y;
    private boolean rising = true;
    private boolean onNextPlatform = false;
    private boolean reducePlatformID = false;
    private boolean onBottom = false;

    //Constants
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

    public boolean isOnBottom() {
        return onBottom;
    }

    public boolean isRising() {
        return rising;
    }

    public boolean isOnNextPlatform() {
        return onNextPlatform;
    }

    public boolean isReducePlatformID() {
        return reducePlatformID;
    }

    public void setReducePlatformID(boolean reducePlatformID) {
        this.reducePlatformID = reducePlatformID;
    }

    public boolean jumpRise (int platefromY){
        //This method is run at each time step of the WoodleJumpViewer

        //Printout speed when jump function called
        System.out.println("Speed " + deltaY);
        onNextPlatform = false;

        //Setup descriptive local variables to clarify inner workins of jump method
        boolean falling = deltaY <= 0;

        //If falling, check whether fall has potential to land on platform (if so, switch to fall function and call from viewer)
        if(falling){
            rising = false;
            return false;
        }
        
        //If the person is rising but is not initialising a new jump, return true to maintain rising sequence
        setY(this.y - deltaY);
        setX(this.x + deltaX);
        System.out.println("Now at " + this.getMidY() + " and rising");
        deltaY -= GRAVITY;

        return true;
    }

    public boolean jumpFall(int plateformY, int platformX){
        //Reduce speed by gravity function and print initial speed
        System.out.println("Speed " + deltaY);

        //Setup descriptive local variables to clarify inner workins of jump method
        reducePlatformID = false;
        boolean atPlatformLevel = (this.getMidY() == plateformY);
        boolean withinPlatformWidth = (this.getMidX() >= platformX) && (this.getMidX() <= (platformX + 40));
        boolean nextBelowPlatform = (this.getMidY() - deltaY) > plateformY;
        boolean nextOnBottom = (this.getMidY() - deltaY) >= 800;
        boolean nextXWithinPlatform = ((this.getMidX() + deltaX) >= platformX) && ((this.getMidX() + deltaX) <= platformX + 40);
        boolean abovePlatform = this.getMidY() < plateformY;
        onBottom = this.getMidY() >= 800;


        if (nextBelowPlatform && nextXWithinPlatform){
            System.out.println("Platform has potential to hit on next time step. Adjust speed to hit platform on next step");
            deltaY = this.getMidY() - plateformY;
            setY(this.y - deltaY);
            setX(this.x + deltaX);
            System.out.println(this.getMidY());
            return true;
        }
        //If fall will not reach this platform, return false to signal the jump will not land on the subject platform
        else if (!(abovePlatform) && onBottom){
            System.out.println("Person has fallen to the bottom ");
            this.setY(785);
            deltaY=0;
            deltaX = 0;
            return false;
        }
        else if(nextOnBottom){
            System.out.println("Bottom will be hit on next time step");
            deltaY = this.getMidY() - 800;
            setY(this.y - deltaY);
            setX(this.x + deltaX);
            return true;
        }
        //If fall method called when on platform return false to switch back to rising
        else if (atPlatformLevel){
            if (withinPlatformWidth) {
                System.out.println("Platform hit! MidY at: " + this.getMidY());
                rising = true;
                onNextPlatform = true;
                deltaY = INITIAL_DELTA_Y;
                return false;
            }
            System.out.println("At platform level but outside of platform width, at: " + getMidX() + " , reduce platform ID");
            reducePlatformID = true;
        }

        //Otherwise continue fall method as usual
        System.out.println("Still falling...");
        setY(this.y - deltaY);
        setX(this.x + deltaX);
        System.out.println("Now at: " + getMidY() + " and falling");
        deltaY -= GRAVITY;
        return true;
    }

    public void paint (GraphicsPainter painter){
        painter.fillRect(this.getX(), this.getY(),this.getWidth(), this.getHeight());
    }

    public static void main(String[] args) {
        //Person mid point calculation works
        Person guy = new Person();
        //more than max jump at first from 785 to 720 = 65;
        //int[] plates = {720, 685, 640, 620};
        //less than max jump at first from 785 to 730 = 55;
        int[] plates = {750, 700, 655, 620, 10, 10 ,10 ,10};
        for (int i = 0; i < plates.length; i++) {
            while(guy.jumpRise(plates[i])){
                guy.jumpRise(plates[i]);
            }
            while(guy.jumpFall(plates[i], plates[i + 4])){
                guy.jumpFall(plates[i], plates[i + 4]);
            }
        }

        //Test jump to apex
    }
}
