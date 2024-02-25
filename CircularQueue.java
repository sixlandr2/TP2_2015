public class CircularQueue implements QueueInterface {

    private static final int maxCapacity = 100;
    private int front;
    private int storedSize;
    private int[] array;

    public CircularQueue(final int capacity) {
        array = new int[capacity];
    }

    @Override
    public int size() {
        return this.storedSize;
    }

    @Override
    public boolean isEmpty() {
        return this.storedSize == 0;
    }

    public boolean isFull() {
        return this.storedSize == this.array.length;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[front];
    }

    public Integer first(){
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    public int rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[(front + storedSize - 1) % array.length];
    }

    @Override
    public void enqueue(int e) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (this.front + this.storedSize) % array.length;
        array[avail] = e;
        this.storedSize++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int element = first();
        array[front] = 0; // Assuming 0 represents an empty slot
        this.front = (this.front + 1) % array.length;
        this.storedSize--;
        return element;
    }

    public boolean checkInQueue(int value) {
        for (int i = 0; i < storedSize; i++) {
            if (array[(front + i) % array.length] == value) {
                return true;
            }
        }
        return false;
    }

    public void remove(int value) {
        for (int i = 0; i < storedSize; i++) {
            if (array[(front + i) % array.length] == value) {
                int indexToRemove = (front + i) % array.length;
                System.arraycopy(array, indexToRemove + 1, array, indexToRemove, storedSize - i - 1);
                array[(front + storedSize - 1) % array.length] = 0;
                storedSize--;
                return;
            }
        }
    }
}








