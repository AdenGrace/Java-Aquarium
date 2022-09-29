/*
Authors:
Aden Turi - 211419395.
 */
public class AnimalFactory implements AbstractSeaFactory {
    @Override
    public Swimmable produceSeaCreature(String type) {
        if (type.equals("Fish")) {
            return new Fish();
        } else if (type.equals("Jellyfish")) {
            return new Jellyfish();
        } else if (type.equals("Worm")) {
            return Worm.getInstance();
        }
        return null;
    }
}
