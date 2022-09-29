/*
Authors:
Aden Turi - 211419395.
 */
public class Hungry implements HungerState {
    public void doAction(Fish fish) {
        fish.chaseFood();
    }
}
