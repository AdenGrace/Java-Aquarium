/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Jellyfish extends Swimmable implements MarineAnimal {
    final private int EAT_DISTANCE = 5;
    final private int FOOD_TIMER = 300;
    private int eatCount;
    private int eatTimer;
    private AquaPanel pan;
    private boolean threadSuspended;
    private CyclicBarrier barrier;
    private Subject subject;

    public Jellyfish() {
        super("Jellyfish", 0, 0, 0, 0, 0, null);
        barrier = null;
        eatCount = 0;
        eatTimer = FOOD_TIMER;
        threadSuspended = false;
    }

    public Jellyfish(Subject subject, int s, int x, int y, int h, int v, Color c, AquaPanel p) {
        super("Jellyfish", h, v, s, x, y, c);
        pan = p;
        barrier = null;
        eatCount = 0;
        eatTimer = FOOD_TIMER;
        threadSuspended = false;
        this.subject = subject;
    }

    public Jellyfish clone() {
        Jellyfish cloned = new Jellyfish();
        cloned.set(subject, size, x_front, y_front, horSpeed, verSpeed, col, pan);
        return cloned;
    }

    public void set(Subject subject, int s, int x, int y, int h, int v, Color c, AquaPanel p) {
        horSpeed = h;
        verSpeed = v;
        size = s;
        x_front = x;
        y_front = y;
        col = c;
        pan = p;
        this.subject = subject;
    }

    public int getSize() {
        return size;
    }

    public void eatInc() {
        eatCount++;
    }

    public int getEatCount() {
        return eatCount;
    }

    public void resetEatTimer() {
        eatTimer = FOOD_TIMER;
    }

    synchronized public void setSuspend() {
        threadSuspended = true;
    }

    synchronized public void setResume() {
        threadSuspended = false;
        notify();
    }

    public void setBarrier(CyclicBarrier b) {
        barrier = b;
    }

    public String getAnimalName() {
        return "Jellyfish";
    }

    @Override
    public MarineAnimalMemento saveStateToMemento() {
        return new MarineAnimalMemento(horSpeed, verSpeed, size, x_front, y_front, x_dir, y_dir, col);
    }

    @Override
    public void getStateFromMemento(MarineAnimalMemento memento) {
        this.horSpeed = memento.horSpeed;
        this.verSpeed = memento.verSpeed;
        this.size = memento.size;
        this.x_front = memento.x_front;
        this.y_front = memento.y_front;
        this.x_dir = memento.x_dir;
        this.y_dir = memento.y_dir;
        this.col = memento.col;
    }

    public String getColor() {
        if (col == Color.black)
            return "Black";
        if (col == Color.red)
            return "Red";
        if (col == Color.blue)
            return "Blue";
        if (col == Color.green)
            return "Green";
        if (col == Color.cyan)
            return "Cyan";
        if (col == Color.orange)
            return "Orange";
        if (col == Color.yellow)
            return "Yellow";
        if (col == Color.magenta)
            return "Magenta";
        if (col == Color.pink)
            return "Pink";
        return null;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);

                synchronized (this) {
                    while (threadSuspended)
                        wait();
                }
            } catch (InterruptedException e) {
                System.out.println("Sleep ERROR!");
            }

            eatTimer--;
            if (eatTimer == 0) {
                this.subject.setState(getColor() + " jellyfish wants to eat!");
                eatTimer = FOOD_TIMER;
            }

            if (pan.checkFood()) {
                if (barrier != null) {
                    try {
                        int k = barrier.await();
                        System.out.println(getColor() + " jellyfish (" + k + ") is ready to eat!");
                        barrier = null;
                    } catch (InterruptedException ex) {
                        return;
                    } catch (BrokenBarrierException ex) {
                        return;
                    } catch (Exception ex) {
                        return;
                    }
                }
                double oldSpead = Math.sqrt(horSpeed * horSpeed + verSpeed * verSpeed);
                double newHorSpeed = oldSpead * (x_front - pan.getWidth() / 2) / (Math.sqrt(Math.pow(x_front - pan.getWidth() / 2, 2) + Math.pow(y_front - pan.getHeight() / 2, 2)));
                double newVerSpeed = oldSpead * (y_front - pan.getHeight() / 2) / (Math.sqrt(Math.pow(x_front - pan.getWidth() / 2, 2) + Math.pow(y_front - pan.getHeight() / 2, 2)));
                x_front -= newHorSpeed;
                y_front -= newVerSpeed;
                if (x_front < pan.getWidth() / 2)
                    x_dir = 1;
                else
                    x_dir = -1;
                if ((Math.abs(x_front - pan.getWidth() / 2) < EAT_DISTANCE) && (Math.abs(y_front - pan.getHeight() / 2) < EAT_DISTANCE)) {
                    //pan.repaint();
                    pan.eatFood(this);
                }
            } else {
                x_front += horSpeed * x_dir;
                y_front += verSpeed * y_dir;
            }

            if (x_front + size / 2 > pan.getWidth()) {
                x_dir = -1;
            } else if (x_front - size / 2 < 0) {
                x_dir = 1;
            }

            if (y_front > (int) (pan.getHeight() - 30 - size * 0.25)) {
                y_dir = -1;
            } else if (y_front < (int) (size * 0.25)) {
                y_dir = 1;
            }

            pan.repaint();
        }
    }

    @Override
    public void paintFish(Color c) {
        this.col = c;
    }

    public void drawAnimal(Graphics g) {
        int numLegs;
        if (size < 40)
            numLegs = 5;
        else if (size < 80)
            numLegs = 9;
        else
            numLegs = 12;
        g.setColor(col);
        g.fillArc(x_front - size / 2, y_front - size / 4, size, size / 2, 0, 180);
        for (int i = 0; i < numLegs; i++)
            g.drawLine(x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front, x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front + size / 3);
    }
}

