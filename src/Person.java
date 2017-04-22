import com.sun.corba.se.impl.orbutil.graph.Graph;

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
    private int deltaX = 0;
    private int deltaY = 20;
    private boolean rising = true;
    private boolean onNextPlatform = false;

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

    public boolean isRising() {
        return rising;
    }

    public boolean isOnNextPlatform() {
        return onNextPlatform;
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
        System.out.println("Now at " + this.getMidY() + " and rising");
        deltaY -= GRAVITY;

        return true;
    }

    public boolean jumpFall(int platefromY){
        //Reduce speed by gravity function and print initial speed
        System.out.println("Speed " + deltaY);

        //Setup descriptive local variables to clarify inner workins of jump method
        boolean onPlatfornm = this.getMidY() == platefromY;
        boolean nextBelowPlatform = (this.getMidY() - deltaY) > platefromY;
        boolean abovePlatform = this.getMidY() < platefromY;
        boolean atBottom = this.getMidY() == 800;


        //If fall will not reach this platform, return false to signal the jump will not land on the subject platform
        if (!(abovePlatform) && !(onPlatfornm) && atBottom){
            System.out.println("Person has fallen to the bottom ");
            return false;
        }
        //If fall method called when on platform return false to switch back to rising
        else if (onPlatfornm){
            System.out.println("Platform hit! MidY at: " + this.getMidY());
            rising = true;
            onNextPlatform = true;
            deltaY = INITIAL_DELTA_Y;
            return false;
        }
        else if (nextBelowPlatform){
            System.out.println("Platform will hit on next time step. Adjust speed to hit platform on next step");
            deltaY = this.getMidY() - platefromY;
            setY(this.y - deltaY);
            System.out.println(this.getMidY());
            return true;
        }
        //Otherwise continue fall method as usual
        System.out.println("Still falling...");
        setY(this.y - deltaY);
        System.out.println("Now at: " + getMidY() + " and falling");
        deltaY -= GRAVITY;
        return true;
    }

    public void paint (GraphicsPainter painter){
        painter.drawRect(this.getX(), this.getY(),this.getWidth(), this.getHeight());
    }

    public static void main(String[] args) {
        //Person mid point calculation works
        Person guy = new Person();
        //more than max jump at first from 785 to 720 = 65;
        //int[] plates = {720, 685, 640, 620};
        //less than max jump at first from 785 to 730 = 55;
        int[] plates = {750, 700, 655, 620};
        for (int plate : plates) {
            while(guy.jumpRise(plate)){
                guy.jumpRise(plate);
            }
            while(guy.jumpFall(plate)){
                guy.jumpFall(plate);
            }
        }

        //Test jump to apex
    }
}
