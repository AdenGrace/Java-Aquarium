/*
Authors:
Aden Turi - 211419395.
Abrar Jabareen - 318390856.
 */
public class PlantFactory implements AbstractSeaFactory {
    @Override
    public Immobile produceSeaCreature(String type) {
        if (type.equals("Laminaria")) {
            return new Laminaria();
        } else if (type.equals("Zostera")) {
            return new Zostera();
        }
        return null;
    }
}
