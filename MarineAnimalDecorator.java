/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public class MarineAnimalDecorator implements MarineAnimal {
    protected MarineAnimal marineAnimal;

    public MarineAnimalDecorator(MarineAnimal marineAnimal) {
        this.marineAnimal = marineAnimal;
    }

    @Override
    public void paintFish(Color c) {
        marineAnimal.paintFish(c);
    }

    @Override
    public String getAnimalName() {
        return marineAnimal.getAnimalName();
    }

    @Override
    public MarineAnimalMemento saveStateToMemento() {
        return marineAnimal.saveStateToMemento();
    }

    @Override
    public void getStateFromMemento(MarineAnimalMemento memento) {
        marineAnimal.getStateFromMemento(memento);
    }
}
