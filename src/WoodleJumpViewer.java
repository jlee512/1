/**
 * Created by jlee512 on 21/04/2017.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class WoodleJumpViewer extends JPanel implements ActionListener, KeyListener {
    //frequency in milliseconds to generate Action events
    private final int TIME_STEP = 20;

    private Timer timer = new Timer(50, this);

    private List<Plateform> platforms;

    private Person person;

    private int platformID;

    private boolean lose = false;

    public WoodleJumpViewer() {

        platformID = 1;

        person = new Person();

        platforms = Plateform.AllXAxis();

        this.addKeyListener(this);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        GraphicsPainter painter = new GraphicsPainter(g);
        Plateform.paint(painter, platforms);
        person.paint(painter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Calculate bounds of animation screen area
        int width = getWidth();
        int height = getHeight();

        //
        if (person.isRising()) {
            person.jumpRise(platforms.get(platformID).getY());
        } else if (!person.isRising()) {
            if (!person.isReducePlatformID()) {
                person.jumpFall(platforms.get(platformID).getY(), platforms.get(platformID).getX());
            }
            else if (person.isReducePlatformID() && platformID > 0) {
                platformID--;
                person.setReducePlatformID(false);
                System.out.println("Platform Id: " + platformID);
            }
            if (person.isOnBottom()) {
                System.out.println("You lose, you have fallen to the bottom");
                lose = true;
                System.out.println(lose);
                timer.stop();
            }
            System.out.println(platformID);
        }
        requestFocusInWindow();
        repaint();
        if(person.isOnNextPlatform() && !(person.isReducePlatformID())) {
            platformID++;
            System.out.println("Platform Id: " + platformID);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    if(person.getDeltaX() <= 0) {
                        person.setDeltaX(-10);
                        break;
                    }
                    else {
                        person.setDeltaX(-10);
                        break;
                    }
                case KeyEvent.VK_RIGHT:
                    if(person.getDeltaX() >= 0) {
                        person.setDeltaX(10);
                        break;
                    }
                    else {
                        person.setDeltaX(10);
                        break;
                    }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        person.setDeltaX(0);
    }
}
