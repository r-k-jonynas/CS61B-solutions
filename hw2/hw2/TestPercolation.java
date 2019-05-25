package hw2;

import org.junit.Test;
import static org.junit.Assert.*;



public class TestPercolation {
    @Test
    public void testPercolationWithNEquals1() {
        Percolation test = new Percolation(1);
        assertFalse(test.isOpen(0, 0));
        assertFalse(test.isFull(0, 0));
        assertTrue(test.percolates());
        test.open(0, 0);
        assertTrue(test.isOpen(0, 0));
        assertTrue(test.isFull(0, 0));
        assertTrue(test.percolates());
    }

    @Test
    public void testPercolationWithNEquals2() {
        Percolation test = new Percolation(2);
        assertFalse(test.isOpen(0, 0));
        assertFalse(test.isFull(0, 0));
        assertTrue(test.percolates());
        test.open(0, 0);
        assertTrue(test.isOpen(0, 0));
        assertTrue(test.isFull(0, 0));
        assertTrue(test.percolates());
    }

//    @Test
//    public void testTranslateXYto1D() {
//        Percolation perc1 = new Percolation(5);
//        assertEquals(1, perc1.translateXYto1D(0, 1));
//        assertEquals(19, perc1.translateXYto1D(3, 4));
//        assertEquals(24, perc1.translateXYto1D(4, 4));
//    }
}
