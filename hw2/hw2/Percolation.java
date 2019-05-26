package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    /* First dimension is rows, second is columns */
    private boolean[][] sites;
    private int numOfOpenSites;
    private WeightedQuickUnionUF connections;
    private WeightedQuickUnionUF connectedDirectlyToTop;
    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOTTOM;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        } else {
            /* all sites are initialized as  */
            sites = new boolean[N][N];
            numOfOpenSites = 0;
            /* last two are for virtual sites
            * N*N is the virtual top
            * N*N +1 is the virtual bottom*/
            connections = new WeightedQuickUnionUF(N * N + 2);
            VIRTUAL_TOP = N * N;
            VIRTUAL_BOTTOM = N * N + 1;
            connectedDirectlyToTop = new WeightedQuickUnionUF(N * N + 1);

            /* Connect all the top-level sites to the virtual top */
            for (int i = 0; i < N; i++) {
                connections.union(i, VIRTUAL_TOP);
                connectedDirectlyToTop.union(i, VIRTUAL_TOP);
            }
            /* Connect all the bottom-level sites to the virtual bottom */
            for (int i = (N - 1) * N; i < N * N; i++) {
                connections.union(i, VIRTUAL_BOTTOM);
            }
        }

    }

    /* Converts a 2D-array representation of a space to a 1D number in WQUF */
    private int translateXYto1D(int row, int col) {
        return (row * sites.length) + col;
    }

    private void isValidIndex(int row, int col) {
        if ((row < 0 || row > sites[0].length - 1) || (col < 0 || col > sites[0].length - 1)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private List<Integer> checkAdjacentOpen(int row, int col) {
        List<Integer> openAdjacents = new ArrayList<>();

        RowColPair[] allAdjacents = new RowColPair[4];
        allAdjacents[0] = new RowColPair(row - 1, col);
        allAdjacents[1] = new RowColPair(row, col - 1);
        allAdjacents[2] = new RowColPair(row, col + 1);
        allAdjacents[3] = new RowColPair(row + 1, col);

        for (RowColPair p : allAdjacents) {

            int r = p.getRow();
            int c = p.getCol();

            try {
                isValidIndex(row, col);
                if (sites[r][c]) {
                    openAdjacents.add(this.translateXYto1D(r, c));
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

            return openAdjacents;
    }


    public void open(int row, int col) {
        isValidIndex(row, col);

        if (!isOpen(row, col)) {
            // Set to 1 to denote open
            sites[row][col] = true;
            // Get the Weighted Quick Union index for row, col
            int wquEntryForRowCol = this.translateXYto1D(row, col);
            // Increase Number of Open Sites counter
            numOfOpenSites++;
            // Connect to all open adjacents
            List<Integer> openNeighbours = this.checkAdjacentOpen(row, col);
            for (int i: openNeighbours) {
                if (!connections.connected(wquEntryForRowCol, i)) {
                    connections.union(wquEntryForRowCol, i);
                }

                if (!connectedDirectlyToTop.connected(wquEntryForRowCol, i)) {
                    connectedDirectlyToTop.union(wquEntryForRowCol, i);
                }
            }
        }
    }


    public boolean isOpen(int row, int col) {
        isValidIndex(row, col);
        return sites[row][col];
    }


    public boolean isFull(int row, int col) {
        isValidIndex(row, col);
        int currentRowColTo1D = this.translateXYto1D(row, col);

        boolean condition = isOpen(row, col) &&
                connectedDirectlyToTop.connected(currentRowColTo1D, VIRTUAL_TOP);

        return condition;
    }


    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        if (this.numberOfOpenSites() < this.sites[0].length) {
            return false;
        }
        return connections.connected(VIRTUAL_TOP, VIRTUAL_BOTTOM);
    }

    /**
     * A class used to iterate over tuples of row & column
     */
    private final class RowColPair {
        private final int row;
        private final int col;

        private RowColPair(int first, int second) {
            this.row = first;
            this.col = second;
        }

        private int getRow() {
            return row;
        }

        private int getCol() {
            return col;
        }
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
