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
//        return new Plateform(10, randomHeight(other));
        return new Plateform(setRandomXAxis(), randomHeight(other));
    }
    public int getReferenceNum(){
        return referenceNum;
    }

     public static int[] AllYAxis(){
         System.out.println("Number of plateform for testing.");
        int numberOfPlate = Integer.parseInt(Keyboard.readInput())-1;
         List<Plateform> list_of_platforms =new ArrayList<>();
         Plateform a = new Plateform();
         list_of_platforms.add(a);
         System.out.println("X: " + a.getX());
         System.out.println("Y: " + a.getY());
         System.out.println();
         for (int i = 0; i < numberOfPlate; i++) {
             Plateform temp = list_of_platforms.get(i);
             Plateform b = temp.spawn(list_of_platforms.get(temp.getReferenceNum()));
             list_of_platforms.add(b);
             System.out.println("X: " + b.getX());
             System.out.println("Y: " + b.getY());
             System.out.println();
             // can alter this function to gain the the arraylist for the x and y axis by using get(i).getY() / get(i).getX();
         }
         int[] allYAixs = new int[numberOfPlate+1];
         for (int i = 0; i < numberOfPlate+1; i++) {
             allYAixs[i] = 800- list_of_platforms.get(i).getY();
             System.out.println(allYAixs[i]);
         }
         return allYAixs;
     }



    public static void main(String[] args) {
        System.out.println(AllYAxis());
//        List<Plateform> list_of_platforms =new ArrayList<>();
//        Plateform a = new Plateform();
//        list_of_platforms.add(a);
////        Plateform b = a.spawn(list_of_platforms.get(a.getReferenceNum()));
////        System.out.println(b.getReferenceNum());
////        list_of_platforms.add(b);
////        Plateform c = b.spawn(list_of_platforms.get(b.getReferenceNum()));
////        list_of_platforms.add(c);
//        System.out.println("X: " + a.getX());
//        System.out.println("Y: " + a.getY());
//        System.out.println();
//        for (int i = 0; i < 9; i++) {
//            Plateform temp = list_of_platforms.get(i);
//            Plateform b = temp.spawn(list_of_platforms.get(temp.getReferenceNum()));
//            list_of_platforms.add(b);
//            System.out.println("X: " + b.getX());
//            System.out.println("Y: " + b.getY());
//            System.out.println();
//        }
    }
}
