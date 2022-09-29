/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public class Zostera extends Immobile {

    public Zostera() {
        super("Zostera", 0, 0, 0);
    }

    public Zostera(int size, int x_front, int y_front) {
        super("Zostera", size, x_front, y_front);
    }

    public void set(int size, int x_front, int y_front) {
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = Color.green;
    }

    public void drawCreature(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.setColor(col);

        g.drawLine(x_front, y_front, x_front, y_front-size);
        g.drawLine(x_front-2, y_front, x_front-10, y_front-size*9/10);
        g.drawLine(x_front+2, y_front, x_front+10, y_front-size*9/10);
        g.drawLine(x_front-4, y_front, x_front-20, y_front-size*4/5);
        g.drawLine(x_front+4, y_front, x_front+20, y_front-size*4/5);
        g.drawLine(x_front-6, y_front, x_front-30, y_front-size*7/10);
        g.drawLine(x_front+6, y_front, x_front+30, y_front-size*7/10);
        g.drawLine(x_front-8, y_front, x_front-40, y_front-size*4/7);
        g.drawLine(x_front+8, y_front, x_front+40, y_front-size*4/7);

        g2.setStroke(new BasicStroke(1));
    }
}
