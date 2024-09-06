import java.util.HashSet;

class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        T item;
        Node left;
        Node right;

        Node(T item, Node left, Node right) {
            super();
            this.item = item;
            this.left = left;
            this.right = right;
        }

        Node(T item) {
            this(item, null, null);
        }
    }

    private int size;
    private HashSet<T> elements; // A fast way of checking whether or not an element exists
    private Node root;

    BinarySearchTree() {
        super();
        this.size = 0;
        this.elements = new HashSet<>();
        this.root = null;
    }

    int size() {
        return this.size;
    }

    boolean contains(T element) {
        return this.elements.contains(element);
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    void clear() {
        this.elements.clear();
        this.root = null;
        this.size = 0;
    }

    int height() {
        return this.height(this.root);
    }

    void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("The argument must not be null");
        }

        if (this.size == 0) {
            this.root = new Node(element);

        } else {
            Node current = this.root;

            while (true) {
                if (element.compareTo(current.item) == 0) {
                    return;
                }

                if (element.compareTo(current.item) < 0) {
                    if (current.left == null) {
                        current.left = new Node(element);
                        break;

                    } else {
                        current = current.left;
                    }

                } else {
                    if (current.right == null) {
                        current.right = new Node(element);
                        break;

                    } else {
                        current = current.right;
                    }
                }
            }
        }

        this.elements.add(element);
        this.size++;
    }

    boolean remove(T element) {
        boolean removed = this.removeFromBST(element);
        this.elements.remove(element);
        this.size--;

        return removed;
    }

    // Returns a string array of the elements in ascending order
    String toStringArray() {
        if (this.root == null) {
            return "[]";

        } else {
            String array = "";
            array += "[";
            array += this.toStringArrayTraversal(this.root);
            array = array.substring(0, array.length() - 2); // Remove comma and space
            array += "]";
            
            return array;
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;

        } else {
            return Math.max(this.height(node.left), this.height(node.right)) + 1;
        }
    }

    private boolean removeFromBST(T element) {
        if (this.elements.contains(element)) {
            Node current = new Node(null, this.root, null); // A dummy node just in case the root is removed
            Node child = null;

            // Find the node corresponding to the element
            while (child == null) {
                if (current.item == null || element.compareTo(current.item) < 0) {
                    if (current.left.item == element) {
                        child = current.left;

                    } else {
                        current = current.left;
                    }

                } else {
                    if (current.right.item == element) {
                        child = current.right;

                    } else {
                        current = current.right;
                    }
                }
            }

            // Remove the node
            if (child.left == null) {
                if (current.left == child) {
                    current.left = child.right;

                } else {
                    current.right = child.right;
                }

            } else if (child.right == null) {
                if (current.left == child) {
                    current.left = child.left;

                } else {
                    current.right = child.left;
                }

            } else { // "child" can be replaced with the right-most node of "child.left", or the left-most node of "child.right"
                Node rightMost = this.rightMost(child.left);
                this.removeFromBST(rightMost.item);
                rightMost.right = child.right;

                if (current.left == child) {
                    if (rightMost != child.left) {
                        rightMost.left = child.left;
                    }

                    current.left = rightMost;

                } else {
                    if (rightMost != child.left) {
                        rightMost.left = child.left;
                    }

                    current.right = rightMost;
                }
            }

            // If the element corresponds to the root node, then reset the root node
            if (element.compareTo(this.root.item) == 0) {
                this.root = current.left;
            }

            return true;

        } else {
            return false;
        }
    }

    private Node rightMost(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    // In-order traversal visits nodes in ascending order of elements of binary search trees
    private String toStringArrayTraversal(Node node) {
        if (node != null) {
            return this.toStringArrayTraversal(node.left)
                + node.item + ", "
                + this.toStringArrayTraversal(node.right);

        } else {
            return "";
        }
    }
}