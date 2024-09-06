import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Separate chaining via linked lists
class HashTableSeparateChaining<K, V> {
    private class Node { // The node inserted in the linked list
        K key;
        V value;
        int hash;

        Node(K key, V value) {
            super();
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOADFACTOR = 0.75;

    private int size; // The amount of elements in the hash table
    private int capacity; // The max amount of elements that can be in the hash table
    private double loadFactor; // The percentage of the hash table that can store elements before needing to resize the table
    private int threshold; // The amount of elements that can be stored in the hash table before needing to resize the table
    private LinkedList<Node>[] table;

    HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
    }

    HashTableSeparateChaining(int capacity) {
        this(capacity, DEFAULT_LOADFACTOR);
    }

    HashTableSeparateChaining(int capacity, double loadFactor) {
        super();

        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity must be greater than 0");
        }

        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("The load factor must be a finite number greater than 0");
        }

        this.size = 0;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.threshold = (int) (this.capacity * this.loadFactor);
        this.table = new LinkedList[this.capacity];
    }

    int size() {
        return this.size;
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
    }

    boolean contains(K key) {
        Node element = this.getNode(key);

        if (element == null) {
            return false;

        } else {
            return true;
        }
    }

    V get(K key) {
        Node element = this.getNode(key);

        if (element == null) {
            return null;

        } else {
            return element.value;
        }
    }

    V remove(K key) {
        Node element = this.getNode(key);

        if (element == null) {
            return null;

        } else {
            int index = this.hashToIndex(element.hash);
            LinkedList<Node> bucket = this.table[index];
            bucket.remove(element);

            if (bucket.isEmpty()) {
                this.table[index] = null;
            }

            this.size--;
            return element.value;
        }
    }

    void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null");
        }

        if (this.size >= this.threshold) { // Perform this first in order to prevent redundant computation with the old table
            this.resizeTable();
        }

        Node existentElement = this.getNode(key);

        if (existentElement == null) {
            Node element = new Node(key, value);
            int index = this.hashToIndex(element.hash);
            LinkedList<Node> bucket = this.table[index];
    
            if (bucket == null) {
                bucket = new LinkedList<>();
                this.table[index] = bucket;
            }
            
            bucket.add(element);
            this.size++;

        } else {
            existentElement.value = value;
        }
    }

    List<K> keys() {
        ArrayList<K> keys = new ArrayList<>();

        for (LinkedList<Node> bucket : this.table) {
            if (bucket != null) {
                for (Node element : bucket) {
                    keys.add(element.key);
                }
            }
        }

        return keys;
    }

    List<V> values() {
        ArrayList<V> values = new ArrayList<>();

        for (LinkedList<Node> bucket : this.table) {
            if (bucket != null) {
                for (Node element : bucket) {
                    values.add(element.value);
                }
            }
        }

        return values;
    }

    private Node getNode(K key) {
        if (key != null) {
            int index = this.hashToIndex(key.hashCode());
            LinkedList<Node> bucket = this.table[index];
    
            if (bucket != null) {
                for (Node element : bucket) {
                    if (element.key.equals(key)) {
                        return element;
                    }
                }
            }
        }

        return null;
    }

    private int hashToIndex(int hash) {
        return (hash & 0x7FFFFFFF) % this.capacity; // "%" returns the remainder in Java, rather than the modulus, which means it can return negative values, so bit manipulation is necessary to strip the negative sign before using "%"
    }

    private void resizeTable() {
        this.capacity *= 2;
        this.threshold = (int) (this.capacity * this.loadFactor);
        LinkedList<Node>[] newTable = new LinkedList[this.capacity];

        for (LinkedList<Node> oldBucket : this.table) {
            if (oldBucket != null) {
                for (Node oldElement : oldBucket) {
                    int newIndex = this.hashToIndex(oldElement.hash);
                    LinkedList<Node> newBucket = newTable[newIndex];
                    
                    if (newBucket == null) {
                        newBucket = new LinkedList<>();
                        newBucket.add(oldElement);
                        newTable[newIndex] = newBucket;
    
                    } else {
                        newBucket.add(oldElement);
                    }
                }
            }
        }

        this.table = newTable;
    }
}