/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public class Laminaria extends Immobile {

    public Laminaria() {
        super("Laminaria", 0, 0, 0);
    }

    public Laminaria(int size, int x_front, int y_front) {
        super("Laminaria", size, x_front, y_front);
    }

    public void set(int size, int x_front, int y_front) {
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = Color.green;
    }

    @Override
    public void drawCreature(Graphics g) {
        g.setColor(col);

        g.fillArc(x_front-size/20, y_front-size, size/10, size*4/5, 0, 360);
        g.fillArc(x_front-size*3/20, y_front-size*13/15, size/10, size*2/3, 0, 360);
        g.fillArc(x_front+size/20, y_front-size*13/15, size/10, size*2/3, 0, 360);
        g.drawLine(x_front, y_front, x_front-size/10, y_front-size/5);
        g.drawLine(x_front, y_front, x_front+size/10, y_front-size/5);
    }
}
