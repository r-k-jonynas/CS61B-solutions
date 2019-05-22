public class UnionFind {
    public int[] parent;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    /* O(N) time in WQU*/
    public UnionFind(int n) {
        // TODO
        parent = new int[n];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parent.length || vertex < 0) {
            throw new IndexOutOfBoundsException("No such vertex exists");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        this.validate(v1);
        int root = this.find(v1);
        return Math.abs(parent[root]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    /* Aka Find */
    public int parent(int v1) {
        // TODO
        this.validate(v1);
        int ans = parent[v1];
        return ans;
    }

    /* O(log N) time in WQU*/
    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* O(log N) time in WQU*/
    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if(this.connected(v1, v2)) {
            // TODO

        } else {
            int root1 = find(v1);
            int root2 = find(v2);
            if (this.parent(root1) < this.parent(root2)) {
                parent[root1]--;
                parent[root2] = root1;
            } else if (this.parent(root1) > this.parent(root2)) {
                parent[root2]--;
                parent[root1] = root2;
            } else {
                parent[root2]--;
                parent[root1] = root2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        this.validate(vertex);
        int r = vertex;
        while(this.parent(r) >= 0) { // this line causes ArrayIndexOutOfBoundsException in parent()
            r = this.parent(r);
        }
        return r;
    }

}
