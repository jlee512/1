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

    private Timer timer = new Timer(2000, this);

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

        System.out.println(person.getMidY());

        //Loop through array of platforms

        //ok it runs but if we are using this then i think we needed to place the jump and fall function from person class to here, in order to put repaint() at the right place.
        for (int i = 0; i < platforms.size(); i++) {
            System.out.println(platforms.get(i).getY());
            if (!person.jump(person.getMidY(), 800-platforms.get(i).getY())) {
                break;
            } else {
                repaint();
                System.out.println(true);
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                WoodleJumpFrame frame = new WoodleJumpFrame();
                frame.setVisible(true);
            }
        });
    }
}
