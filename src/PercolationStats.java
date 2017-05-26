import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] th;
    private int t;
    // perform trials independent experiments on an n by n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException("n <= 0 or trials <= 0");
        th = new double[trials];
        t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                p.open(row, col);
            }
            th[i] = (double) p.numberOfOpenSites()/(n*n);
            // th[i] = p.numberOfOpenSites();
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(th);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(th);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()/Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()/Math.sqrt(t));
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        // int n = 200;
        // int trials = 100;
        // StdOut.println("mean = ");
        // StdOut.println("stddev = ");
        
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println("mean = "+ ps.mean());
        StdOut.println("stddev = "+ ps.stddev());
        StdOut.println("95% confidence interval = "+ ps.confidenceLo() + ", "+ ps.confidenceHi());
    }
}
