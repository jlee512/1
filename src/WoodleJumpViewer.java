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

    private Timer timer = new Timer(50, this);

    private List<Plateform> platforms;

    private Person person;

    private int platformID;

    public WoodleJumpViewer() {

        platformID = 0;

        person = new Person();

        platforms = Plateform.AllXAxis();

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        GraphicsPainter painter = new GraphicsPainter(g);
        person.paint(painter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Calculate bounds of animation screen area
        int width = getWidth();
        int height = getHeight();

        //
        if (person.isRising()) {
            person.jumpRise(800 - platforms.get(platformID).getY());
        } else if (!person.isRising()) {
            person.jumpFall(800 - platforms.get(platformID).getY());
        }
        repaint();
        if(person.isOnNextPlatform()) {
            platformID++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WoodleJumpFrame frame = new WoodleJumpFrame();
                frame.setVisible(true);
            }
        });
    }
}
