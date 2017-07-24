package de.doubleslash.kata.designpattern.state;

public abstract class DishwasherState {

    protected Dishwasher dishwasher;

    protected DishwasherState(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    public void turnOn() {
        // Standardverhalten: keine Zustandsänderung
    }

    public void turnOff() {
        // Standardverhalten: keine Zustandsänderung
    }

    public void wash() {
        // Standardverhalten: keine Zustandsänderung
    }

    public void finished() {
        // Standardverhalten: keine Zustandsänderung
    }

    public void openDoor() {
        // Standardverhalten: keine Zustandsänderung
    }

    public void closeDoor() {
        // Standardverhalten: keine Zustandsänderung
    }

    public abstract String getStateName();

}
