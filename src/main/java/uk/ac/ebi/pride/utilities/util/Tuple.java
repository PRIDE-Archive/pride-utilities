package uk.ac.ebi.pride.utilities.util;


import java.io.Serializable;
import java.util.Objects;

/**
 * Tuple stores two elements.
 *
 * @author rwang
 * @author ypriverol
 */
public class Tuple <K, V> implements Serializable {
    private K key;
    private V value;

    private int hashCode;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
        this.hashCode = computeHashCode();
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
        this.hashCode = computeHashCode();
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
        this.hashCode = computeHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;

        Tuple tuple = (Tuple) o;

        return Objects.equals(key, tuple.key) && Objects.equals(value, tuple.value);

    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    private int computeHashCode(){
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}