public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
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
    public ArrayDeque(ArrayDeque other) {
        items = (T []) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

    @SuppressWarnings("unchecked")
    private void resizeUp() {
        int newCapacity = items.length * 2;
        int posFirstElement;
        if ((nextFirst + 1) % items.length >= 0) {
            posFirstElement = (nextFirst + 1) % items.length;
        } else {
            posFirstElement = (nextFirst + 1) % items.length + items.length;
        }
       
        int numOfElIn1stCopy = items.length - posFirstElement;
        int numOfElIn2ndCopy = items.length - numOfElIn1stCopy;
        
        T[] temp = (T []) new Object[newCapacity];
        
        System.arraycopy(items, posFirstElement, temp, newCapacity / 4, numOfElIn1stCopy);
        System.arraycopy(items, 0, temp, newCapacity / 4 + numOfElIn1stCopy, numOfElIn2ndCopy);
        
        this.items = temp;
        nextFirst = newCapacity / 4 - 1;
        nextLast = newCapacity / 4 * 3;
    }

    @SuppressWarnings("unchecked")
    private void resizeDown() {
        int newCapacity = items.length / 2;
        int posFirstElement;
        int posLastElement;

        /* Find out the position of 1st element */
        if ((nextFirst + 1) % items.length >= 0) {
            posFirstElement = (nextFirst + 1) % items.length;
        } else {
            posFirstElement = (nextFirst + 1) % items.length + items.length;
        }
        /* Find out the position of last element */
        if ((nextLast - 1) % items.length >= 0) {
            posLastElement = (nextLast - 1) % items.length;
        } else {
            posLastElement = (nextLast - 1) % items.length + items.length;
        }

        T[] temp = (T []) new Object[newCapacity];

        /* Check if not wrapped around */
        if (posLastElement > posFirstElement) {
            int numOfElIn1stCopy = size;
            System.arraycopy(items, posFirstElement, temp, newCapacity / 4, numOfElIn1stCopy);
        } else {
            int numOfElIn1stCopy = items.length - posFirstElement;
            int numOfElIn2ndCopy = size - numOfElIn1stCopy;

            System.arraycopy(items, posFirstElement, temp, newCapacity / 4, numOfElIn1stCopy);
            System.arraycopy(items, 0, temp, newCapacity / 4 + numOfElIn1stCopy, numOfElIn2ndCopy);
        }

        this.items = temp;
        nextFirst = newCapacity / 4 - 1;
        nextLast = newCapacity / 4 * 3;
    }

    /**  Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (this.size == this.items.length) {
            resizeUp();
            this.addFirst(item);
        } else {
            this.items[nextFirst] = item;
            if ((nextFirst - 1) % items.length >= 0) {
                nextFirst = (nextFirst - 1) % items.length;
            } else {
                nextFirst = (nextFirst - 1) % items.length + items.length;
            }
            size++;
        }
    }

    /**  Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        if (this.size == this.items.length) {
            resizeUp();
            this.addLast(item);
        } else {
            this.items[nextLast] = item;
            if ((nextLast + 1) % items.length >= 0) {
                nextLast = (nextLast + 1) % items.length;
            } else {
                nextLast = (nextLast + 1) % items.length + items.length;
            }
            size++;
        }
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
        int counter = size;
        int i = nextFirst;
        while (counter > 0) {
            i = (i + 1) % items.length;
            System.out.print(items[i] + " ");
            counter--;
        }
        System.out.println();
    }

    /**  Removes and returns the item at the front of the deque. 
         If no such item exists, returns null.*/
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        } else if (this.size - 1 < this.items.length / 4 && items.length > 15) {
            resizeDown();
            return this.removeFirst();
        } else {
            if ((nextFirst + 1) % items.length >= 0) {
                nextFirst = (nextFirst + 1) % items.length;
            } else {
                nextFirst = (nextFirst + 1) % items.length + items.length;
            }
            T temp = items[nextFirst];
            items[nextFirst] = null;
            size--;
            return temp;
        }
    }

    /**  Removes and returns the item at the back of the deque. 
         If no such item exists, returns null.*/
    public T removeLast() {
        if (this.size == 0) {
            return null;
        } else if (this.size - 1 < this.items.length / 4 && items.length > 15) {
            resizeDown();
            return this.removeLast();
        } else {
            if ((nextLast - 1) % items.length >= 0) {
                nextLast = (nextLast - 1) % items.length;
            } else {
                nextLast = (nextLast - 1) % items.length + items.length;
            }
            T temp = items[nextLast];
            items[nextLast] = null;
            size--;
            return temp;
        }
    }

    /**  Gets the item at the given index, where 0 is the front, 
        1 is the next item, and so forth. If no such item exists, 
        returns null. Must not alter the deque!*/
    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        int pos;
        if ((nextFirst + 1 + index) % items.length >= 0) {
            pos = (nextFirst + 1 + index) % items.length;
        } else {
            pos = (nextFirst + 1 + index) % items.length + items.length;
        }

        return items[pos];
    }
}
