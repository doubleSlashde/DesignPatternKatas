package de.doubleslash.kata.designpattern.factory;

import org.apache.commons.io.output.TeeOutputStream;
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

    private static final String LOG_MESSAGE = "This is a test log message";

    private ByteArrayOutputStream baos;

    @Before
    public void setUp() throws Exception {
        // copy output to System.out into a ByteArrayOutputStream from which we can
        baos = new ByteArrayOutputStream();
        PrintStream orig = System.out;
        System.setOut(new PrintStream(new TeeOutputStream(orig, baos)));
    }

    /**
     * For this test to succeed, the LoggerFactory must return an object that implements the Logger interface and is
     * not null.
     */
    @Test
    public void testWhenCreatingDbLoggerItIsNotNull() throws Exception {

        // arrange
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // act
        Logger logger = factory.getLogger();

        // assert
        assertThat(logger, is(notNullValue()));
    }

    /**
     * For this test to succeed,  the LoggerFactory must return an object that implements the Logger interface whose
     * class name is "DbLogger".
     *
     */
    @Test
    public void testWhenCreatingDbLoggerItsClassNameIsDbLogger() throws Exception {

        // arrange
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // act
        Logger logger = factory.getLogger();

        // assert
        assertThat(classNameOf(logger), is(equalTo("DbLogger")));
    }

    /**
     * For this test to succeed, the LoggerFactory must return an object that implements the Logger interface, and whose
     * log(String logMessage) method writes the logMessage to System.out with the Suffix " (logged into DB)".
     */
    @Test
    public void testWhenLoggingToDbLoggerTheCorrectMessageIsLogged() throws Exception {

        // arrange
        LoggerFactory factory = new LoggerFactory(new LoggerConfiguration("db"));

        // act
        Logger logger = factory.getLogger();
        if (logger != null) {
            logger.log(LOG_MESSAGE);
        }

        // assert
        assertThatMessageWasLogged(LOG_MESSAGE + " (logged into DB)");
    }

    private void assertThatMessageWasLogged(String expectedLogMessage) throws Exception {
        expectedLogMessage = expectedLogMessage.trim();
        String loggedMessage = loggedMessage().trim();
        assertThat(loggedMessage, is(equalTo(expectedLogMessage)));
    }

    private String loggedMessage() throws Exception {
        return baos.toString(StandardCharsets.UTF_8.name());
    }

    private String classNameOf(Logger logger) {
        return logger == null ? "null" : logger.getClass().getSimpleName();
    }

}