package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

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
    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(6);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        Iterator<Integer> arbWiz = arb.iterator();
        assertTrue(arbWiz.hasNext());
        assertEquals(1, (int) arbWiz.next());
    }

    @Test
    public void testIterator2() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(6);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        Iterator<Integer> arbWiz = arb.iterator();
        assertFalse(arbWiz.hasNext());
    }

    @Test
    public void testIterator3() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(6);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.dequeue();
        arb.dequeue();
        Iterator<Integer> arbWiz = arb.iterator();
        assertTrue(arbWiz.hasNext());

        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        assertTrue(arbWiz.hasNext());
        assertEquals(3, (int) arbWiz.next());
        assertEquals(4, (int) arbWiz.next());
        assertEquals(5, (int) arbWiz.next());
        assertEquals(6, (int) arbWiz.next());
        assertEquals(7, (int) arbWiz.next());
        assertEquals(8, (int) arbWiz.next());
        assertFalse(arbWiz.hasNext());
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(6);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);

        assertTrue(arb.equals(arb));
        ArrayRingBuffer<Integer> arbEmpty = new ArrayRingBuffer(6);
        assertFalse(arb.equals(arbEmpty));
        assertFalse(arbEmpty.equals(arb));

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer(6);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(3);
        assertTrue(arb.equals(arb2));
        assertTrue(arb2.equals(arb));

        ArrayRingBuffer<Integer> arb3 = new ArrayRingBuffer(6);
        arb3.enqueue(1);
        arb3.enqueue(2);
        assertFalse(arb.equals(arb3));
        assertFalse(arb3.equals(arb));
    }



}
