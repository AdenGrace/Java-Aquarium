/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public class ImmobileMemento {
    public int size;
    public int x_front;
    public int y_front;
    public Color col;

    public ImmobileMemento(int s, int x, int y, Color c) {
        size = s;
        x_front = x;
        y_front = y;
        col = c;
    }

    @Override
    public String toString() {
        return "size=" + size +
                ", x_front=" + x_front +
                ", y_front=" + y_front +
                ", col=" + col;
    }
}
