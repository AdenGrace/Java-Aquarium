/*
Authors:
Aden Turi - 211419395.
 */
import javax.swing.*;

public class FoodObserver extends Observer {
    private AquaFrame frame;
    private Subject subject;

    public FoodObserver(AquaFrame frame, Subject subject) {
        this.frame = frame;
        this.subject = subject;
        subject.attach(this);
    }

    public void update() {
        JOptionPane.showMessageDialog(frame, this.subject.getState());
    }

}
