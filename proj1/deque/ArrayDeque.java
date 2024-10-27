package deque;

import edu.princeton.cs.algs4.StdOut;
import org.checkerframework.framework.qual.SubtypeOf;

import java.util.Iterator;

//To make the arrayDeque more standard in being iterable,
//we need to implement Iterable<T>
//So the class should find a way to return an instance of Iterator
//So first, we will add implements iterable for the class
//Second, we will create a class that implements the iterator and override its methods
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int capacity = 8;
    private int size;
    private int front;
    private int rear;

    private class ArrayDequeIterator implements Iterator<T>{
        int curPos;
        int count;

        public ArrayDequeIterator(){
            curPos = front;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (curPos == capacity)
                curPos = 0;
            T item = items[curPos++];
            ++count;
            return item;
        }
    }

    //A constructor to create an empty deque
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    //A method to increase the array when it becomes full
    //When our array is full, we want to create a new array with extra capacity
    //and add the items to it, then copy the created array to our items array
    private void resizeUp() {
        capacity *= 2;
        T[] temp = items;
        items = (T[]) new Object[capacity];
        int i = 0;
        int idx = front;
        while (i != size) {
            if (idx == temp.length)
                idx = 0;
            items[i++] = temp[idx++];
        }
        front = 0;
        rear = size;
    }

    //A method to decrease the array when the usage factor is under 0.25
    private void resizeDown() {
        capacity /= 2;
        T[] temp = items;
        items = (T[]) new Object[capacity];
        int i = 0;
        int idx = front;
        while (i != size) {
            if (idx == temp.length)
                idx = 0;
            items[i++] = temp[idx++];
        }
        front = 0;
        rear = size;
    }

    //A method to add an item from the front
    @Override
    public void addFirst(T item) {
        if (size == capacity)
            resizeUp();

        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        ++size;
    }

    //A method to add an item from the back
    @Override
    public void addLast(T item) {
        if (size == capacity)
            resizeUp();

        items[rear] = item;
        rear = (rear + 1) % items.length;
        ++size;
    }

    //A method to remove the first item in constant time and return its value
    @Override
    public T removeFirst() {
        if (size == 0)
            return null;

        double usageFactor = (double) (size - 1) / items.length;
        if (usageFactor < 0.25 && items.length >= 16)
            resizeDown();

        T first = items[front];
        items[front] = null;
        front = (front + 1) % capacity;
        --size;
        return first;
    }

    //A method to remove the last item in constant time and return its value
    @Override
    public T removeLast() {
        if (size == 0)
            return null;

        double usageFactor = (double) (size - 1) / items.length;
        if (usageFactor < 0.25 && items.length >= 16)
            resizeDown();

        rear = (rear - 1 + capacity) % capacity;
        T last = items[rear];
        items[rear] = null;
        --size;
        return last;
    }

    //A method to get an item at specific index
    @Override
    public T get(int index) {
        if (index >= size || index < 0)
            return null;
        int idx = (index + front) % capacity;
        return items[idx];
    }

    //A method to print all element in the deque
    @Override
    public void printDeque() {
        int startIdx = front;
        int count = 0;

        while (count != size) {
            if (startIdx == capacity)
                startIdx = 0;
            if (count != size - 1)
                System.out.print(items[startIdx++] + " ");
            else
                System.out.print(items[startIdx++]);
            ++count;
        }
        System.out.println();
    }

    //A method to get the size of the array
    @Override
    public int size() {
        return size;
    }

    //A method to return iterator (instance of the ArrayDequeIterator class)
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    //A method to return true if 2 lists have the same items
    //First, we need to check if the Object passed to the function
    //is of the same type of this using instanceof, which is also make the object o
    //of the same type as our class
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o instanceof ArrayDeque ard) {
            if (this.size != ard.size)
                return false;

            for (int i = 0; i < this.size; ++i) {
                if (this.items[i] != ard.items[i])
                    return false;
            }
            return true;
        }
        return false;
    }
}
