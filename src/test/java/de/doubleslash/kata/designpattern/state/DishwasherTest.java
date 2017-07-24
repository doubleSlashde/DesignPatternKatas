package de.doubleslash.kata.designpattern.state;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * TODO: Das State Pattern ausimplementieren, so dass alle Tests erfolgreich durchlaufen.
 * An dieser Testklasse sollen keine Änderungen durchgeführt werden!
 */
public class DishwasherTest {

    private static final String OFF = "off";
    private static final String ON = "on";
    private static final String WASHING = "washing";
    private static final String PAUSED = "paused";

    private Dishwasher dishwasher;

    @Before
    public void setUp() throws Exception {
        this.dishwasher = new Dishwasher();
    }

    @Test
    public void testStateOfNewDishwasherIsOff() throws Exception {
        assertDishwasherIs(OFF);
    }

    @Test
    public void testStateTransitionsFromStateOff() throws Exception {
        assertDishwasherIs(OFF);

        // keine Zustandsänderung
        assertStateTransition( () -> dishwasher.turnOff(), OFF);
        assertStateTransition( () -> dishwasher.wash(), OFF);
        assertStateTransition( () -> dishwasher.finished(), OFF);
        assertStateTransition( () -> dishwasher.openDoor(), OFF);
        assertStateTransition( () -> dishwasher.closeDoor(), OFF);

        // einschalten => an
        assertStateTransition( () -> dishwasher.turnOn(), ON);
    }

    @Test
    public void testStateTransitionsFromStateOn() throws Exception {
        ensureDishwasherIsOn();

        // keine Zustandsänderung
        assertStateTransition( () -> dishwasher.turnOn(), ON);
        assertStateTransition( () -> dishwasher.openDoor(), ON);
        assertStateTransition( () -> dishwasher.closeDoor(), ON);
        assertStateTransition( () -> dishwasher.finished(), ON);

        // Spülvorgang starten => spült
        assertStateTransition( () -> dishwasher.wash(), WASHING);

        // ausschalten => aus
        ensureDishwasherIsOn();
        assertStateTransition( () -> dishwasher.turnOff(), OFF);
    }

    @Test
    public void testStateTransitionsFromStateWashing() throws Exception {
        ensureDishwasherIsWashing();

        // keine Zustandsänderung
        assertStateTransition( () -> dishwasher.turnOn(), WASHING);
        assertStateTransition( () -> dishwasher.wash(), WASHING);
        assertStateTransition( () -> dishwasher.closeDoor(), WASHING);

        // Spülvorgang beenden => an
        assertStateTransition( () -> dishwasher.finished(), ON);

        // ausschalten => aus
        ensureDishwasherIsWashing();
        assertStateTransition( () -> dishwasher.turnOff(), OFF);

        // Tür öffnen => Pause
        ensureDishwasherIsWashing();
        assertStateTransition( () -> dishwasher.openDoor(), PAUSED);
    }

    @Test
    public void testStateTransitionsFromStatePaused() throws Exception {
        ensureDishwasherIsPaused();

        // keine Zustandsänderung
        assertStateTransition( () -> dishwasher.openDoor(), PAUSED);
        assertStateTransition( () -> dishwasher.turnOff(), PAUSED);
        assertStateTransition( () -> dishwasher.wash(), PAUSED);
        assertStateTransition( () -> dishwasher.finished(), PAUSED);
        assertStateTransition( () -> dishwasher.turnOn(), PAUSED);

        // Tür schließen => weiterspülen
        assertStateTransition( () -> dishwasher.closeDoor(), WASHING);
    }

    private void ensureDishwasherIsPaused() {
        ensureDishwasherIsWashing();
        dishwasher.openDoor();
        assertDishwasherIs(PAUSED);
    }

    private void ensureDishwasherIsWashing() {
        ensureDishwasherIsOn();
        dishwasher.wash();
        assertDishwasherIs(WASHING);
    }

    private void ensureDishwasherIsOn() {
        dishwasher = new Dishwasher();
        dishwasher.turnOn();
        assertDishwasherIs(ON);
    }

    /**
     * Prüft ob eine Aktion an der Spülmaschine den erwarteten Zustand zur Folge hat.
     *
     * @param action die ausgeführte Aktion (Lambda).
     * @param expectedState der erwartete Zustand.
     */
    private void assertStateTransition(DishwasherAction action, String expectedState) {
        action.execute();
        assertDishwasherState(expectedState);
    }

    private void assertDishwasherState(String expectedState) {
        assertDishwasherIs(expectedState);
    }

    private void assertDishwasherIs(String expectedStateName) {
        DishwasherState state = dishwasher.getState();
        assertThat(state.getStateName(), is(equalTo(expectedStateName)));
        assertThat(state.getClass().getSimpleName().toLowerCase(), containsString(expectedStateName));
    }

}

@FunctionalInterface
interface DishwasherAction {
    void execute();
}
