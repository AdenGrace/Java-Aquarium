/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.util.ArrayList;
import java.util.List;

public class MarineAnimalCaretaker {
    private List<MarineAnimalMemento> marineAnimalMementos = new ArrayList<MarineAnimalMemento>();

    public void add(MarineAnimalMemento memento) {
        marineAnimalMementos.add(memento);
    }

    public MarineAnimalMemento get(int index) {
        return marineAnimalMementos.get(index);
    }

    public MarineAnimalMemento[] asArray() {
        return marineAnimalMementos.toArray(new MarineAnimalMemento[0]);
    }
}
