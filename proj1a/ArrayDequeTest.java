/** Performs some basic linked list tests. */
public class ArrayDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out nextFirst checks. */
	public static boolean checkNextFirst(int expected, int actual) {
		if (expected != actual) {
			System.out.println("nextFirst returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}
	/* Utility method for printing out nextLast checks. */
	public static boolean checkNextLast(int expected, int actual) {
		if (expected != actual) {
			System.out.println("nextLast returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out get checks. */
	public static boolean checkGet(Object expected, Object actual) {
		if (expected != actual) {
			System.out.println("get returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}
	

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checks isEmpty() and size() are correct, 
	  * finally prints the results.
	  */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
	}

	public static void lastFirstPointersTest() {

		System.out.println("Running LastFirstPointersT test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		boolean passed = true;

		lld1.addFirst(10);
		passed = checkNextFirst(2, lld1.nextFirst) && passed;
		passed = checkNextLast(4, lld1.nextLast) && passed;

		lld1.addLast(13);
		passed = checkNextFirst(2, lld1.nextFirst) && passed;
		passed = checkNextLast(5, lld1.nextLast) && passed;

		lld1.addLast(14);
		passed = checkNextFirst(2, lld1.nextFirst) && passed;
		passed = checkNextLast(6, lld1.nextLast) && passed;
		

		lld1.addLast(15);
		passed = checkNextFirst(2, lld1.nextFirst) && passed;
		passed = checkNextLast(7, lld1.nextLast) && passed;

		printTestStatus(passed);
	}

	public static void getTest1() {

		System.out.println("Running getTest test No.1.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		boolean passed = true;

		lld1.addFirst(10);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(null, lld1.get(1)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;
		
		lld1.addLast(13);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(13, lld1.get(1)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;
		
		lld1.addLast(14);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(13, lld1.get(1)) && passed;
		passed = checkGet(14, lld1.get(2)) && passed;
		passed = checkGet(null, lld1.get(3)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;


		printTestStatus(passed);
	}

	public static void getTest2() {

		System.out.println("Running getTest test No.2.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		boolean passed = true;

		lld1.addLast(10);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(null, lld1.get(1)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;
		
		lld1.addLast(13);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(13, lld1.get(1)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;
		
		lld1.addLast(14);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(13, lld1.get(1)) && passed;
		passed = checkGet(14, lld1.get(2)) && passed;
		passed = checkGet(null, lld1.get(3)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;


		printTestStatus(passed);
	}
	

	public static void getTest3() {

		System.out.println("Running getTest test No.3.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		boolean passed = true;

		lld1.addLast(10);		
		lld1.addLast(13);
		lld1.addLast(14);
		lld1.addLast(15);
		lld1.addLast(16);
		lld1.addLast(17);
		lld1.addLast(18);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(13, lld1.get(1)) && passed;
		passed = checkGet(14, lld1.get(2)) && passed;
		passed = checkGet(15, lld1.get(3)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;
		
		passed = checkGet(16, lld1.get(4)) && passed;
		passed = checkGet(17, lld1.get(5)) && passed;
		passed = checkGet(18, lld1.get(6)) && passed;
		passed = checkGet(null, lld1.get(7)) && passed;

		printTestStatus(passed);
	}

	public static void getTestDeepCopy() {

		System.out.println("Running getTestDeepCopy test.");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		boolean passed = true;

		lld1.addLast(10);		
		lld1.addLast(13);
		lld1.addLast(14);
		lld1.addLast(15);
		lld1.addLast(16);
		lld1.addLast(17);
		lld1.addLast(18);
		passed = checkGet(10, lld1.get(0)) && passed;
		passed = checkGet(13, lld1.get(1)) && passed;
		passed = checkGet(14, lld1.get(2)) && passed;
		passed = checkGet(15, lld1.get(3)) && passed;
		passed = checkGet(null, lld1.get(-1)) && passed;
		
		passed = checkGet(16, lld1.get(4)) && passed;
		passed = checkGet(17, lld1.get(5)) && passed;
		passed = checkGet(18, lld1.get(6)) && passed;
		passed = checkGet(null, lld1.get(7)) && passed;

		ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>(lld1);

		passed = checkGet(10, lld2.get(0)) && passed;
		passed = checkGet(13, lld2.get(1)) && passed;
		passed = checkGet(14, lld2.get(2)) && passed;
		passed = checkGet(15, lld2.get(3)) && passed;
		passed = checkGet(null, lld2.get(-1)) && passed;
		
		passed = checkGet(16, lld2.get(4)) && passed;
		passed = checkGet(17, lld2.get(5)) && passed;
		passed = checkGet(18, lld2.get(6)) && passed;
		passed = checkGet(null, lld2.get(7)) && passed;

		printTestStatus(passed);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		lastFirstPointersTest();
		getTest1();
		getTest2();
		getTest3();
		getTestDeepCopy();
	}
} 