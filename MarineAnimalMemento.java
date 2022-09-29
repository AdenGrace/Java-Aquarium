/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public class MarineAnimalMemento {
    public int horSpeed;
    public int verSpeed;
    public int size;
    public int x_front;
    public int y_front;
    public int x_dir;
    public int y_dir;
    public Color col;

    public MarineAnimalMemento(int hor, int ver, int s, int x, int y, int x_dir, int y_dir, Color c) {
        horSpeed = hor;
        verSpeed = ver;
        size = s;
        x_front = x;
        y_front = y;
        this.x_dir = x_dir;
        this.y_dir = y_dir;
        col = c;
    }

    @Override
    public String toString() {
        return  "horSpeed=" + horSpeed +
                ", verSpeed=" + verSpeed +
                ", size=" + size +
                ", x_front=" + x_front +
                ", y_front=" + y_front +
                ", x_dir=" + x_dir +
                ", y_dir=" + y_dir +
                ", col=" + col;
    }
}
