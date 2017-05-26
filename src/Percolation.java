import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private WeightedQuickUnionUF uf;
    private int num;
    private boolean[] op;
    private int count;
    // create n by n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal argument n <= 0");
        } else {
            uf = new WeightedQuickUnionUF(n*n + 2);
            num = n;
            count = 0;
            op = new boolean[n*n];
//            for (int i = 0; i < n*n; i++) {
//                op[i] = 0;
//            }
        }
    }
    
    // open site(row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > num) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > num) throw new IndexOutOfBoundsException("col index out of bounds");
        if (!isOpen(row, col)) {
            int p = (col + (row - 1)* num) - 1;
            op[p] = true;
            count += 1;
            if ((col - 1) > 0 && isOpen(row, col - 1)) {
                uf.union(p, p - 1);
            }
            if ((col + 1) <= num && isOpen(row, col + 1)) {
                uf.union(p, p + 1);
            }
            if ((row - 1) > 0 && isOpen(row - 1, col)) {
                uf.union(p, p - num);
            }
            if ((row + 1) <= num && isOpen(row + 1, col)) {
                uf.union(p, p + num);
            }
            if (row == 1) uf.union(p, num*num);
            // this introduced backwash problem
            if (row == num) uf.union(p, num*num + 1);
        }
    }
    // is site(row, col) open
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > num) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > num) throw new IndexOutOfBoundsException("col index out of bounds");
        int p = (col + (row - 1)* num) - 1;
        return op[p];
    }
    
    // is site(row, col) full
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > num) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > num) throw new IndexOutOfBoundsException("col index out of bounds");
        if (!isOpen(row, col)) {
            return false;
        } else {
            int p = (col + (row - 1)* num) - 1;
            // return uf.connected(p, num*num) && uf.connected(p, num*num +1);
            return uf.connected(p, num*num);
        }
    }
   
    // number of open sites
    public int numberOfOpenSites() {
        return count;
    }
    
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(num*num, num*num +1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
