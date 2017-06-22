package de.doubleslash.kata.designpattern.factory;

public class LoggerConfiguration {

    String type;

    public LoggerConfiguration(String type) {
        this.type = type;
    }

    public String getConfiguredLoggerType() {
        return type;
    }
}
