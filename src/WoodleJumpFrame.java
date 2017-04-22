import javax.swing.*;
import java.awt.*;

/**
 * Created by jlee512 on 21/04/2017.
 */
public class WoodleJumpFrame extends JFrame {

    public WoodleJumpFrame() {
        setTitle("WOODLE JUMP!");
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WoodleJumpViewer frameContent = new WoodleJumpViewer();
        Container visibleArea = getContentPane();
        visibleArea.add(frameContent);
        frameContent.setPreferredSize(new Dimension (400, 800));
        pack();
        frameContent.requestFocusInWindow();
    }
}
