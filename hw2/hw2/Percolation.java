package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    // TODO: make private after done
    /* First dimension is rows, second is columns */
    private int[][] sites;
    private int numOfOpenSites;
    private WeightedQuickUnionUF connections;

    // create N-by-N grid, with all sites initially blocked
    // The constructor should take time proportional to N^2
    public Percolation(int N) {
        if (N <= 0) { throw new java.lang.IllegalArgumentException(); }
        else {
            /* all sites are initialized as  */
            sites = new int[N][N];
            numOfOpenSites = 0;
            /* last two are for virtual sites
            * N*N is the virtual top
            * N*N +1 is the virtual bottom*/
            connections = new WeightedQuickUnionUF(N * N + 2);
            /* Connect all the top-level sites to the virtual top */
            for(int i = 0; i < N; i++){
                connections.union(i, N * N);
            }
            /* Connect all the bottom-level sites to the virtual bottom */
            for(int i = (N - 1) * N; i < N * N; i++){
                connections.union(i, N * N + 1);
            }
        }

    }

    /* Converts a 2D-array representation of a space to a 1D number in WQUF */
    private int translateXYto1D(int row, int col) {
        return (row * sites.length) + col;
    }

    private boolean isValidIndex(int row, int col) {
        if ((row < 0 || row > sites[0].length - 1) ||(col < 0 || col > sites[1].length - 1)) {
            return false;
        }
        return true;
    }

    private List<Integer> checkAdjacentOpen(int row, int col) {
        List<Integer> openAdjacents = new ArrayList<>();

        RowColPair[] allAdjacents = new RowColPair[4];
        allAdjacents[0] = new RowColPair(row - 1, col);
        allAdjacents[1] = new RowColPair(row, col - 1);
        allAdjacents[2] = new RowColPair(row, col + 1);
        allAdjacents[3] = new RowColPair(row + 1, col);

        for (RowColPair p:
             allAdjacents) {

            int r = p.getRow();
            int c = p.getCol();

            if(this.isValidIndex(r, c)) {
                if (sites[r][c] > 0) {
                    openAdjacents.add(this.translateXYto1D(r, c));
                }
            }
        }

        return openAdjacents;
    }


    /** Open the site (row, col) if it is not open already
     * 1) check if valid index;
     * 2) check if open alrd;
     * 3) set to open;
     * 4) call union to all adjecent open sites
     * @param row site's row number
     * @param col site's column number
     */
    /* should take constant time plus a constant number of calls to the union-find
    methods union(), find(), connected(), and count(). */
    public void open(int row, int col) {
        if ((row < 0 || row > sites[0].length - 1) ||(col < 0 || col > sites[1].length - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            // Set to 1 to denote open
            sites[row][col] = 1;
            // Get the Weighted Quick Union index for row, col
            int wquEntryForRowCol = this.translateXYto1D(row, col);
            // Increase Number of Open Sites counter
            numOfOpenSites++;
            // Connect to all open adjacents
            List<Integer> openNeighbours= this.checkAdjacentOpen(row, col);
            for (int i:
                 openNeighbours) {
                if(!connections.connected(wquEntryForRowCol, i)) {
                    connections.union(wquEntryForRowCol, i);
                }
            }
        }
    }

    // is the site (row, col) open?
    /* should take constant time plus a constant number of calls to the union-find
    methods union(), find(), connected(), and count(). */
    public boolean isOpen(int row, int col) {
        if ((row < 0 || row > sites[0].length - 1) ||(col < 0 || col > sites[1].length - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return sites[row][col] > 0;

    }
    // is the site (row, col) full?
    /* should take constant time plus a constant number of calls to the union-find
    methods union(), find(), connected(), and count(). */
    public boolean isFull(int row, int col) {
        if ((row < 0 || row > sites[0].length - 1) ||(col < 0 || col > sites[1].length - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int currentRowColTo1D = this.translateXYto1D(row, col);
        int virtualTop = sites[0].length * sites[0].length;
        return connections.connected(currentRowColTo1D, virtualTop) && isOpen(row, col);
    }
    // number of open sites
    /* should take constant time*/
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }
    // does the system percolate?
    /* should take constant time plus a constant number of calls to the union-find
    methods union(), find(), connected(), and count(). */
    public boolean percolates() {
        // TODO: make sure there is no backwash after percolation.
        int virtualTop = sites[0].length * sites[0].length;
        int virtualBottom = virtualTop + 1;
        return connections.connected(virtualBottom, virtualTop);
    }

    /**
     * A class used to iterate over tuples of row & column
     */
    private final class RowColPair {
        private final int row;
        private final int col;

        public RowColPair(int first, int second) {
                this.row = first;
                this.col = second;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
