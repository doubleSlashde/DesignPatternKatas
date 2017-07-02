package de.doubleslash.kata.designpattern.state;

public abstract class State {

    protected String stateName;

    public State turnOn() {
        return this;
    }

    public State turnOff() {
        return this;
    }

    public State wash() {
        return this;
    }

    public State openDoor() {
        return this;
    }

    public State closeDoor() {
        return this;
    }

    public String getStateName() {
        return stateName;
    }

    public String toString() {
        return getStateName();
    }

}
