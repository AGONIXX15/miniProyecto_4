package view.utils;

import java.io.Serializable;

public class Pair<T, K> implements Serializable {
    public T first;
    public K second;
    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }
}
