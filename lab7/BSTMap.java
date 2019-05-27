import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    private Node root;

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        private Node(K key, V val) {
            this.key = key;
            this.val = val;
            // Not sure about the next line.
            this.size = 1;
        }
    }
    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

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

    @Override
    public int size() {
        return size(root);
    }

    // TODO: Fix this
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + node.size;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

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


    public void printInOrder() {
        printNode(root);
    }

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

}
