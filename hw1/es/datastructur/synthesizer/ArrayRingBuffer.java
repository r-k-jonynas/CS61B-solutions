package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. Least recently inserted element*/
    private int first;
    /* Index for the next enqueue. One beyond the most recently inserted item.*/
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
    }

    @Override
    public int capacity() {
        return this.rb.length;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            fillCount++;
            if ((last + 1) % this.capacity() >= 0) {
                last = (last + 1) % this.capacity();
            } else {
                last = (last + 1) % this.capacity() + this.capacity();
            }
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        rb[first] = null;
        fillCount--;
        if ((first + 1) % this.capacity() >= 0) {
            first = (first + 1) % this.capacity();
        } else {
            first = (first + 1) % this.capacity() + this.capacity();
        }
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        return temp;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new ArrayRingBufferIterator(this.first);
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        private int iterationsDone;
//        private int wizEnd;
//        private int wizArrayCap;

        public ArrayRingBufferIterator(int start) {
           this.wizPos = start;
           this.iterationsDone = 0;
        }

       @Override
       public boolean hasNext() {
            // fillCount != 0

           return iterationsDone < capacity() && rb[wizPos] != null;
       }

       @Override
       public T next() {
            T returnItem = (T) rb[wizPos];
           if ((wizPos + 1) % rb.length >= 0) {
               wizPos = (wizPos + 1) % rb.length;
           } else {
               wizPos = (wizPos + 1) %  rb.length +  rb.length;
           }
           iterationsDone++;
           return returnItem;
       }
   }

    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; } // optimization
        if (this.getClass() != o.getClass()) { return false; }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        java.util.Iterator oWizard = other.iterator();
        if (this.fillCount() != other.fillCount()
            || this.capacity() != other.capacity()) { return false; }
            for (T item : this) {
                if (!item.equals(oWizard.next()))
                    return false;
            }
        return true;

    }

}
