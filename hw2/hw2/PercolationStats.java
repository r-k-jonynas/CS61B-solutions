package hw2;


import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    // TODO: make sure following variables are private;
    private PercolationFactory factory;
    private int[] means;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) { throw new java.lang.IllegalArgumentException(); }
        else
            factory = pf;
            means = new int[T];

            for(int i = 0; i < T; i++) {
                Percolation temp = factory.make(N);
                while(!temp.percolates()) {
                    int r1 = StdRandom.uniform(N * N);
                    int r2 = StdRandom.uniform(N * N);
                    temp.open(r1, r2);
                }
                means[i] = temp.numberOfOpenSites();
            }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(means);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = this.mean();
        double totalStdSum = 0;
        for(int i = 0; i < means.length; i++) {
            double tempDif = (means[i] - mean);
            totalStdSum += tempDif * tempDif;
        }
        double stdSquared = totalStdSum / (means.length - 1);
        return Math.sqrt(stdSquared);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = this.mean();
        double std = this.stddev();
        double sqrootOfT = Math.sqrt(this.means.length);
        return mu - (1.96 * std / sqrootOfT);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = this.mean();
        double std = this.stddev();
        double sqrootOfT = Math.sqrt(this.means.length);
        return mu + (1.96 * std / sqrootOfT);
    }
}
