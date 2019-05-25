package hw2;

import org.junit.Test;
import static org.junit.Assert.*;



public class TestPercolation {
    @Test
    public void testPercolationWithNEquals1() {
        Percolation test = new Percolation(1);
        assertFalse(test.isOpen(0, 0));
        assertFalse(test.isFull(0, 0));
        assertEquals(false, test.percolates());
        test.open(0, 0);
        assertTrue(test.isOpen(0, 0));
        assertTrue(test.isFull(0, 0));
        assertTrue(test.percolates());
    }

    @Test
    public void testPercolationWithNEquals2() {
        Percolation test = new Percolation(2);
        assertFalse(test.percolates());
        test.open(0, 0);
        assertFalse(test.percolates());
        test.open(0, 1);
        assertFalse(test.percolates());
    }

    @Test
    public void testBackwash() {
        Percolation test = new Percolation(10);
        test.open(0, 6);
        test.open(1, 6);
        test.open(2, 6);
        test.open(3, 6);
        test.open(4, 6);
        test.open(5, 6);
        test.open(6, 6);
        test.open(7, 6);
        test.open(8, 6);
        test.open(9, 6);
        assertTrue(test.percolates());
        test.open(9, 1);
        assertEquals(false, test.isFull(9, 1));

    }

//    @Test
//    public void testTranslateXYto1D() {
//        Percolation perc1 = new Percolation(5);
//        assertEquals(1, perc1.translateXYto1D(0, 1));
//        assertEquals(19, perc1.translateXYto1D(3, 4));
//        assertEquals(24, perc1.translateXYto1D(4, 4));
//    }
}
