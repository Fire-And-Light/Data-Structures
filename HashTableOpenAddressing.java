import java.util.ArrayList;
import java.util.List;

// Open addressing via quadratic probing
class HashTableOpenAddressing<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOADFACTOR = 0.5;

    private final K TOMBSTONE = (K) new Object(); // Represents a deleted key, which is useful for finding keys that collided

    private int size;
    private int capacity;
    private double loadFactor;
    private int threshold;
    private K[] keys;
    private V[] values;
    private int tombstones;

    HashTableOpenAddressing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
    }

    HashTableOpenAddressing(int capacity) {
        this(capacity, DEFAULT_LOADFACTOR);
    }

    HashTableOpenAddressing(int capacity, double loadFactor) {
        super();

        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity must be greater than 0");
        }

        if (loadFactor <= 0 || loadFactor > 1 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("The load factor must be a finite number greater than 0, and less than or equal to 1");
        }

        this.size = 0;
        this.capacity = Math.max(DEFAULT_CAPACITY, this.nextPowerOf2(capacity)); // The capacity must be a power of 2 in order for quadratic probing to cover all entries of the hash table
        this.loadFactor = loadFactor;
        this.threshold = (int) (this.capacity * this.loadFactor);
        this.keys = (K[]) new Object[this.capacity];
        this.values = (V[]) new Object[this.capacity];
    }

    int size() {
        return this.size;
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    void clear() {
        this.keys = (K[]) new Object[this.capacity];
        this.values = (V[]) new Object[this.capacity];
        this.size = 0;
    }

    boolean contains(K key) {
        return this.keys[this.probeIndex(key)] == key;
    }

    V get(K key) {
        return this.values[this.probeIndex(key)];
    }

    V remove(K key) {
        int index = this.probeIndex(key);
        V value = null;

        if (this.keys[index] == key) {
            value = this.values[index];
            this.keys[index] = TOMBSTONE;
            this.values[index] = null;
            this.tombstones++;
            this.size--;
        }

        return value;
    }

    void put(K key, V value) {
        if (this.size + this.tombstones >= this.threshold) {
            this.resizeTable();
        }

        int index = this.probeIndex(key);

        if (this.keys[index] == key) {
            this.values[index] = value;

        } else {
            if (this.keys[index] == TOMBSTONE) {
                this.tombstones--;
            }

            this.keys[index] = key;
            this.values[index] = value;
            this.size++;
        }
    }

    List<K> keys() {
        ArrayList<K> keys = new ArrayList<>();

        for (int i = 0; i < this.capacity; i++) {
            if (this.keys[i] != null && this.keys[i] != TOMBSTONE) {
                keys.add(this.keys[i]);
            }
        }

        return keys;
    }

    List<V> values() {
        ArrayList<V> values = new ArrayList<>();

        for (int i = 0; i < this.capacity; i++) {
            if (this.keys[i] != null && this.keys[i] != TOMBSTONE) {
                values.add(this.values[i]);
            }
        }

        return values;
    }

    private int nextPowerOf2(int n) {
        if (n == 0) {
            return 1;

        // Check if "n" is already a power of 2
        } else if (n > 0 && (n & (n - 1)) == 0) {
            return n;

        } else {
            n--;
            n |= n >> 1;
            n |= n >> 2;
            n |= n >> 4;
            n |= n >> 8;
            n |= n >> 16;
            n++;

            return n;
        }
    }

    // Returns the index of the key, or if the key doesn't exist, then the index of where the key would be
    private int probeIndex(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null");
        }

        int hash = key.hashCode();
        int index = this.hashToIndex(hash);
        int x = 0; // The quadratic probing variable
        int tombstoneIndex = -1; // The index of the first tombstone that's encountered

        while (this.keys[index] != key) {
            // If the first tombstone is encountered, then save the index
            if (this.keys[index] == TOMBSTONE && tombstoneIndex == -1) {
                tombstoneIndex = index;
            }

            // If the key doesn't exist, then return the index of where the key would be
            if (this.keys[index] == null) {
                if (tombstoneIndex == -1) {
                    return index;

                } else {
                    return tombstoneIndex;
                }
            }

            x++;
            index = this.hashToIndex(hash + this.quadraticProbingFunction(x));
        }

        // If a tombstone was encountered, then replace it with the current key, and replace the current key will "null"
        if (tombstoneIndex != -1) {
            this.keys[index] = TOMBSTONE;
            this.keys[tombstoneIndex] = key;
            this.values[tombstoneIndex] = this.values[index];
            this.values[index] = null;

            return tombstoneIndex;

        } else {
            return index;
        }
    }

    private int hashToIndex(int hash) {
        return (hash & 0x7FFFFFFF) % this.capacity;
    }

    private int quadraticProbingFunction(int x) {
        return (x * x + x) / 2;
    }

    private void resizeTable() {
        this.capacity *= 2;
        this.threshold = (int) (this.loadFactor * this.capacity);

        K[] oldKeys = this.keys;
        this.keys = (K[]) new Object[this.capacity];

        V[] oldValues = this.values;
        this.values = (V[]) new Object[this.capacity];

        this.tombstones = 0;
        this.size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null && oldKeys[i] != TOMBSTONE) {
                this.put(oldKeys[i], oldValues[i]);
            }
        }
    }
}