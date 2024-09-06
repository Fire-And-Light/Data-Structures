class UnionFind {
    private int[] roots; // The index refers to the node, and the entry refers to the root node of the compoenent that the node is in
    private int[] sizes; // The size of the component that the node is in
    private int numComponents;

    UnionFind(int numNodes) {
        super();

        if (numNodes <= 0) {
            throw new IllegalArgumentException("The amount of nodes must be greater than 0");
        }

        this.roots = new int[numNodes];
        this.sizes = new int[numNodes];
        this.numComponents = numNodes;

        for (int i = 0; i < numNodes; i++) {
            this.roots[i] = i;
            this.sizes[i] = 1;
        }
    }

    int size() {
        return this.roots.length;
    }

    int sizeComponent(int node) {
        return this.sizes[this.find(node)];
    }

    int numComponents() {
        return this.numComponents;
    }

    boolean connected(int node1, int node2) {
        return this.find(node1) == this.find(node2);
    }

    void union(int node1, int node2) {
        int root1 = this.find(node1);
        int root2 = this.find(node2);

        if (root1 != root2) {
            if (this.sizeComponent(node1) >= this.sizeComponent(node2)) {
                this.roots[root2] = root1;
                this.sizes[root1] += this.sizes[root2];

            } else {
                this.roots[root1] = root2;
                this.sizes[root2] += this.sizes[root1];
            }

            this.numComponents--;
        }
    }

    String toStringArray() {

        return null;
    }

    private int find(int node) {
        if (node < 0 || node >= this.roots.length) {
            throw new IllegalArgumentException("The node must be greater than or equal to 0, and less than " + this.roots.length);
        }

        int start = node;

        while (node != this.roots[node]) {
            node = this.roots[node];
        }

        // Path compression
        while (start != node) {
            int temp = this.roots[start];
            this.roots[start] = node;
            start = temp;
        }

        return node;
    }
}