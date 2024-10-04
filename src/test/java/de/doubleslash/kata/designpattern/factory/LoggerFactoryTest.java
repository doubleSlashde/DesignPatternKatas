package de.doubleslash.kata.designpattern.factory;

import org.apache.commons.io.output.TeeOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * TODO:
 * <ul>
 *     <li>Die {@link Disabled}-Annotation über der Klassendeklaration entfernen</li>
 *     <li>Das Factory Method Pattern ausimplementieren, so dass alle Tests erfolgreich durchlaufen.</li>
 * </ul>
 *
 * Außer dem Entfernen der Annotation sollen in dieser Klasse keine Änderungen durchgeführt werden!
 */
@Disabled
class LoggerFactoryTest {

    private static final String LOG_MESSAGE = "Das ist eine Test-Lognachricht";

    private ByteArrayOutputStream baos;

    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        // Ausgaben nach System.out in einen ByteArrayOutputStream kopieren, damit die Ausgaben später ausgewertet
        // werden können
        baos = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(new TeeOutputStream(originalSystemOut, baos)));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das nicht null ist und das
     * Logger-Interface implementiert.
     */
    @Test
    void testWhenCreatingFileLoggerItIsNotNull() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("file"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(logger).isNotNull();
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert und den Klassennamen FileLogger hat.
     */
    @Test
    void testWhenCreatingFileLoggerItsClassNameIsFileLogger() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("file"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(classNameOf(logger)).isEqualTo("FileLogger");
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert, und dessen log(message)-Methode die Lognachricht gefolgt von " (in eine DB geloggt)"
     * nach System.out schreibt.
     */
    @Test
    void testWhenLoggingToFileLoggerTheCorrectMessageIsLogged() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("file"));

        // Testen
        Logger logger = factory.getLogger();
        if (logger != null) {
            logger.log(LOG_MESSAGE);
        }

        // Auswerten
        assertThatMessageWasLogged(LOG_MESSAGE + " (in eine Datei geloggt)");
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das nicht null ist und das
     * Logger-Interface implementiert.
     */
    @Test
    void testWhenCreatingDbLoggerItIsNotNull() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(logger).isNotNull();
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert und den Klassennamen DbLogger hat.
     */
    @Test
    void testWhenCreatingDbLoggerItsClassNameIsDbLogger() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(classNameOf(logger)).isEqualTo("DbLogger");
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert, und dessen log(message)-Methode die Lognachricht gefolgt von " (in eine DB geloggt)"
     * nach System.out schreibt.
     */
    @Test
    void testWhenLoggingToDbLoggerTheCorrectMessageIsLogged() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // Testen
        Logger logger = factory.getLogger();
        if (logger != null) {
            logger.log(LOG_MESSAGE);
        }

        // Auswerten
        assertThatMessageWasLogged(LOG_MESSAGE + " (in eine DB geloggt)");
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das nicht null ist und das
     * Logger-Interface implementiert.
     */
    @Test
    void testWhenCreatingSilentLoggerItIsNotNull() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("silent"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(logger).isNotNull();
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert und den Klassennamen SilentLogger hat.
     */
    @Test
    void testWhenCreatingSilentLoggerItsClassNameIsSilentLogger() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("silent"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(classNameOf(logger)).isEqualTo("SilentLogger");
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert, und dessen log(message)-Methode nichts loggt (d.h. nichts nach System.out schreibt).
     */
    @Test
    void testWhenLoggingToSilentLoggerNothingIsLogged() {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("silent"));

        // Testen
        Logger logger = factory.getLogger();
        logger.log(LOG_MESSAGE);

        // Auswerten
        assertThatNothingWasLogged();
    }

    private void assertThatMessageWasLogged(String expectedLogMessage) {
        expectedLogMessage = expectedLogMessage.trim();
        String loggedMessage = loggedMessage().trim();
        assertThat(loggedMessage).isEqualTo(expectedLogMessage);
    }

    private void assertThatNothingWasLogged() {
        assertThat(loggedMessage()).isEmpty();
    }

    private String loggedMessage() {
        return baos.toString(StandardCharsets.UTF_8);
    }

    private String classNameOf(Logger logger) {
        return logger == null ? "null" : logger.getClass().getSimpleName();
    }

}
