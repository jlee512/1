import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljam763 on 21/04/2017.
 */
public class Plateform {
    private final int length = 40;
    private final int height = 10;
    private int x;
    private int y;
    private static int reference = 0;
    private int referenceNum = 0;


    public Plateform(){
        this.x = 10;
        this.y = 20;
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

    public int maxSpawnheight(Plateform other){
        return other.getY() + 50;
    }

    public int minSpwanheight(Plateform other){
        return other.getY() + 10;
    }
    public int referencingPlateform (){
        return (referenceNum);
    }
    public int randomHeight (Plateform other){
        return this.minSpwanheight(other) + ((int) (Math.random()*(this.maxSpawnheight(other)-this.minSpwanheight(other))));
    }
    public int setRandomXAxis (){
        return (int)(Math.random()*370);
    }

    public Plateform spawn (Plateform other){
        return new Plateform(10, randomHeight(other));
//        return new Plateform(setRandomXAxis(), randomHeight(other));
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



    public static void main(String[] args) {
        System.out.println(AllXAxis());
    }
}
