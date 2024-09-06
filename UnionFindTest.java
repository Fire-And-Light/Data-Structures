import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class UnionFindTest {
    @Test
    void size() {
        UnionFind uf = new UnionFind(1);
        UnionFind uf2 = new UnionFind(10);
        UnionFind uf3 = new UnionFind(100);

        Assertions.assertEquals(1, uf.size());
        Assertions.assertEquals(10, uf2.size());
        Assertions.assertEquals(100, uf3.size());
    }

    @Test
    void connected() {
        UnionFind uf = new UnionFind(2);

        Assertions.assertFalse(uf.connected(0, 1));

        uf.union(0, 1);

        Assertions.assertTrue(uf.connected(0, 1));
    }

    @Test
    void unionAll() {
        UnionFind uf = new UnionFind(7);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        Assertions.assertTrue(uf.connected(0, 1));
        Assertions.assertTrue(uf.connected(0, 2));
        Assertions.assertTrue(uf.connected(0, 3));

        Assertions.assertFalse(uf.connected(0, 4));
        Assertions.assertFalse(uf.connected(0, 5));
        Assertions.assertFalse(uf.connected(0, 6));
    }

    @Test
    void unionTwoComponentsByRoots() {
        UnionFind uf = new UnionFind(8);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        uf.union(4, 5);
        uf.union(4, 6);
        uf.union(4, 7);

        Assertions.assertFalse(uf.connected(0, 4));
        Assertions.assertFalse(uf.connected(0, 5));
        Assertions.assertFalse(uf.connected(0, 6));

        uf.union(0, 4);

        Assertions.assertTrue(uf.connected(0, 4));
        Assertions.assertTrue(uf.connected(0, 5));
        Assertions.assertTrue(uf.connected(0, 6));
    }

    @Test
    void unionTwoComponentsByDescendants() {
        UnionFind uf = new UnionFind(8);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        uf.union(4, 5);
        uf.union(4, 6);
        uf.union(4, 7);

        Assertions.assertFalse(uf.connected(0, 4));
        Assertions.assertFalse(uf.connected(0, 5));
        Assertions.assertFalse(uf.connected(0, 6));

        uf.union(3, 7);

        Assertions.assertTrue(uf.connected(0, 4));
        Assertions.assertTrue(uf.connected(0, 5));
        Assertions.assertTrue(uf.connected(0, 6));
    }

    @Test
    void unionChain() {
        UnionFind uf = new UnionFind(8);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);

        Assertions.assertFalse(uf.connected(0, 7));

        uf.union(1, 2);
        uf.union(5, 6);
        uf.union(3, 4);

        Assertions.assertTrue(uf.connected(0, 7));
    }

    @Test
    void numComponents() {
        UnionFind uf = new UnionFind(4);

        Assertions.assertEquals(4, uf.numComponents());

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        Assertions.assertEquals(1, uf.numComponents());
    }

    @Test
    void numComponentsTwoComponents() {
        UnionFind uf = new UnionFind(8);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        uf.union(4, 5);
        uf.union(4, 6);
        uf.union(4, 7);

        Assertions.assertEquals(2, uf.numComponents());

        uf.union(0, 4);

        Assertions.assertEquals(1, uf.numComponents());
    }

    @Test
    void numComponentsReunion() {
        UnionFind uf = new UnionFind(4);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        uf.union(0, 1);
        uf.union(0, 1);
        uf.union(0, 1);

        uf.union(2, 3);
        uf.union(3, 1);
        uf.union(1, 1);

        Assertions.assertEquals(1, uf.numComponents());
    }

    @Test
    void sizeComponent() {
        UnionFind uf = new UnionFind(4);

        Assertions.assertEquals(1, uf.sizeComponent(0));

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        Assertions.assertEquals(4, uf.sizeComponent(0));
    }

    @Test
    void sizeComponentUnionTwoComponents() {
        UnionFind uf = new UnionFind(8);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        uf.union(4, 5);
        uf.union(4, 6);
        uf.union(4, 7);

        uf.union(0, 4);

        Assertions.assertEquals(8, uf.sizeComponent(0));
    }

    @Test
    void sizeComponentReunion() {
        UnionFind uf = new UnionFind(8);

        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);

        uf.union(4, 5);
        uf.union(4, 6);
        uf.union(4, 7);

        uf.union(0, 4);

        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(0, 7);
        uf.union(0, 6);
        uf.union(0, 5);

        Assertions.assertEquals(8, uf.sizeComponent(0));
    }
}