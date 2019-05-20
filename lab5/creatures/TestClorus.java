package creatures;
import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static huglife.Direction.TOP;
import static org.junit.Assert.*;

/** Tests the Clorus class
 *  @author Rytis
 */
public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.stay();
        assertEquals(1.96, c.energy(), 0.01);
        c.move();
        assertEquals(1.93, c.energy(), 0.01);
    }

    @Test
    /**
     * Check if replication works correctly.
     * Check that the returned Plip is not the same Plip as the Plip whose
     * replicate() method was called.
     */
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus copyOfC = c.replicate();
        assertEquals(1, copyOfC.energy(), 0);
        assertFalse(c.equals(copyOfC));
    }

    @Test
    /**
     * Check if chooseAction works correctly.
     */
    public void testChoose() {
        // TEST RULE 1:
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // TEST RULE 2:
        // Plip around, but no EMPTY. STAY.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Impassible());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topPlip);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        // Plip around. ATTACK
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlipBottomEmpty = new HashMap<Direction, Occupant>();
        topPlipBottomEmpty.put(Direction.TOP, new Plip());
        topPlipBottomEmpty.put(Direction.BOTTOM, new Empty());
        topPlipBottomEmpty.put(Direction.LEFT, new Impassible());
        topPlipBottomEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topPlipBottomEmpty);
        expected = new Action(Action.ActionType.ATTACK, TOP);
        assertEquals(expected, actual);

        // TEST RULE 3:
        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        // Energy < 1; MOVE.
        c = new Clorus(.99);

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);
    }
}
