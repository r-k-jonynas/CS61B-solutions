public class LinkedListDeque<T> implements Deque<T> {
    private class GenericNode {
        private GenericNode prev;
        private T item;
        private GenericNode next;

        private GenericNode(GenericNode p, T i, GenericNode n) {
            prev = p;
            item = i;
            next = n;
        }

        private T traverseGenericNode(int depth) { 
            if (depth == 0) {
                return this.item;
            }
            return (this.next).traverseGenericNode(depth - 1);
        }
    }

    private GenericNode sentFront;
    private GenericNode sentBack;;
    private int size;

    public LinkedListDeque() {
        sentFront = new GenericNode(null, null, null);
        sentBack = new GenericNode(sentFront, null, null);
        sentFront.next = sentBack;
    }

    /** Creating a deep copy means that you create an
     entirely new LinkedListDeque, with the exact same
     items as other. */
    public LinkedListDeque(LinkedListDeque<T> other) {
        GenericNode temp = other.sentFront;
        GenericNode tempForCopy = new GenericNode(temp.prev, temp.item, null);
        sentFront = tempForCopy;
        while (temp != other.sentBack) {
            temp = temp.next;
            tempForCopy.next = new GenericNode(tempForCopy, temp.item, null);
            tempForCopy = tempForCopy.next;
        }
        sentBack = tempForCopy;
        size = other.size;
    }

    /**  Adds an item of type T to the front of the deque.*/
    @Override
    public void addFirst(T item) {
        sentFront.next = new GenericNode(sentFront, item, sentFront.next);
        sentFront.next.next.prev = sentFront.next;
        size += 1;
    }

    /**  Adds an item of type T to the back of the deque.*/
    @Override
    public void addLast(T item) {
        sentBack.prev = new GenericNode(sentBack.prev, item, sentBack);
        sentBack.prev.prev.next = sentBack.prev;
        size += 1;
    }

    /**  Returns the number of items in the deque.*/
    @Override
    public int size() {
        return this.size;
    }
 
    /**  Prints the items in the deque from first to last, 
        separated by a space. Once all the items have been 
        printed, print out a new line.*/
    @Override
    public void printDeque() {
        GenericNode temp = sentFront.next;
        while (temp != sentBack) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**  Removes and returns the item at the front of the deque. 
         If no such item exists, returns null.*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T temp = sentFront.next.item;
            sentFront.next = sentFront.next.next;
            sentFront.next.prev = sentFront;
            this.size -= 1;
            return temp;
        }
    }

    /**  Removes and returns the item at the back of the deque. 
         If no such item exists, returns null.*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T temp = sentBack.prev.item;
            sentBack.prev = sentBack.prev.prev;
            sentBack.prev.next = sentBack;
            this.size -= 1;
            return temp;
        }
    }

    /**  Gets the item at the given index, where 0 is the front, 
        1 is the next item, and so forth. If no such item exists, 
        returns null. Must not alter the deque!*/
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        } else {
            GenericNode temp = this.sentFront.next; 
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.item;
        }
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        GenericNode temp = this.sentFront.next;
        return temp.traverseGenericNode(index);
    }
}
