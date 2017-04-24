import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljam763 on 21/04/2017.
 */
public class Plateform {
    private final int length =(int) ((Math.random() * 50) + 30);
    private final int height = 5;
    private int x;
    private int y;
    private static int reference = 0;
    private int referenceNum = 0;


    public Plateform(){
        this.x = 200 - this.length/2;
        this.y = 800;
        this.referenceNum = reference;
    reference++;
}

    public Plateform(int x, int y){
        this.x = x;
        this.y = y;
        this.referenceNum = reference;
        reference++;
    }

    public int getX(){
    return x;
    }

    public int getY(){
        return y;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int maxSpawnheightprevious(Plateform other){
        return other.getY() - 50;
    }

    public int minSpawnheightprevious1(Plateform other){
        return other.getY() - 10;
    }

    public int xAxisPrevious(Plateform other) {
        double y = Math.random();
        if (y < 0.5) {
            y = -1;
        }
        else{
            y = 1;
        }
        int xValue = (int) ((Math.random() * 40* y) + other.getX());
        while (xValue < 0 || xValue > 400- this.length){
            xValue = (int) ((Math.random() * 40* y) + other.getX());
        }

      return xValue;
    }

    public int referencingPlateform (){
        return (referenceNum);
    }

    public int randomHeight (Plateform other){
        return this.minSpawnheightprevious1(other) - ((int) (Math.random()*(this.minSpawnheightprevious1(other) - this.maxSpawnheightprevious(other))));
    }

    public int setRandomXAxis (Plateform other){
        return xAxisPrevious(other);
    }

    public Plateform spawn (Plateform other){

        return new Plateform(setRandomXAxis(other), randomHeight(other));
    }
    public int getReferenceNum(){
        return referenceNum;
    }

    public static List<Plateform> AllXAxis(){
        System.out.println("Number of plateform for testing.");
        int numberOfPlate = Integer.parseInt(Keyboard.readInput())-1;
        List<Plateform> list_of_platforms =new ArrayList<>();
        Plateform a = new Plateform();
        list_of_platforms.add(a);

        for (int i = 0; i < numberOfPlate+1; i++) {
            Plateform temp = list_of_platforms.get(i);
            Plateform b = temp.spawn(list_of_platforms.get(temp.getReferenceNum()));
            list_of_platforms.add(b);
            System.out.println("X :"+ i+ " " + list_of_platforms.get(i).getX());
            System.out.println("Y :"+ i +" " +list_of_platforms.get(i).getY());
            // can alter this function to gain the the arraylist for the x and y axis by using get(i).getY() / get(i).getX();
        }
        return list_of_platforms;
    }

    public static void paint (GraphicsPainter painter, List<Plateform> list_of_platforms){
        for (Plateform platform: list_of_platforms) {
            painter.drawRect(platform.getX(), platform.getY(), platform.getLength(), platform.getHeight());
        }
    }


    public static void main(String[] args) {
        System.out.println(AllXAxis());
    }
}

