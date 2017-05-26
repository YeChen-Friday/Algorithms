import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
 

public class FastCollinearPoints {
    private LineSegment[] ls = new LineSegment[1000];
    private int nos;
    
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null) throw new java.lang.NullPointerException();
        int length = points.length;
        Point[] a = new Point[length];
        for (int i = 0; i < length; i++) a[i] = points[i];
        int m, n;            
        for (int i = 0; i < length; i++) {
            if (points[i] == null) throw new java.lang.NullPointerException();
            sort(a);
            Arrays.sort(a, points[i].slopeOrder());
            double[] slope = new double[length - 1];
            for (int j = 1; j < length; j++) {
                double s = a[0].slopeTo(a[j]);
                if (s == Double.NEGATIVE_INFINITY) throw new java.lang.IllegalArgumentException();
                slope[j-1] = s;
            }
            m = 0; 
            n = 0;
            while (m < slope.length - 2) {
                boolean found = false;
                n = m + 2;
                int b = 0;
                if (slope[m] == slope[n]) {
                    while (n < slope.length && slope[m] == slope[n]) {
                        n++;
                    }
                    for (int j = m; j < n; j++) {
                        if (points[i].compareTo(a[j+1]) > 0) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        if (nos > ls.length - 2) {
                            LineSegment[] temp = new LineSegment[ls.length * 2];
                            for (int x = 0; x <= nos; x++) {
                                temp[x] = ls[x];
                            }
                            ls = temp;
                        }
                        ls[nos++] = new LineSegment(points[i], a[n]);                    
                    }
                    m = n;
                } else {
                    m++;
                }
            }
        }

    }
    private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a [i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
    
    private static void sort(Point[] a, Point[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    private static void sort(Point[] a) {
        Point[] aux = new Point[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public int numberOfSegments()        // the number of line segments
    {
        return nos;
    }
    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] res = new LineSegment[nos];
        for (int i = 0; i < nos; i++) {
            res[i] = ls[i];
        }
        return res;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
