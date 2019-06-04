import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;

/** Tests by Brendan Hu, Spring 2015, revised for 2016 by Josh Hug */
public class TestBSTMap {

    @Test
    public void iteratorTest() {
        try {
            BSTMap<Integer, Integer> a = new BSTMap<>();
            for (int i = 0; i < 455; i++) {
                a.put(i, 1+i);
            }
            Iterator<Integer> seer = a.iterator();
            while (seer.hasNext()) {
                System.out.println(seer.next());
            }
        } catch (Exception e) {
            fail();
        }
    }

    /* MY TESTS @RKJ*/

    @Test
    public void testRemove1() {
        BSTMap<Integer,String> q = new BSTMap<>();
        q.put(5,"a");
        q.put(2,"b");
        q.put(1,"c");
        q.put(4,"d");
        q.put(6,"e");

        assertEquals("e", q.remove(6));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(2));
        assertTrue(q.containsKey(4));
        assertTrue(q.containsKey(5));
        assertEquals(false, q.containsKey(6));


        assertEquals("d", q.remove(4));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(2));
        assertTrue(q.containsKey(5));
        assertEquals(false, q.containsKey(6));
        assertEquals(false, q.containsKey(4));
    }


    @Test
    public void testRemove2() {
        BSTMap<Integer,String> q = new BSTMap<>();
        q.put(5,"a");
        q.put(2,"b");
        q.put(1,"c");
        q.put(4,"d");
        q.put(6,"e");
        q.put(7,"f");
        q.put(3,"z");

        assertEquals("e", q.remove(6));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(2));
        assertTrue(q.containsKey(3));
        assertTrue(q.containsKey(4));
        assertTrue(q.containsKey(5));
        assertTrue(q.containsKey(7));
        assertFalse(q.containsKey(6));

        assertEquals("d", q.remove(4));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(2));
        assertTrue(q.containsKey(3));
        assertTrue(q.containsKey(5));
        assertTrue(q.containsKey(7));
        assertFalse(q.containsKey(6));
        assertFalse(q.containsKey(4));
    }

    @Test
    public void testRemove3() {
        BSTMap<Integer,String> q = new BSTMap<>();
        q.put(5,"a");
        q.put(2,"b");
        q.put(1,"c");
        q.put(4,"d");
        q.put(6,"e");
        q.put(7,"f");
        q.put(3,"z");

        assertEquals("b", q.remove(2));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(6));
        assertTrue(q.containsKey(3));
        assertTrue(q.containsKey(4));
        assertTrue(q.containsKey(5));
        assertTrue(q.containsKey(7));
        // Make sure that it is 1 3 (subroot) 2
        assertFalse(q.containsKey(2));

        assertEquals("z", q.remove(3));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(4));
        assertTrue(q.containsKey(6));
        assertTrue(q.containsKey(5));
        assertTrue(q.containsKey(7));
        assertFalse(q.containsKey(3));
        assertFalse(q.containsKey(2));
    }

    @Test
    public void testRemoveRoot() {
        BSTMap<Integer,String> q = new BSTMap<>();
        q.put(5,"a");
        q.put(2,"b");
        q.put(1,"c");
        q.put(4,"d");
        q.put(6,"e");
        q.put(7,"f");
        q.put(3,"z");

        assertEquals("a", q.remove(5));
        assertTrue(q.containsKey(1));
        assertTrue(q.containsKey(2));
        assertTrue(q.containsKey(3));
        assertTrue(q.containsKey(4));
        assertTrue(q.containsKey(6));
        assertTrue(q.containsKey(7));
        assertFalse(q.containsKey(5));
    }

    @Test
    public void testRemoveThreeCases() {
        BSTMap<String,String> q = new BSTMap<String,String>();
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a");                         // a b c d e
        assertEquals("a", q.remove("e"));      // a b c d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("c"));
        assertTrue(q.containsKey("d"));
        assertEquals("a", q.remove("c"));      // a b d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        q.put("f","a");                         // a b d f
        assertTrue(null != q.remove("d"));      // a b f
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("f"));
    }

    /* MY OWN TESTS END @RKJ*/

	@Test
    public void sanityGenericsTest() {
    	try {
    		BSTMap<String, String> a = new BSTMap<String, String>();
	    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
	    	BSTMap<Integer, String> c = new BSTMap<Integer, String>();
	    	BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
	    } catch (Exception e) {
	    	fail();
	    }
    }

    //assumes put/size/containsKey/get work
	@Test
    public void sanityClearTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1+i);
            //make sure put is working via containsKey and get
            assertTrue( null != b.get("hi" + i) && (b.get("hi"+i).equals(1+i))
                        && b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null,b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null,b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++)
            b.put("hi" + i, 1);
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
