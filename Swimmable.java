/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Thread implements SeaCreature, Cloneable {

    protected String type;
    protected int horSpeed;
    protected int verSpeed;
    protected int size;
    protected int x_front;
    protected int y_front;
    protected int x_dir;
    protected int y_dir;
    protected Color col;

    public Swimmable(String type) {
        this.type = type;
        horSpeed = 0;
        verSpeed = 0;
    }

    public Swimmable(String type, int hor, int ver, int s, int x, int y, Color c) {
        this.type = type;
        horSpeed = hor;
        verSpeed = ver;
        size = s;
        x_front = x;
        y_front = y;
        x_dir = 1;
        y_dir = 1;
        col = c;
    }

    public Swimmable clone() {
        Swimmable clone = null;

        try {
            clone = (Swimmable) super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

    public int getHorSpeed() {
        return horSpeed;
    }

    public int getVerSpeed() {
        return verSpeed;
    }

    public void setHorSpeed(int hor) {
        horSpeed = hor;
    }

    public void setVerSpeed(int ver) {
        verSpeed = ver;
    }

    abstract public void set(Subject subject, int s, int x, int y, int h, int v, Color c, AquaPanel p);

    abstract public String getAnimalName();

    public void drawCreature(Graphics g) {
        drawAnimal(g);
    }

    abstract public void drawAnimal(Graphics g);

    abstract public void setSuspend();

    abstract public void setResume();

    abstract public void setBarrier(CyclicBarrier b);

    abstract public int getSize();

    abstract public void eatInc();

    abstract public int getEatCount();
    public void resetEatTimer() { }

    abstract public String getColor();

}

