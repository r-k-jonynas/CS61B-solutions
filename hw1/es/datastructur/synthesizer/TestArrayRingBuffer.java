package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testConstructor() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        assertEquals(0, arb.first);
        assertEquals(0, arb.last);
        assertEquals(0, arb.fillCount);
    }

    @Test
    public void testBasics() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());

        arb.enqueue(1);
        assertEquals(0, arb.first);
        assertEquals(1, arb.last);
        assertEquals(1, arb.fillCount);

        int temp = arb.peek();
        assertEquals(1, temp);

        arb.enqueue(2);
        assertEquals(0, arb.first);
        assertEquals(2, arb.last);
        assertEquals(2, arb.fillCount);

        temp = arb.dequeue();
        assertEquals(1, arb.first);
        assertEquals(2, arb.last);
        assertEquals(1, arb.fillCount);
        assertEquals(1, temp);

        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        assertEquals(1, arb.first);
        assertEquals(0, arb.last);
        assertEquals(9, arb.fillCount);

        arb.enqueue(11);
        assertFalse(arb.isEmpty());
        assertTrue(arb.isFull());
        assertEquals(1, arb.first);
        assertEquals(1, arb.last);
        assertEquals(10, arb.fillCount);

    }


}
