package de.doubleslash.kata.designpattern.state;

/**
 * Zustand "off" ("aus").
 */
public class OffState extends DishwasherState {

    public OffState(Dishwasher dishwasher) {
        super(dishwasher);
    }

    @Override
    public void turnOn() {
        // TODO: folgende Zeile auskommentieren und zum Funktionieren bringen:
        // dishwasher.setState(dishwasher.getOnState());
    }

    // TODO: korrekten Zustandsnamen zurückgeben
    @Override
    public String getStateName() {
        return null;
    }

}
