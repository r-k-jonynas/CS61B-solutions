package hw2;


import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    // TODO: make sure following variables are private;
    private PercolationFactory factory;
    private double[] thresholds;

    // TODO: fix array index out of bounds
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) { throw new java.lang.IllegalArgumentException(); }
        else
            factory = pf;
            thresholds = new double[T];

            for(int i = 0; i < T; i++) {
                Percolation temp = factory.make(N);
                while(!temp.percolates()) {
                    int r1 = StdRandom.uniform(N);
                    int r2 = StdRandom.uniform(N);
                    temp.open(r1, r2);
                }
                thresholds[i] = (double) temp.numberOfOpenSites() / (double) (N * N);
            }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = this.mean();
        double totalStdSum = 0;
        for(int i = 0; i < thresholds.length; i++) {
            double tempDif = (thresholds[i] - mean);
            totalStdSum += tempDif * tempDif;
        }
        double stdSquared = totalStdSum / (thresholds.length - 1);
        return Math.sqrt(stdSquared);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = this.mean();
        double std = this.stddev();
        double sqrootOfT = Math.sqrt(this.thresholds.length);
        return mu - (1.96 * std / sqrootOfT);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = this.mean();
        double std = this.stddev();
        double sqrootOfT = Math.sqrt(this.thresholds.length);
        return mu + (1.96 * std / sqrootOfT);
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(50, 10, new PercolationFactory());
        PercolationStats stats2 = new PercolationStats(50, 5, new PercolationFactory());

        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(stats.confidenceLow());
        System.out.println(stats.confidenceHigh());
        System.out.println("-------------");
        System.out.println(stats2.mean());
        System.out.println(stats2.stddev());
        System.out.println(stats2.confidenceLow());
        System.out.println(stats2.confidenceHigh());
    }

}
