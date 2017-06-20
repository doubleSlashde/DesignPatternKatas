package de.doubleslash.kata.designpattern.factory;

public class LoggerFactory {

    private final LoggerConfiguration loggerConfig;

    public LoggerFactory(LoggerConfiguration loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    public Logger getLogger() {
        String loggerTypeToCreate = loggerConfig.getConfiguredLoggerType();

        // TODO: return appropriate logger instance according to the configured logger type
        return null;
    }

}
