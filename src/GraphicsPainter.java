import javax.swing.*;
import java.awt.*;

/**
 * Created by jlee512 on 21/04/2017.
 */
public class GraphicsPainter {
    // Delegate object.
    private Graphics g;

    /**
     * Creates a ictgradschool.industry.lab12.bounce.GraphicsPainter object and sets its Graphics delegate.
     */
    public GraphicsPainter(Graphics g) {
        this.g = g;
    }
    
    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }

    public void drawOval(int x, int y, int width, int height) {
        g.drawOval(x, y, width, height);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }
    
    public void setColor(Color color) {
        g.setColor(color);
    }
    
    public Color getColor() {
        return g.getColor();
    }
    
    public void drawPolygon(Polygon polygon) {
        g.drawPolygon(polygon);
    }
    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    public void drawImage(Image img, int displayX1, int displayY1, int displayX2, int displayY2, int sourceX1, int sourceY1, int sourceX2, int sourceY2) {
        g.drawImage(img, displayX1, displayY1, displayX2, displayY2, sourceX1, sourceY1, sourceX2, sourceY2,null);
    }
}
