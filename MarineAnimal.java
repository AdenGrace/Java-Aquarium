/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.awt.*;

public interface MarineAnimal {
    void paintFish(Color c);
    String getAnimalName();
    public MarineAnimalMemento saveStateToMemento();
    public void getStateFromMemento(MarineAnimalMemento memento);
}
