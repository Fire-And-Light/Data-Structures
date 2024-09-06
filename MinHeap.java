import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// A balanced binary tree whose parent nodes are less than child nodes
class MinHeap<T extends Comparable<T>> {
    private ArrayList<T> heap; // The heap is array-based rather than node-based
    private HashMap<T, HashSet<Integer>> elemToIndex; // A fast way of finding the index(es) that correspond(s) to an element

    MinHeap() {
        super();
        this.heap = new ArrayList<>();
        this.elemToIndex = new HashMap<>();
    }

    // Insert all elements at once using the heapify algorithm, which takes O(n) time rather than O(n * logn)
    MinHeap(T[] elements) {
        this.heap = new ArrayList<T>(elements.length);
        this.elemToIndex = new HashMap<>();

        for (T element : elements) {
            this.heap.add(element);
            this.mapAdd(element, this.heap.size() - 1);
        }

        for (int i = Math.max(0, (elements.length / 2) - 1); i >= 0; i--) {
            this.sink(i);
        }
    }

    int size() {
        return this.heap.size();
    }

    boolean isEmpty() {
        return this.heap.isEmpty();
    }

    boolean contains(T element) {
        return this.elemToIndex.containsKey(element);
    }

    void clear() {
        this.heap.clear();
        this.elemToIndex.clear();
    }

    T peek() {
        if (this.heap.isEmpty()) {
            return null;

        } else {
            return this.heap.get(0);
        }
    }

    T dequeue() {
        T result = this.heap.get(0);
        HashSet<Integer> indexes = this.elemToIndex.get(result);
        indexes.remove(0);

        if (indexes.isEmpty()) {
            this.elemToIndex.remove(result);
        }

        T last = this.heap.remove(this.heap.size() - 1);

        if (!result.equals(last)) {
            this.heap.set(0, last);
            this.sink(0);
        }

        return result;
    }

    void enqueue(T element) {
        this.heap.add(element);
        int index = this.heap.size() - 1;
        this.mapAdd(element, index);
        this.swim(index);
    }

    T remove(T element) {
        int lastIndex = this.heap.size() - 1;
        T last = this.heap.get(lastIndex);

        HashSet<Integer> indexesSet = this.elemToIndex.get(element);
        ArrayList<Integer> indexesList = new ArrayList<>(indexesSet);
        int index = indexesList.get(0); // Retrieve an arbitrary index

        this.swap(index, lastIndex);
        this.heap.remove(lastIndex);
        indexesSet.remove(lastIndex);

        if (indexesSet.isEmpty()) {
            this.elemToIndex.remove(element);
        }

        if (last != element) {
            // Check if the last node can sink
            this.sink(index);

            // If the last node didn't sink, then check if it can swim
            if (this.heap.get(index).equals(last)) {
                this.swim(index);
            }
        }
 
        return element;
    }

    private void mapAdd(T element, int index) {
        if (this.elemToIndex.containsKey(element)) {
            HashSet<Integer> indexes = this.elemToIndex.get(element);
            indexes.add(index);

        } else {
            HashSet<Integer> indexes = new HashSet<>();
            indexes.add(index);
            this.elemToIndex.put(element, indexes);
        }
    }

    private void swim(int index) {
        T element = this.heap.get(index);
        int parentIndex = (index - 1) / 2; // Because of integer division, "(index - 1) / 2" always works
        T parent = this.heap.get(parentIndex);

        while (index > 0 && element.compareTo(parent) < 0) {
            this.swap(index, parentIndex);

            index = parentIndex;
            element = this.heap.get(index);
            parentIndex = (index - 1) / 2;
            parent = this.heap.get(parentIndex);
        }
    }

    private void sink(int index) {
        T element = this.heap.get(index);

        while (true) {
            T leftChild = this.leftChild(index);
            T rightChild = this.rightChild(index);
            T smallest = null; // Sinking must only occur with the smallest child
            int smallestIndex = -1;

            if (leftChild == null) {
                smallest = rightChild;
                smallestIndex = 2 * index + 2;

            } else if (rightChild == null) {
                smallest = leftChild;
                smallestIndex = 2 * index + 1;

            } else {
                if (leftChild.compareTo(rightChild) < 0) {
                    smallest = leftChild;
                    smallestIndex = 2 * index + 1;

                } else {
                    smallest = rightChild;
                    smallestIndex = 2 * index + 2;
                }
            }

            if (smallest != null) {
                if (element.compareTo(smallest) > 0) {
                    this.swap(index, smallestIndex);
                    index = smallestIndex;
                    continue;
                }
            }

            break;
        }
    }

    private void swap(int index1, int index2) {
        T element1 = this.heap.get(index1);
        T element2 = this.heap.get(index2);

        this.heap.set(index1, element2);
        this.heap.set(index2, element1);

        HashSet<Integer> indexes1 = this.elemToIndex.get(element1);
        HashSet<Integer> indexes2 = this.elemToIndex.get(element2);

        indexes1.remove(index1);
        indexes1.add(index2);
        
        indexes2.remove(index2);
        indexes2.add(index1);
    }

    private T leftChild(int index) {
        if (2 * index + 1 >= this.heap.size()) {
            return null;

        } else {
            return this.heap.get(2 * index + 1);
        }
    }

    private T rightChild(int index) {
        if (2 * index + 2 >= this.heap.size()) {
            return null;

        } else {
            return this.heap.get(2 * index + 2);
        }
    }
}