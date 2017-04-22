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
    private boolean stillRising = true;
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

//    public boolean alignedWithPlatform(int platformX1, int platformX2){
//        if(getMidX() >= platformX1 && getMidX() <= platformX2){
//            return true;
//        }
//        return false;
//    }
    public boolean jump (int startingPoint, int platefromY){
        System.out.println("Speed " + deltaY);

        if(deltaY <= 0 && this.getY() <= platefromY){
            return falling(platefromY);
        }
        else if (deltaY <= 0 && !(this.getY() <= platefromY)){
            System.out.println("Fall jump can't reach at the end needed " + (this.getY() - platefromY));
            return false;
        }
        if(startingPoint == this.getMidY()){
            deltaY = INITIAL_DELTA_Y;
            setY(this.y - deltaY);
            System.out.println(this.y + " Rising");
        }
        setY(this.y - deltaY);
        System.out.println(this.y + " Rising");
        deltaY -= GRAVITY;
        return jump(startingPoint, platefromY);
    }

    public boolean falling(int platefromY){
        deltaY -= GRAVITY;
        System.out.println("Speed " + deltaY);
        System.out.println("Estimate Y " + (this.y - deltaY));
        setY(this.y - deltaY);
        System.out.println(this.y+ " Dropping");
        if (this.y >= platefromY){
            System.out.println("Speed " + deltaY);
            System.out.println("Estimate Y " + (this.y - deltaY));
            setY(platefromY);
            System.out.println("Current Y " + this.y);
            System.out.println("Success? Clear");
            deltaY = INITIAL_DELTA_Y;
            return true;
        }
        System.out.println("not clear yet still have"+ (this.y- platefromY));
        return falling(platefromY);
    }



    public static void main(String[] args) {
        //Person mid point calculation works
        Person guy = new Person();
        //more than max jump at first from 785 to 720 = 65;
//        int[] plates = {720, 685, 640, 620};
        //less than max jump at first from 785 to 730 = 55;
        int[] plates = {730, 685, 640, 620};
        for (int plate : plates) {
            if (!guy.jump(guy.getY(), plate)){
                break;
            }
            else{
                System.out.println(true);
            }
        }

        //Test jump to apex
    }

    public void paint (GraphicsPainter painter){
        painter.drawRect(this.getX(), this.getY(),this.getWidth(), this.getHeight());
    }
}
