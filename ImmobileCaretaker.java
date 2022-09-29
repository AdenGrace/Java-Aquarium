/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
import java.util.ArrayList;
import java.util.List;

public class ImmobileCaretaker {
    private List<ImmobileMemento> immobileMementos = new ArrayList<ImmobileMemento>();

    public void add(ImmobileMemento memento) {
        immobileMementos.add(memento);
    }

    public ImmobileMemento get(int index) {
        return immobileMementos.get(index);
    }

    public ImmobileMemento[] asArray() {
        return immobileMementos.toArray(new ImmobileMemento[0]);
    }
}
