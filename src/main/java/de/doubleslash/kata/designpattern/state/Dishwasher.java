package de.doubleslash.kata.designpattern.state;

public class Dishwasher {

    private DishwasherState offState = new OffState(this);
    // TODO: weitere Zustandsobjekte implementieren und initialisieren

    private DishwasherState dishwasherState = getOffState();

    public DishwasherState getOffState() {
        return offState;
    }

    // TODO: getter-Methoden für weitere Zustandsobjekte

    public void turnOn() {
        dishwasherState.turnOn();
    }

    public void turnOff() {
        dishwasherState.turnOff();
    }

    public void wash() {
        dishwasherState.wash();
    }

    public void finished() {
        dishwasherState.finished();
    }

    public void openDoor() {
        dishwasherState.openDoor();
    }

    public void closeDoor() {
        dishwasherState.closeDoor();
    }

    public void setState(DishwasherState dishwasherState) {
        this.dishwasherState = dishwasherState;
    }

    public DishwasherState getState() {
        return dishwasherState;
    }

}
