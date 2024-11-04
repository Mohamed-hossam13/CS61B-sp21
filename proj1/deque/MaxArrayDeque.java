package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    //A constructor to create a MaxArrayDeque with the given Comparator
    public MaxArrayDeque(Comparator<T> c){
        comparator = c;
    }

    //A method to return the maximum element in the deque as governed by the previously given Comparator.
    //If the MaxArrayDeque is empty, simply return null.
    public T max() {
        if (this.isEmpty())
            return null;

        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); ++i) {
            T currentItem = this.get(i);
            if (comparator.compare(currentItem, maxItem) > 0)
                maxItem = currentItem;
        }
        return maxItem;
    }

    //A method to return the maximum element in the deque as governed by the parameter Comparator c.
    //If the MaxArrayDeque is empty, simply return null.
    public T max(Comparator<T> c) {
        if (this.isEmpty())
            return null;

        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); ++i) {
            T currentItem = this.get(i);
            if (c.compare(currentItem, maxItem) > 0)
                maxItem = currentItem;
        }
        return maxItem;
    }
}