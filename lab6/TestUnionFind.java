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

//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testValidate1() {
//        UnionFind u1 = new UnionFind(5);
//        u1.validate(-1);
//    }
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testValidate2() {
//        UnionFind u1 = new UnionFind(5);
//        u1.validate(5);
//    }

    @Test
    public void testSizeOf() {

    }

    @Test
    public void testParent() {

    }

    @Test
    public void testConnected() {

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

    }
}
