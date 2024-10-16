package deque;

import java.util.Iterator;

public class ArrayDeque<T> {
    private T[] items;
    private final int capacity = 8;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    //When our array is full, we want to create a new array with extra capacity
    //and add the items to it, then copy the created array to our items array
    private void resize() {
        //todo: write a method to resize the array when it becomes full
    }

    //A method to add an item from the front
    public void addFirst(T item) {
        if (size == capacity)
            return;

        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        ++size;
    }

    //A method to add an item from the back
    public void addLast(T item) {
        if (size == capacity)
            return;

        items[rear] = item;
        rear = (rear + 1) % items.length;
        ++size;
    }

    //A method to remove the first item in constant time and return its value
    public T removeFirst() {
        if (size == 0)
            return null;

        T first = items[front];
        items[front] = null;
        front = (front + 1) % capacity;
        --size;
        return first;
    }

    //A method to remove the last item in constant time and return its value
    public T removeLast() {
        if (size == 0)
            return null;

        rear = (rear - 1 + capacity) % capacity;
        T last = items[rear];
        items[rear] = null;
        --size;
        return last;
    }

    //A method to get an item at specific index
    public T get(int index) {
        if (index >= size || index < 0)
            return null;
        return items[index];
    }

    //A method to print all element in the deque
    public void printDeque() {
        int firstIdx = front;
        int count = 0;

        while (count != size) {
            if (firstIdx == capacity)
                firstIdx = 0;
            System.out.print(items[firstIdx++] + " ");
            ++count;
        }
        System.out.println();
    }

    //A method to return true if the list is empty or false if it has elements
    public boolean isEmpty() {
        return size == 0;
    }

    //A method to get the size of the array
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        //todo: write a method to return an iterator
        return null;
    }

    @Override
    public boolean equals(Object o) {
        //todo: write a method to return if the content of a given deque is equal to another deque
        return true;
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> items = new ArrayDeque<>();
//
//        items.addLast("a");
//        items.addLast("b");
//        items.addFirst("c");
//        System.out.println(items.removeLast());
//        items.addLast("d");
//        items.addLast("e");
//        items.addFirst("f");
//        System.out.println(items.removeLast());
//        items.addLast("g");
//        items.addLast("h");
//
//        items.printDeque();
//
//        System.out.println("Size = " + items.size());
//    }
}
