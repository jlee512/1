/**
 * Created by jlee512 on 21/04/2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class WoodleJumpViewer extends JPanel implements ActionListener {
    //frequency in milliseconds to generate Action events
    private final int TIME_STEP = 20;

    private Timer timer = new Timer(TIME_STEP, this);

    private List<Plateform> platforms;

    private Person person;

    private int platformID;

    public WoodleJumpViewer() {

        platformID = -1;

        person = new Person();

        platforms = Plateform.AllXAxis();

        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        GraphicsPainter painter = new GraphicsPainter(g);
        person.paint(painter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Calculate bounds of animation screen area
        int width = getWidth();
        int height = getHeight();

        //Get person to do first jump automatically
        person.jump(0, 400,800);
        platformID ++;

        //Loop through array of platforms
        for (int platformID = 0; platformID < platforms.size(); platformID++) {
            int platformY = platforms.get(platformID).getY();
            int platformX = platforms.get(platformID).getX();
            while (person.initialiseJump(platformX, platformX + 40, platformY)) {
                person.jump(platformX, platformX + 40, platformY);
                System.out.println(platformID);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame frame = new JFrame("Woodle Blast Game");
                frame.add(new WoodleJumpViewer());

                // Set window properties.
                frame.setSize(400, 800);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
