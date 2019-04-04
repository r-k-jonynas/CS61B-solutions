public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T []) new Object[50];
        nextFirst = 22;
        nextLast = 23;
        size = 0;
    }

    /** Creating a deep copy means that you create an
     entirely new ArrayDeque, with the exact same
     items as other. */
    @SuppressWarnings("unchecked")
    public ArrayDeque(ArrayDeque other) {
        items = (T []) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, size);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

    /**  Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst--;
        size++;
    }

    /**  Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast++;
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
    public void printDeque() {
        for (int i = nextFirst + 1; i < nextLast; i++) {
            System.out.print(items[i] + " ");
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

    public static void main(String[] args) {
        ArrayDeque<String> test1 = new ArrayDeque<>();
        test1.printDeque();
        test1.addFirst("One");
        test1.addFirst("Two");
        test1.addLast("Three");
        test1.printDeque();
    }
}