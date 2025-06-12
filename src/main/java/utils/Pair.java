package utils;

import java.io.Serializable;

public class Pair<T, K> implements Serializable {
    public T first;
    public K second;
    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return String.format("Pair(first=%s, second=%s)", first, second);
    }
}
