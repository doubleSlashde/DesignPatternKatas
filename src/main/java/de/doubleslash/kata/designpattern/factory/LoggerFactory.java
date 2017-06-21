package de.doubleslash.kata.designpattern.factory;

import static java.lang.String.format;

public class LoggerFactory {

    private final LoggerConfiguration loggerConfig;

    public LoggerFactory(LoggerConfiguration loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    /**
     * Gibt je nach Konfiguration eine entsprechende Logger-Instanz zurück.
     *
     * @return eine Logger-Instanz gemäß Konfiguration.
     */
    public Logger getLogger() {
        String loggerTypeToCreate = loggerConfig.getConfiguredLoggerType();

        // TODO: eine dem konfigurierten Logger-Typ entsprechende Logger-Implementierung zurückliefern
        return null;
    }

}
