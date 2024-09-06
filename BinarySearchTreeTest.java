import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
    @Test
    void removeRootNoDescendants() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(1);

        Assertions.assertTrue(bst.remove(1));
        Assertions.assertFalse(bst.remove(1));
    }

    @Test
    void removeRootWithDescendants() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(7);

        // Left
        bst.add(3);
        bst.add(2);
        bst.add(4);

        // Right
        bst.add(10);
        bst.add(8);
        bst.add(12);

        Assertions.assertTrue(bst.remove(7));
        Assertions.assertFalse(bst.remove(7));
    }

    @Test
    void removeLeftElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        bst.add(3);
        bst.add(2);
        bst.add(4);

        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        Assertions.assertTrue(bst.remove(7));
        Assertions.assertFalse(bst.remove(7));
    }

    @Test
    void removeLeftBranchFirst() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        bst.add(3);
        bst.add(2);
        bst.add(4);

        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        Assertions.assertTrue(bst.remove(15));
        Assertions.assertTrue(bst.remove(7));
        Assertions.assertTrue(bst.remove(3));
        Assertions.assertTrue(bst.remove(2));
        Assertions.assertTrue(bst.remove(4));
        Assertions.assertTrue(bst.remove(10));
        Assertions.assertTrue(bst.remove(8));
        Assertions.assertTrue(bst.remove(12));
        Assertions.assertTrue(bst.remove(30));
        Assertions.assertTrue(bst.remove(20));
        Assertions.assertTrue(bst.remove(19));
        Assertions.assertTrue(bst.remove(21));
        Assertions.assertTrue(bst.remove(35));
        Assertions.assertTrue(bst.remove(34));
        Assertions.assertTrue(bst.remove(36));
    }

    @Test
    void removeRightBranchFirst() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        bst.add(3);
        bst.add(2);
        bst.add(4);

        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        Assertions.assertTrue(bst.remove(15));
        Assertions.assertTrue(bst.remove(30));
        Assertions.assertTrue(bst.remove(20));
        Assertions.assertTrue(bst.remove(19));
        Assertions.assertTrue(bst.remove(21));
        Assertions.assertTrue(bst.remove(35));
        Assertions.assertTrue(bst.remove(34));
        Assertions.assertTrue(bst.remove(36));
        Assertions.assertTrue(bst.remove(7));
        Assertions.assertTrue(bst.remove(3));
        Assertions.assertTrue(bst.remove(2));
        Assertions.assertTrue(bst.remove(4));
        Assertions.assertTrue(bst.remove(10));
        Assertions.assertTrue(bst.remove(8));
        Assertions.assertTrue(bst.remove(12));
    }

    @Test
    void size() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        Assertions.assertEquals(0, bst.size());

        // Root
        bst.add(7);

        // Left
        bst.add(3);
        bst.add(2);
        bst.add(4);

        // Right
        bst.add(10);
        bst.add(8);
        bst.add(12);

        Assertions.assertEquals(7, bst.size());
    }

    @Test
    void sizeAfterRemoval() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(7);

        // Left
        bst.add(3);
        bst.add(2);
        bst.add(4);

        // Right
        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Removal
        bst.remove(3);
        bst.remove(8);

        Assertions.assertEquals(5, bst.size());
    }

    @Test
    void containsAddedElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(7);

        Assertions.assertTrue(bst.contains(7));
    }

    @Test
    void containsUnaddedElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(7);

        Assertions.assertFalse(bst.contains(2));
    }

    @Test
    void isEmptyDefault() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Assertions.assertTrue(bst.isEmpty());
    }
    
    @Test
    void isEmptySingleElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(7);

        Assertions.assertFalse(bst.isEmpty());
    }

    @Test
    void isEmptyFullRemoval() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(7);
        bst.add(6);
        bst.add(8);

        bst.remove(7);
        bst.remove(6);
        bst.remove(8);

        Assertions.assertTrue(bst.isEmpty());
    }

    @Test
    void clear() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(7);
        bst.add(3);
        bst.add(2);
        bst.add(4);
        bst.add(10);
        bst.add(8);
        bst.add(12);

        bst.clear();

        Assertions.assertTrue(bst.isEmpty());
    }

    @Test
    void heightDefault() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Assertions.assertEquals(0, bst.height());
    }

    @Test
    void heightSingleElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(1);

        Assertions.assertEquals(1, bst.height());
    }

    @Test
    void heightBalancedTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        bst.add(3);
        bst.add(2);
        bst.add(4);

        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        Assertions.assertEquals(4, bst.height());
    }

    @Test
    void heightImbalancedTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        Assertions.assertEquals(4, bst.height());
    }

    @Test
    void heightFullRemoval() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        bst.add(3);
        bst.add(2);
        bst.add(4);

        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        // Removal
        bst.remove(15);
        bst.remove(7);
        bst.remove(3);
        bst.remove(2);
        bst.remove(4);
        bst.remove(10);
        bst.remove(8);
        bst.remove(12);
        bst.remove(30);
        bst.remove(20);
        bst.remove(19);
        bst.remove(21);
        bst.remove(35);
        bst.remove(34);
        bst.remove(36);

        Assertions.assertEquals(0, bst.height());
    }

    @Test
    void toStringArrayEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Assertions.assertEquals("[]", bst.toStringArray());
    }

    @Test
    void toStringArrayNonEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Root
        bst.add(15);

        // Left
        bst.add(7);

        bst.add(3);
        bst.add(2);
        bst.add(4);

        bst.add(10);
        bst.add(8);
        bst.add(12);

        // Right
        bst.add(30);

        bst.add(20);
        bst.add(19);
        bst.add(21);

        bst.add(35);
        bst.add(34);
        bst.add(36);

        Assertions.assertEquals("[2, 3, 4, 7, 8, 10, 12, 15, 19, 20, 21, 30, 34, 35, 36]", bst.toStringArray());
    }
}