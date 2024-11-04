package deque;

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();
    int size();
    void printDeque();
    T get(int index);

    default boolean isEmpty() {
        int size = size();
        return size == 0;
    }
}
