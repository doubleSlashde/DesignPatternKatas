package de.doubleslash.kata.designpattern.factory;

import org.apache.commons.io.output.TeeOutputStream;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class LoggerFactoryTest {

    private static final String LOG_MESSAGE = "Das ist eine Test-Lognachricht";
    private static final String EMPTY_STRING = "";

    private ByteArrayOutputStream baos;

    private PrintStream originalSystemOut;

    @Before
    public void setUp() throws Exception {
        // Ausgaben nach System.out in einen ByteArrayOutputStream kopieren, damit die Ausgaben später ausgewertet
        // werden können
        baos = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(new TeeOutputStream(originalSystemOut, baos)));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalSystemOut);
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das nicht null ist und das
     * Logger-Interface implementiert.
     */
    @Test
    public void testWhenCreatingFileLoggerItIsNotNull() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("file"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(logger, is(notNullValue()));
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert und den Klassennamen FileLogger hat.
     */
    @Test
    public void testWhenCreatingFileLoggerItsClassNameIsFileLogger() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("file"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(classNameOf(logger), is(equalTo("FileLogger")));
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert, und dessen log(message)-Methode die Lognachricht gefolgt von " (in eine DB geloggt)"
     * nach System.out schreibt.
     */
    @Test
    public void testWhenLoggingToFileLoggerTheCorrectMessageIsLogged() throws Exception {

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
    public void testWhenCreatingDbLoggerItIsNotNull() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(logger, is(notNullValue()));
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert und den Klassennamen DbLogger hat.
     */
    @Test
    public void testWhenCreatingDbLoggerItsClassNameIsDbLogger() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(classNameOf(logger), is(equalTo("DbLogger")));
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert, und dessen log(message)-Methode die Lognachricht gefolgt von " (in eine DB geloggt)"
     * nach System.out schreibt.
     */
    @Test
    public void testWhenLoggingToDbLoggerTheCorrectMessageIsLogged() throws Exception {

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
    public void testWhenCreatingSilentLoggerItIsNotNull() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("silent"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(logger, is(notNullValue()));
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert und den Klassennamen SilentLogger hat.
     */
    @Test
    public void testWhenCreatingSilentLoggerItsClassNameIsSilentLogger() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("silent"));

        // Testen
        Logger logger = factory.getLogger();

        // Auswerten
        assertThat(classNameOf(logger), is(equalTo("SilentLogger")));
    }

    /**
     * Damit dieser Test erfolgreich ist, muss die LoggerFactory ein Objekt zurückliefern, das das Logger-Interface
     * implementiert, und dessen log(message)-Methode nichts loggt (d.h. nichts nach System.out schreibt).
     */
    @Test
    public void testWhenLoggingToSilentLoggerNothingIsLogged() throws Exception {

        // Vorbereiten
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("silent"));

        // Testen
        Logger logger = factory.getLogger();
        if (logger != null) {
            logger.log(LOG_MESSAGE);
        }

        // Auswerten
        assertThatNothingWasLogged();
    }

    private void assertThatMessageWasLogged(String expectedLogMessage) throws Exception {
        expectedLogMessage = expectedLogMessage.trim();
        String loggedMessage = loggedMessage().trim();
        assertThat(loggedMessage, is(equalTo(expectedLogMessage)));
    }

    private void assertThatNothingWasLogged() throws Exception {
        assertThat(loggedMessage(), is(equalTo(EMPTY_STRING)));
    }

    private String loggedMessage() throws Exception {
        return baos.toString(StandardCharsets.UTF_8.name());
    }

    private String classNameOf(Logger logger) {
        return logger == null ? "null" : logger.getClass().getSimpleName();
    }

}
