import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testBasics() {
        UnionFind u1 = new UnionFind(5);
        assertEquals(-1, u1.parent[0]);
        assertEquals(-1, u1.parent[2]);
        assertEquals(-1, u1.parent[4]);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testValidate1() {
//        UnionFind u1 = new UnionFind(5);
//        u1.validate(-1);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void testValidate2() {
//        UnionFind u1 = new UnionFind(5);
//        u1.validate(5);
//    }

    @Test
    public void testSizeOf() {
        UnionFind u1 = new UnionFind(5);
        assertEquals(1, u1.sizeOf(1));

        u1.union(1, 2);
        assertEquals(2, u1.sizeOf(1));
        assertEquals(2, u1.sizeOf(2));
        assertEquals(1, u1.sizeOf(0));

        u1.union(3, 4);
        assertEquals(2, u1.sizeOf(3));
        assertEquals(2, u1.sizeOf(4));
        assertEquals(2, u1.sizeOf(2));

        u1.union(2, 0);
        assertEquals(3, u1.sizeOf(0));
        assertEquals(2, u1.sizeOf(3));
        assertEquals(2, u1.sizeOf(4));
        assertEquals(3, u1.sizeOf(2));

        u1.union(2, 4);
        assertEquals(5, u1.sizeOf(4));
        assertEquals(5, u1.sizeOf(2));
    }

    @Test
    public void testParent() {
        UnionFind u1 = new UnionFind(5);
        assertEquals(-1, u1.parent(0));
        u1.union(1, 2);
        assertEquals(2, u1.parent(1));
        assertEquals(-2, u1.parent(2));
        u1.union(2, 0);
        assertEquals(2, u1.parent(0));
        assertEquals(-3, u1.parent(2));
    }

    @Test
    public void testConnected() {
        UnionFind u1 = new UnionFind(5);
        assertTrue(u1.connected(0, 0));
        assertFalse(u1.connected(0, 1));
        assertFalse(u1.connected(2, 1));

        u1.union(1, 2);
        assertTrue(u1.connected(2, 1));
        assertTrue(u1.connected(1, 1));
        assertTrue(u1.connected(1, 2));

        u1.union(2, 0);
        assertTrue(u1.connected(2, 0));
        assertTrue(u1.connected(0, 1));
        assertFalse(u1.connected(1, 4));
    }

    @Test
    public void testUnion() {
        UnionFind u1 = new UnionFind(5);
        u1.union(1, 2);
        assertEquals(2, u1.parent[1]);
        assertEquals(-2, u1.parent[2]);

        u1.union(3, 4);
        assertEquals(4, u1.parent[3]);
        assertEquals(-2, u1.parent[4]);

        u1.union(2, 0);
        assertEquals(2, u1.parent[0]);
        assertEquals(-3, u1.parent[2]);
    }

    @Test
    public void testFind() {
        // TODO: finish

        UnionFind u1 = new UnionFind(5);
        assertEquals(1, u1.find(1));

        u1.union(1, 2);
        assertEquals(2, u1.find(1));
        assertEquals(2, u1.find(2));
        assertEquals(0, u1.find(0));

        u1.union(3, 4);
        assertEquals(4, u1.find(3));
        assertEquals(4, u1.find(4));
        assertEquals(2, u1.find(2));

        u1.union(2, 0);
        assertEquals(2, u1.find(0));
        assertEquals(4, u1.find(3));
        assertEquals(4, u1.find(4));
        assertEquals(2, u1.find(2));

        u1.union(2, 4);
        assertEquals(2, u1.find(0));
        assertEquals(2, u1.find(3));
        assertEquals(2, u1.find(4));
        assertEquals(2, u1.find(2));
    }

    @Test
    public void testWorstCase() {
        UnionFind u1 = new UnionFind(8);
        u1.union(1, 0);
        u1.union(3, 2);
        u1.union(3, 1);
    }
}
