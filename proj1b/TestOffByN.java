import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByN(1);
    static CharacterComparator offByTwo = new OffByN(2);
    static CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void testOffByNOne1() {
        assertFalse(offByOne.equalChars('a', 'a'));
    }

    @Test
    public void testOffByNOne2() {
        assertTrue(offByOne.equalChars('a', 'b'));
    }

    @Test
    public void testOffByNOne3() {
        assertTrue(offByOne.equalChars('b', 'a'));
    }

    @Test
    public void testOffByNOne4() {
        assertTrue(offByOne.equalChars('&', '%'));
    }
    @Test
    public void testOffByNTwo1() {
        assertTrue(offByTwo.equalChars('a', 'c'));
    }

    @Test
    public void testOffByNTwo2() {
        assertTrue(offByTwo.equalChars('d', 'b'));
    }

    @Test
    public void testOffByTwo3() {
        assertFalse(offByTwo.equalChars('b', 'a'));
    }

    @Test
    public void testOffBy5() {
        assertFalse(offByTwo.equalChars('b', 'a'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('f', 'h'));
    }
}
