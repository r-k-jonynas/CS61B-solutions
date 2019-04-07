public class ArrayDeque<T> {
    public T[] items;
    public int nextFirst;
    public int nextLast;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T []) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    /** Creating a deep copy means that you create an
     entirely new ArrayDeque, with the exact same
     items as other. */
    @SuppressWarnings("unchecked")
    public ArrayDeque(ArrayDeque<?> other) {
        items = (T []) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, size);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

    @SuppressWarnings("unchecked")
    public void resizeUp(int capacity) {
        boolean wrappedAround = !((nextFirst == 0) | (nextFirst == items.length-1));
        T[] temp = (T []) new Object[capacity];
        if (wrappedAround == true) {
            /* the front part starts at nextFirst+1 and ends at items.length -1 */
            /* so it should be inserted from index capacity / 4 to index capacity / 4 + frontLength-1*/
            int frontLength = items.length - nextFirst - 1;
            System.arraycopy(items, nextFirst+1, temp, capacity/4, frontLength);
            /* the back part starts at 0 and ends at nextLast -1 */
            /* so it should be inserted at index after front part start index + front part length*/
            System.arraycopy(items, 0, temp, capacity/4 + frontLength, nextLast);
            nextFirst = capacity / 4 - 1;
            nextLast = capacity / 4 * 3 - 1;
        } else {
            System.arraycopy(items, 0, temp, capacity/4, size);
            /* FIX INDICES HERE */
                if (nextFirst == items.length - 1) {
                    nextFirst = capacity / 4 - 1;
                    nextLast = capacity / 4 * 3 - 1;
                } else if (nextFirst == 0) {
                    nextFirst = capacity / 4;
                    nextLast = capacity / 4 * 3;
                }
        }
        items = temp;
    }

    // public void resizeDown(int capacity) {
    //     if (nextFirst == nextLast) {

    //     } else {
    //         T[] temp = (T []) new Object[capacity];
    //         System.arraycopy(items, 0, temp, 3, size);
    //         items = temp;
    //     }

    // }



    public boolean shouldResize() {
        return size == (items.length - 1);
    }

    /**  Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (shouldResize() == true) {
            resizeUp(2*items.length);
        }

        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length-1;
        } else {
            nextFirst--;
        }
        size++;
    }

    /**  Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        if (shouldResize() == true) {
            resizeUp(2*items.length);
        }

        items[nextLast] = item;
        if (nextLast + 1 == items.length) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

    /**  Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /**  Returns the number of items in the deque.*/
    public int size() {
        return size;
    }
 
    /**  Prints the items in the deque from first to last, 
        separated by a space. Once all the items have been 
        printed, print out a new line.*/

    /* NEED TO FIX */
    public void printDeque() {
        boolean wrappedAround = (nextFirst > nextLast) | (nextLast == nextFirst & (nextFirst==0 | nextFirst==items.length-1));
        // boolean wrappedAround = ((nextFirst == 0) | (nextFirst == items.length-1));
        if (wrappedAround == true) {
            // int frontLength = items.length - nextFirst - 1;
            for(int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    /**  Removes and returns the item at the front of the deque. 
         If no such item exists, returns null.*/
    public T removeFirst() {
        T temp = items[nextFirst+1];
        items[nextFirst+1] = null;
        nextFirst++;
        size--;
        return temp;
    }

    /**  Removes and returns the item at the back of the deque. 
         If no such item exists, returns null.*/
    public T removeLast() {
        T temp = items[nextLast-1];
        items[nextLast-1] = null;
        nextLast--;
        size--;
        return temp;
    }

    /**  Gets the item at the given index, where 0 is the front, 
        1 is the next item, and so forth. If no such item exists, 
        returns null. Must not alter the deque!*/
    public T get(int index) {
        return items[nextFirst + 1 + index];
    }

    // public static void testWrapAround() {
    //     ArrayDeque<Integer> test1 = new ArrayDeque<>();
    //     // test1.printDeque();
    //     test1.addFirst(1);
    //     test1.addFirst(2);
    //     test1.addLast(3);
    //     test1.addLast(4);
    //     test1.addLast(5);
    //     // test1.printDeque();
    //     test1.addLast(6);
    //     // test1.printDeque();
    //     test1.addLast(7);
    //     // test1.printDeque();
    //     test1.addLast(8);
    // }

    public static void main(String[] args) {
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        // test1.printDeque();
        test1.addFirst(1);
        test1.printDeque();
        test1.addLast(2);
        test1.printDeque();
        test1.addFirst(3);
        test1.printDeque();
        test1.addLast(4);
        test1.printDeque();
        test1.addFirst(5);
        test1.printDeque();
        test1.addLast(6);
        test1.printDeque();
        test1.addFirst(7);
        test1.printDeque();
        test1.addLast(8);
        test1.printDeque();
        System.out.println("Finished");
    }
}