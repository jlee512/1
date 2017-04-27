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

    private Timer timer = new Timer(80, this);

    private List<Plateform> platforms;

    private Person person;

    private boolean defaultSpeedChange = true;

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
        System.out.println("First platform below: " + platformIndexBelow);
        //Setup list of platforms below to ensure no platforms that could be hit are missed
        List<Plateform> platformsBelow = platforms.subList(0, platformIndexBelow + 1);
        //Initialise default speed change to true and modify to false if any other speed change cases are satisfied
        defaultSpeedChange = true;

        int platformIndexAbove = person.platformAbove(platforms);
        System.out.println("Platform above: " + platformIndexAbove);

        //Check default speed change and estimate next position before setting speed
        int verticalSpeedCheck = person.getDeltaY() - person.GRAVITY;


        if (person.getMidY() == 800 && person.getMidX() == 200 && person.getDeltaY() == Person.INITIAL_DELTA_Y) {
            person.setDeltaY(person.INITIAL_DELTA_Y);
            System.out.println("First move of the game, initial speed: " + person.getDeltaY());
            defaultSpeedChange = false;


        }//If person is on the bottom and not moving up, the player has lost
        else if (person.getMidY() >= 800 && person.getDeltaY() <= 0) {
            System.out.println("Player loses");
            lose = true;
            person.setDeltaY(0);
            person.setY(785);
            System.out.println("Vertical speed: " + person.getDeltaY());
            defaultSpeedChange = false;


            //If person is on the top platform, the player has won
        } else if (platformIndexBelow == (platforms.size() - 1) && person.getMidY() == platforms.get(platforms.size() - 1).getY() && ((person.getX() + person.getWidth()) >= platforms.get(platforms.size() - 1).getX() && person.getX() <= (platforms.get(platforms.size() - 1).getX() + platforms.get(platforms.size() - 1).getLength()))) {
            System.out.println("Player wins");
            win = true;
            person.setY(platforms.get(platformIndexBelow).getY() - person.getHeight());
            person.setDeltaY(0);
            System.out.println("Vertical speed: " + person.getDeltaY());
            defaultSpeedChange = false;


            //Check person is on a platform, shift speed to initial speed
        } else if (person.getDeltaY() <= 0 && person.getMidY() == platforms.get(platformIndexBelow).getY() &&
                /*and person landing within platform width*/
                ((person.getX() + person.getWidth()) >= platforms.get(platformIndexBelow).getX() && person.getX() <= (platforms.get(platformIndexBelow).getX() + platforms.get(platformIndexBelow).getLength()))){
            System.out.println("Platform: " + platformIndexBelow  + " hit! Liftoff!");
            person.setDeltaY(person.INITIAL_DELTA_Y);
            defaultSpeedChange = false;

            //Else check if person has potential to hit any of underlying platforms and adjust speed accordingly
        } else {
            /*person going to be at or below platform level for any of platforms below*/
            for (int i = (platformsBelow.size() - 1); i > -1; i--) {
                if (/*person going to be at or below platform level for any of platforms below*/
                        (person.getMidY() - verticalSpeedCheck) >= platformsBelow.get(i).getY() && person.getMidY() != platformsBelow.get(i).getY() && (((person.getX() + person.getWidth()) - person.getDeltaX()) >= (platformsBelow.get(i).getX()) && (person.getMidX() - person.getDeltaX()) <= (platformsBelow.get(i).getX() + platformsBelow.get(i).getLength()))) {
                    //If the person is going to hit a platform, setDeltaY to land the person on the platform
                    System.out.println("Person will hit the platform " + (platformsBelow.size() - i) + " below the platform above on next time increment");
                    person.setDeltaY(person.getMidY() - platformsBelow.get(i).getY());
                    System.out.println("Vertical speed: " + person.getDeltaY());
                    defaultSpeedChange = false;
                    break;
                }
            }
            System.out.println(defaultSpeedChange);
            if (defaultSpeedChange){
                    System.out.println("Regular speed reduction by gravity");
                    person.changeDeltaY(Person.GRAVITY);
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
                        person.setDeltaX(18);
                        break;
                case KeyEvent.VK_RIGHT:
                        person.setDeltaX(-18);
                        break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        person.setDeltaX(0);
    }
}
