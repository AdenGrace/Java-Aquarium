/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public abstract class Immobile implements SeaCreature {
    protected String name;
    protected int size;
    protected int x_front;
    protected int y_front;
    protected Color col;

    public Immobile(String name, int size, int x_front, int y_front) {
        this.name = name;
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = Color.green;
    }

    public abstract void set(int size, int x_front, int y_front);

    public String getName() {
        return name;
    }

    public ImmobileMemento saveStateToMemento() {
        return new ImmobileMemento(size, x_front, y_front, col);
    }

    public void getStateFromMemento(ImmobileMemento memento) {
        size = memento.size;
        x_front = memento.x_front;
        y_front = memento.y_front;
    }

}
