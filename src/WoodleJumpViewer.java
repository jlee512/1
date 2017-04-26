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

    private boolean win = false;
    private boolean lose = false;

    public WoodleJumpViewer() {

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

        //Get initial position at start of time click and assess platform immediately above and below (i.e. person needs to know where they are
        int midX_click_init = person.getMidX();
        System.out.println("Mid X init: " + midX_click_init);
        int midy_click_init = person.getMidY();
        System.out.println("Mid Y init: " + midy_click_init);

        int platformIndexBelow = person.platformBelow(platforms);
        System.out.println("Platform below: " + platformIndexBelow);
        int platformIndexAbove = person.platformAbove(platforms);
        System.out.println("Platform above: " + platformIndexAbove);

        //Check default speed change and estimate next position before setting speed
        int verticalSpeedCheck = person.getDeltaY() - person.GRAVITY;
        if (person.getMidY() == 800 && person.getMidX() == 200 && person.getDeltaY() == Person.INITIAL_DELTA_Y) {
            person.setDeltaY(person.INITIAL_DELTA_Y);
            System.out.println("First move of the game, initial speed: " + person.getDeltaY());


        }//If person is on the bottom and not moving up, the player has lost
        else if (person.getMidY() >= 800 && person.getDeltaY() <= 0) {
            System.out.println("Player loses");
            lose = true;
            person.setDeltaY(0);
            person.setY(785);
            System.out.println("Vertical speed: " + person.getDeltaY());


            //If person is on the top platform, the player has won
        } else if (platformIndexBelow == (platforms.size() - 1) && person.getDeltaY() == 0 && ((person.getX() + person.getWidth()) >= platforms.get(platforms.size() - 1).getX() && person.getX() <= (platforms.get(platforms.size() - 1).getX() + platforms.get(platforms.size() - 1).getLength()))) {
            System.out.println("Player wins");
            win = true;
            person.setY(platforms.get(platformIndexBelow).getY() - person.getHeight());
            person.setDeltaY(0);
            System.out.println("Vertical speed: " + person.getDeltaY());


            //Check person is on a platform, shift speed to initial speed
        } else if (person.getDeltaY() <= 0 && person.getMidY() == platforms.get(platformIndexBelow).getY() &&
                /*and person landing within platform width*/
                ((person.getX() + person.getWidth()) >= platforms.get(platformIndexBelow).getX() && person.getX() <= (platforms.get(platformIndexBelow).getX() + platforms.get(platformIndexBelow).getLength()))){
            System.out.println("Platform: " + platformIndexBelow  + " hit! Liftoff!");
            person.setDeltaY(person.INITIAL_DELTA_Y);


        } else if (/*person going to be at or below platform level*/
                (person.getMidY() - verticalSpeedCheck) >= platforms.get(platformIndexBelow).getY() && person.getMidY() != platforms.get(platformIndexBelow).getY() && (((person.getX() + person.getWidth()) - person.getDeltaX()) >= (platforms.get(platformIndexBelow).getX()) && (person.getMidX() - person.getDeltaX()) <= (platforms.get(platformIndexBelow).getX() + platforms.get(platformIndexBelow).getLength()))){
            //If the person is going to hit a platform, setDeltaY to land the person on the platform
            System.out.println("Person will hit platform " + platformIndexBelow + " level on next time increment");
            person.setDeltaY(person.getMidY() - platforms.get(platformIndexBelow).getY());
            System.out.println("Vertical speed: " + person.getDeltaY());


        } else{
            System.out.println("Regular speed reduction by gravity");
            if (person.getDeltaY() > -15) {
                person.changeDeltaY(Person.GRAVITY);
            } else {
                person.setDeltaY(-15);
            }
            System.out.println("Vertical speed: " + person.getDeltaY());
        }

        //Set next position based on the new speed determined above. If the player has won or lost, stop the timer
        if (!win && !lose)

        {
            person.setY(person.getY() - person.getDeltaY());
            person.setX(person.getX() - person.getDeltaX());
        } else

        {
            timer.stop();
        }

        System.out.println("Mid X Final: " + person.getMidX());
        System.out.println("Mid Y Final:" + person.getMidY());

        repaint();

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
                        person.setDeltaX(10);
                        break;
                case KeyEvent.VK_RIGHT:
                        person.setDeltaX(-10);
                        break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        person.setDeltaX(0);
    }
}
