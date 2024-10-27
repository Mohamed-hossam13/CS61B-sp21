package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node{
        private T item;
        private Node prev;
        private Node next;

        public Node(T i) {
            item = i;
            prev = null;
            next = null;
        }
    }

    //The deque class will have an instance from the node class which is a sentinel node
    //It will also have a size variable to count the size in constant time
    private Node sentinel;
    private int size;

    //An empty constructor to initialize the deque with size = 0
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node cur;
        int count;

        public LinkedListDequeIterator() {
            cur = sentinel.next;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T item = cur.item;
            cur = cur.next;
            ++count;
            return item;
        }
    }

    //A method to add an item from the front
    //First, we need to handle the next and prev of the first node
    //Second, we need handle the prev of the sentinel.next
    //Third, we will handle the sentinel.next to point to the new node
    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;

        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        ++size;
    }

    //A method to add an item from the back
    //First, we need to handle the next and prev of the new node
    //Second, we need to handle the next of prev of the sentinel which was the last node
    //Third, we will handle the prev of the sentinel to point to the last node
    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;

        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        ++size;
    }

    //A method to remove the first item in the list and return its value
    //First, we need to handle the prev of the next of the first node
    //Second, we need to handle sentinel.next to point to the new first node
    @Override
    public T removeFirst() {
        if (size == 0)
            return null;
        Node first = sentinel.next;
        T data = first.item;

        first.next.prev = sentinel;
        sentinel.next = first.next;
        --size;

        return data;
    }

    //A method to remove the last item in the list and return its value
    //First, we need to handle the next of the new last node
    //Second, we need to handle the prev of the sentinel to point to the last node
    @Override
    public T removeLast() {
        if (size == 0)
            return null;
        Node last = sentinel.prev;
        T data = last.item;

        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        --size;

        return data;
    }

    //A method to get the ith element in the deque using iteration
    //First, we need a reference for the first node
    //Second, we will loop till the given index
    @Override
    public T get(int index) {
        if (index >= size || index < 0)
            return null;

        Node cur = sentinel.next;
        for (int i = 0; i < size; ++i) {
            if (i == index)
                break;
            cur = cur.next;
        }
        return cur.item;
    }

    //A method to get the ith element in the deque using recursion
    //We need a helper method to use recursion
    public T getRecursiveHelper(int index, int start, Node cur) {
        if (start == size)
            return null;
        if (start == index)
            return cur.item;
        return getRecursiveHelper(index, start + 1, cur.next);
    }
    public T getRecursive(int index) {
        Node cur = sentinel.next;
        return getRecursiveHelper(index, 0, cur);
    }

    //A method to print all element in the deque
    @Override
    public void printDeque() {
        Node cur = sentinel.next;
        for (int i = 0; i < size; ++i) {
            if (i != size - 1) {
                System.out.print(cur.item + " ");
            }
            else
                System.out.print(cur.item);
            cur = cur.next;
        }
        System.out.println();
    }

    //A method to return the size of the list
    @Override
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof LinkedListDeque list) {
            if (this.size != list.size)
                return false;
            Node cur1 = sentinel.next;
            for (Object x : list) {
                if (x != cur1.item)
                    return false;
                cur1 = cur1.next;
            }
            return true;
        }
        return false;
    }
}