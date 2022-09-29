/*
Authors:
Aden Turi - 211419395.
 */
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Fish extends Swimmable implements MarineAnimal {
    final private int EAT_DISTANCE = 5;
    final private int FOOD_TIMER = 200;
    private int eatCount;
    private int eatTimer;
    private AquaPanel pan;
    private boolean threadSuspended;
    private CyclicBarrier barrier;
    private Subject subject;
    private HungerState hungerState;

    public Fish() {
        super("Fish", 0, 0, 0, 0, 0, null);
        barrier = null;
        eatCount = 0;
        eatTimer = FOOD_TIMER;
        threadSuspended = false;
        this.hungerState = new Satiated();
    }

    public Fish(Subject subject, int s, int x, int y, int h, int v, Color c, AquaPanel p) {
        super("Fish", h, v, s, x, y, c);
        pan = p;
        barrier = null;
        eatCount = 0;
        eatTimer = FOOD_TIMER;
        threadSuspended = false;
        this.subject = subject;
        this.hungerState = new Satiated();
    }

    public Fish clone() {
        Fish cloned = new Fish();
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
        this.hungerState = new Satiated();
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
        return "Fish";
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
                this.subject.setState(getColor() + " fish wants to eat!");
                eatTimer = FOOD_TIMER;
                this.hungerState = new Hungry();
            }

            x_front += horSpeed * x_dir;
            y_front += verSpeed * y_dir;

            if (x_front > pan.getWidth()) {
                x_dir = -1;
                x_front = (int) (pan.getWidth() - size * 1.25);
            } else if (x_front < 0) {
                x_dir = 1;
                x_front = (int) (size * 1.25);
            }

            if (y_front > (int) (pan.getHeight() - 30 - size * 0.25)) {
                y_dir = -1;
            } else if (y_front < (int) (size * 0.25)) {
                y_dir = 1;
            }

            pan.repaint();

            hungerState.doAction(this);
        }
    }

    public void chaseFood() {
        if (pan.checkFood()) {
            if (barrier != null) {
                try {
                    int k = barrier.await();
                    System.out.println(getColor() + " fish (" + k + ") is ready to eat!");
                    barrier = null;
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                } catch (Exception ex) {
                    return;
                }
            }

            x_front -= horSpeed * x_dir;
            y_front -= verSpeed * y_dir;

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
        }
    }

    public void drawAnimal(Graphics g) {
        g.setColor(col);
        if (x_dir == 1) // fish swims to right side
        {
            // Body of fish
            g.fillOval(x_front - size, y_front - size / 4, size, size / 2);

            // Tail of fish
            int[] x_t = {x_front - size - size / 4, x_front - size - size / 4, x_front - size};
            int[] y_t = {y_front - size / 4, y_front + size / 4, y_front};
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);

            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
            g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);

            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));
        } else // fish swims to left side
        {
            // Body of fish
            g.fillOval(x_front, y_front - size / 4, size, size / 2);

            // Tail of fish
            int[] x_t = {x_front + size + size / 4, x_front + size + size / 4, x_front + size};
            int[] y_t = {y_front - size / 4, y_front + size / 4, y_front};
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);

            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
            g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10, size / 10);

            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));
        }
    }

    @Override
    public void paintFish(Color c) {
        this.col = c;
    }
}

