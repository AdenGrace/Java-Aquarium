/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class JPanelDecorator extends JPanel {
    private Set<Swimmable> animals;

    public JPanelDecorator(AquaFrame f, Set<Swimmable> animals) {
        this.animals = animals;

        setLayout(new GridLayout(animals.size(), 2, 5, 5));

        for (Swimmable animal: animals) {
            add(new JButton(animal.getAnimalName()));
            add(new JButton("Change Color"));
        }
    }
}