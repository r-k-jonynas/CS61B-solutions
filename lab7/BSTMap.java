import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        private Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
        }
    }

    /**
     * Wipe out all the items from BSTMap.
     */
    @Override
    public void clear() {
        this.root = null;
    }

    /** Check if the BSTMap contains the input key
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /** Return the value for the input key from this BSTMap
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    /** Helper for get
     */
    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        }
        if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.val;
        }
    }

    /**
     * @return the size of the BSTMap
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * Helper for size()
     * @return the size of the input node
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + node.size;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        keyAggregation(root, keys);
        return keys;
    }

    void keyAggregation(Node n, Set<K> set) {
        if (n != null) {
            keyAggregation(n.left, set);
            set.add(n.key);
            keyAggregation(n.right, set);
        }
    }

    /**
     * Delete the key-value pair (key, value) and return value.
     * @param key
     * @return null if key not in BSTMap. Otherwise, V value.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        root = remove(root, key);
        return value;
    }

    /**
     * Helper for remove
     */
    private Node remove(Node root, K key) {
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = remove(root.left, key);
            return root;
        }
        if (cmp > 0) {
            root.right = remove(root.right, key);
            return root;
        } else {
            /* - Deletion key has no children  */
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            /* - Deletion key has one child  */
            } else if (root.left == null && root.right != null) {
                root = root.right;
                return root;
            } else if (root.left != null && root.right == null) {
                root = root.left;
                return root;
            /*- Deletion key has two children */
            } else {
                Node successor = findMin(root.right);
                root.key = successor.key;
                root.val = successor.val;
                root.right = remove(root.right, root.key);
                return root;
            }
        }
    }
    /**
     * Alternative remove function. Removes key/value pair.
     * If such a pair isn't found, throws exception.
     */
    @Override
    public V remove(K key, V value) {
        V valFromMap = get(key);
        if (value.equals(valFromMap)) {
            root = remove(root, key);
            return value;
        } else {
            throw new InputMismatchException();
        }
    }

    /**
     * Returns the node with the smallest key within the input subtree.
    */
    private Node findMin(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    /**
     * Puts a key/value pair into BSTMap
     */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * Traverses a node to put a key/value pair into the BSTMap subtree
     * @param n node to be traversed
     * @param key input key
     * @param value input value
     * @return
     */
    private Node put (Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, value);
        } else if (cmp > 0) {
            n.right = put(n.right, key, value);
        } else {
            n.key = key;
            n.val = value;
        }
        return n;
    }

    /**
     * Prints inorder traversal of BSTMap
     */
    public void printInOrder() {
        printNode(root);
    }

    /**
     * Helper for inorder print
     */
    private void printNode(Node n) {
        if (n == null) {
            return;
        } else {
            printNode(n.left);
            System.out.print(n.key + ", " + n.val);
            System.out.print(" ");
            printNode(n.right);
        }
    }

    /**
     * Custom BSTMap iterator
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        Stack<Node> stack = new Stack<Node>();

        private BSTMapIterator() {
            pushToLeft(root);
        }

        private void pushToLeft(Node node) {
            if(node != null) {
                stack.push(node);
                pushToLeft(node.left);
            }
        }

        @Override
        public boolean hasNext() {
            if(stack.isEmpty()) {
                return false;
            } return true;
        }

        @Override
        public K next() {
            Node temp = stack.pop();
            pushToLeft(temp.right);
            return temp.key;
        }
    }

}
