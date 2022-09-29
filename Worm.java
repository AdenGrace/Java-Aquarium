/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worm extends Swimmable {
    private static Worm instance = null;

    final private int EAT_DISTANCE = 5;
    private int eatCount;
    private AquaPanel pan;
    private boolean threadSuspended;
    private CyclicBarrier barrier;
    private Subject subject;

    private Worm() {
        super("Worm", 0, 0, 0, 0, 0, null);
        barrier = null;
        eatCount = 0;
        threadSuspended = false;
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

    public static Worm getInstance() {
        if (instance == null)
            instance = new Worm();
        return instance;
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
        return "Worm";
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

            if (pan.checkFood()) {
                if (barrier != null) {
                    try {
                        int k = barrier.await();
                        System.out.println(getColor() + " worm (" + k + ") is ready to eat!");
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
        }
    }

    public void drawAnimal(Graphics g) {
        g.setColor(col);
        if (x_dir == 1) // worm swims to right side
        {
            // Body of worm
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(size / 10));
            int[] wormX = new int[size];
            int[] wormY = new int[size];
            for (int i = 0; i < size; i++) {
                wormX[i] = x_front - size + i;
                wormY[i] = y_front + (int) (Math.sin(Math.toRadians(i * 680 / size)) * size / 4);
            }
            g.drawPolyline(wormX, wormY, size);

            // Eye of worm
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
            g2.fillOval(x_front - size / 20, y_front - size / 4, size / 16, size / 16);
            g2.setStroke(new BasicStroke(1));
        } else // worm swims to left side
        {
            // Body of worm
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(size / 10));
            int[] wormX = new int[size];
            int[] wormY = new int[size];
            for (int i = size - 1; i >= 0; i--) {
                wormX[i] = x_front + i;
                wormY[i] = y_front + (int) (Math.sin(Math.toRadians(((size - i) * 680) / size)) * size / 4);
            }
            g.drawPolyline(wormX, wormY, size);

            // Eye of worm
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
            g2.fillOval(x_front, y_front - size / 4, size / 16, size / 16);
            g2.setStroke(new BasicStroke(1));
        }
    }
}

