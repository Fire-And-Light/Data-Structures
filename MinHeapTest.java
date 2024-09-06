import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Random;

class MinHeapTest {
    @Test
    void addAscending() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(1);
        mh.enqueue(2);
        mh.enqueue(3);
        mh.enqueue(4);
        mh.enqueue(5);

        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(3, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
        Assertions.assertEquals(5, mh.dequeue());
    }

    @Test
    void addDescending() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(5);
        mh.enqueue(4);
        mh.enqueue(3);
        mh.enqueue(2);
        mh.enqueue(1);

        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(3, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
        Assertions.assertEquals(5, mh.dequeue());
    }

    @Test
    void addUnsorted() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(3, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
        Assertions.assertEquals(5, mh.dequeue());
    }

    @Test
    void addDuplicates() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(4);
        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(1);

        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
    }

    @Test
    void addDuplicates2() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(1);
        mh.enqueue(4);
        mh.enqueue(2);
        mh.enqueue(2);
        mh.enqueue(2);
        mh.enqueue(2);
        mh.enqueue(2);
        mh.enqueue(2);

        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
    }

    @Test
    void removeSingle() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.remove(2);

        Assertions.assertEquals(1, mh.dequeue());
        Assertions.assertEquals(3, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
        Assertions.assertEquals(5, mh.dequeue());
    }

    @Test
    void removeMultiple() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.remove(1);
        mh.remove(3);
        mh.remove(5);

        Assertions.assertEquals(2, mh.dequeue());
        Assertions.assertEquals(4, mh.dequeue());
    }

    @Test
    void size() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        Assertions.assertEquals(5, mh.size());
    }

    @Test
    void sizeAfterRemoval() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.dequeue();
        mh.dequeue();

        Assertions.assertEquals(3, mh.size());
    }

    @Test
    void clear() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.clear();

        Assertions.assertEquals(0, mh.size());
    }

    @Test
    void isEmpty() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        Assertions.assertFalse(mh.isEmpty());
    }

    @Test
    void isEmptyAfterClear() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.clear();

        Assertions.assertTrue(mh.isEmpty());
    }

    @Test
    void isEmptyFullDequeue() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.dequeue();
        mh.dequeue();
        mh.dequeue();
        mh.dequeue();
        mh.dequeue();

        Assertions.assertTrue(mh.isEmpty());
    }

    @Test
    void isEmptyFullRemoval() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        mh.remove(1);
        mh.remove(2);
        mh.remove(3);
        mh.remove(4);
        mh.remove(5);

        Assertions.assertTrue(mh.isEmpty());
    }

    @Test
    void containsAddedElements() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        Assertions.assertTrue(mh.contains(3));
        Assertions.assertTrue(mh.contains(1));
    }

    @Test
    void containsUnaddedElements() {
        MinHeap<Integer> mh = new MinHeap<>();

        mh.enqueue(3);
        mh.enqueue(1);
        mh.enqueue(5);
        mh.enqueue(2);
        mh.enqueue(4);

        Assertions.assertFalse(mh.contains(7));
        Assertions.assertFalse(mh.contains(9));
    }

    @Test
    void heapify() {
        Random rng = new Random();
        Integer[] nums = new Integer[100];
        nums[0] = 1;

        // Insert random integers between 1 and 100 without replacement
        for (int i = 1; i < 100; i++) {
            int randomIndex = rng.nextInt(i + 1);
            nums[i] = nums[randomIndex];
            nums[randomIndex] = i + 1;
        }

        MinHeap<Integer> mh = new MinHeap<>(nums);

        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i + 1, mh.dequeue());
        }
    }
}