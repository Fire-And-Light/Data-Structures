import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableSeparateChainingTest {
    @Test
    void sizeDefault() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();
        Assertions.assertEquals(0, table.size());
    }
    
    @Test
    void sizeOverwrite() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(1, "2");
        table.put(1, "3");

        Assertions.assertEquals(1, table.size());
    }

    @Test
    void sizeMultipleElements() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        Assertions.assertEquals(3, table.size());
    }

    @Test
    void isEmptyDefault() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();
        Assertions.assertTrue(table.isEmpty());
    }

    @Test
    void isEmptyWithElements() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        Assertions.assertFalse(table.isEmpty());
    }

    @Test
    void clear() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        table.clear();

        Assertions.assertTrue(table.isEmpty());
    }

    @Test
    void containsAddedElement() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");

        Assertions.assertTrue(table.contains(1));
    }

    @Test
    void containsUnaddedElement() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");

        Assertions.assertFalse(table.contains(2));
    }

    @Test
    void get() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");

        Assertions.assertEquals("1", table.get(1));
    }

    @Test
    void getOverwrite() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(1, "2");
        table.put(1, "3");

        Assertions.assertEquals("3", table.get(1));
    }

    @Test
    void getCollision() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(1, 4);

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        Assertions.assertEquals("3", table.get(3));
    }

    @Test
    void containsCollision() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(1, 4);

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        Assertions.assertTrue(table.contains(3));
    }

    @Test
    void remove() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");

        table.remove(2);

        Assertions.assertFalse(table.contains(2));
    }

    @Test
    void removeAll() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");

        table.remove(1);
        table.remove(2);

        Assertions.assertTrue(table.isEmpty());
    }

    @Test
    void removeCollision() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(1, 4);

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        table.remove(3);

        Assertions.assertFalse(table.contains(3));
    }

    @Test
    void removeAllCollision() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(1, 4);

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");

        table.remove(1);
        table.remove(2);
        table.remove(3);

        Assertions.assertTrue(table.isEmpty());
    }
    
    @Test
    void keys() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");
        table.put(4, "4");
        table.put(5, "5");
        table.put(6, "6");
        table.put(7, "7");

        List<Integer> expected = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        Assertions.assertTrue(expected.equals(table.keys()));
    }

    @Test
    void values() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");
        table.put(4, "4");
        table.put(5, "5");
        table.put(6, "6");
        table.put(7, "7");

        List<String> expected = Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7"});

        Assertions.assertTrue(expected.equals(table.values()));
    }

    @Test
    void keysAfterResize() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(4);

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");
        table.put(4, "4"); // Resize
        table.put(5, "5");
        table.put(6, "6");
        table.put(7, "7"); // Resize

        List<Integer> expected = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        Assertions.assertTrue(expected.equals(table.keys()));
    }

    @Test
    void valuesAfterResize() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(4);

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");
        table.put(4, "4"); // Resize
        table.put(5, "5");
        table.put(6, "6");
        table.put(7, "7"); // Resize

        List<String> expected = Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7"});

        Assertions.assertTrue(expected.equals(table.values()));
    }

    @Test
    void keysAfterRemoval() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");
        table.put(4, "4");
        table.put(5, "5");
        table.put(6, "6");
        table.put(7, "7");

        table.remove(4);

        List<Integer> expected = Arrays.asList(new Integer[]{1, 2, 3, 5, 6, 7});

        Assertions.assertTrue(expected.equals(table.keys()));
    }

    @Test
    void valuesAfterRemoval() {
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        table.put(1, "1");
        table.put(2, "2");
        table.put(3, "3");
        table.put(4, "4");
        table.put(5, "5");
        table.put(6, "6");
        table.put(7, "7");

        table.remove(4);

        List<String> expected = Arrays.asList(new String[]{"1", "2", "3", "5", "6", "7"});

        Assertions.assertTrue(expected.equals(table.values()));
    }

    @Test
    void randomKeys() {
        // Create an array of random integers between 1 and 100 without replacement
        Random rng = new Random();
        Integer[] randomNums = new Integer[100];
        randomNums[0] = 1;

        for (int i = 1; i < 100; i++) {
            int randomIndex = rng.nextInt(i + 1);
            randomNums[i] = randomNums[randomIndex];
            randomNums[randomIndex] = i + 1;
        }

        // Insert integers between 1 and 100, without replacement, in random order
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        for (int i = 0; i < 100; i++) {
            table.put(randomNums[i], randomNums[i] + "");
        }

        // Check integers
        List<Integer> keys = table.keys();

        for (int i = 1; i <= 100; i++) {
            Assertions.assertTrue(keys.contains(i));
        }
    }

    @Test
    void randomRemoveAll() {
        // Create an array of random integers between 1 and 100 without replacement
        Random rng = new Random();
        Integer[] randomNums = new Integer[100];
        randomNums[0] = 1;

        for (int i = 1; i < 100; i++) {
            int randomIndex = rng.nextInt(i + 1);
            randomNums[i] = randomNums[randomIndex];
            randomNums[randomIndex] = i + 1;
        }

        // Insert integers between 1 and 100, without replacement, in random order
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>();

        for (int i = 0; i < 100; i++) {
            table.put(randomNums[i], randomNums[i] + "");
        }

        // Remove integers
        for (int i = 1; i <= 100; i++) {
            table.remove(i);
        }

        // Check removed integers
        for (int i = 1; i <= 100; i++) {
            Assertions.assertFalse(table.contains(i));
        }

        Assertions.assertTrue(table.isEmpty());
    }

    @Test
    void randomFullCapacity() {
        // Create an array of random integers between 1 and 16 without replacement
        Random rng = new Random();
        Integer[] randomNums = new Integer[16];
        randomNums[0] = 1;

        for (int i = 1; i < 16; i++) {
            int randomIndex = rng.nextInt(i + 1);
            randomNums[i] = randomNums[randomIndex];
            randomNums[randomIndex] = i + 1;
        }

        // Insert integers between 1 and 16, without replacement, in random order
        HashTableSeparateChaining<Integer, String> table = new HashTableSeparateChaining<>(16, 1);

        for (int i = 0; i < 16; i++) {
            table.put(randomNums[i], randomNums[i] + "");
        }

        // Remove six random integers
        for (int i = 0; i < 6; i++) {
            table.remove(randomNums[i]);
        }

        // Insert six new integers
        for (int i = 21; i <= 26; i++) {
            table.put(i, i + "");
        }

        // Check all integers
        for (int i = 6; i < 16; i++) {
            Assertions.assertTrue(table.contains(randomNums[i]));
        }

        for (int i = 21; i <= 26; i++) {
            Assertions.assertTrue(table.contains(i));
        }
        
        // Remove all integers individually
        for (int i = 6; i < 16; i++) {
            table.remove(randomNums[i]);
        }

        for (int i = 21; i <= 26; i++) {
            table.remove(i);
        }

        // Check removed integers
        for (int i = 6; i < 16; i++) {
            Assertions.assertFalse(table.contains(randomNums[i]));
        }

        for (int i = 21; i <= 26; i++) {
            Assertions.assertFalse(table.contains(i));
        }    

        Assertions.assertTrue(table.isEmpty());
    }
}