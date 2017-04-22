///**
// * Created by jlee512 on 21/04/2017.
// */
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.*;
//
//public class WoodleJumpViewer extends JPanel implements ActionListener {
//    //frequency in milliseconds to generate Action events
//    private final int TIME_STEP = 20;
//
//    private Timer timer = new Timer(TIME_STEP, this);
//
//    private List<Plateform> platforms;
//
//    private Person person;
//
//    private int platformID;
//
//    public WoodleJumpViewer() {
//
//        platformID = -1;
//
//        person = new Person();
//
//        platforms = Plateform.AllXAxis();
//
//        timer.start();
//    }
//
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//
//        GraphicsPainter painter = new GraphicsPainter(g);
//        person.paint(painter);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        //Calculate bounds of animation screen area
//        int width = getWidth();
//        int height = getHeight();
//
//        //Get person to do first jump automatically
//
//        person.jump(0, 400,800);
//        System.out.println(person.getMidY());
//        platformID ++;
//        repaint();
//
//        //Loop through array of platforms
//        for (int platformID = 0; platformID < platforms.size(); platformID++) {
//            System.out.println("PlatformId: " + platformID);
//            System.out.println(person.getDeltaY());
//            int platformY = 800 - platforms.get(platformID).getY();
//            int platformX = platforms.get(platformID).getX();
//            int i = 0;
//            //Set speed to initial speed because we have hit a platform
//            while (!person.initialiseJump(platformX, platformX + 40, platformY) && i < 15) {
//                System.out.println("midY initial: " + person.getMidY());
//                System.out.println("Plat Y:" + platformY);
//
//                person.jump(platformX, platformX + 40, platformY);
//                repaint();
//                if(person.getMidY() == platformY && person.getDeltaY() <= 0){
//                    person.setDeltaY(person.INITIAL_DELTA_Y);
//                }
//                System.out.println("midY final: " + person.getMidY());
//                System.out.println(person.initialiseJump(platformX, platformX + 40, platformY));
//                i++;
//            }
//
//
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable(){
//            @Override
//            public void run(){
//                WoodleJumpFrame frame = new WoodleJumpFrame();
//                frame.setVisible(true);
//            }
//        });
//    }
//}
