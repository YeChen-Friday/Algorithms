// import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class BruteCollinearPoints {
    private LineSegment[] ls = new LineSegment[1000];
    private int nos;
    private Point max, min;
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.NullPointerException();
        int length = points.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }
            }
        }
        for (int i = 0; i < length - 3; i++) {
            if (points[i] == null) throw new java.lang.NullPointerException();
            for (int j = i + 1; j < length - 2; j++) {
                if (points[j] == null) throw new java.lang.NullPointerException();
                if (points[i].compareTo(points[j]) > 0) {
                    max = points[i];
                    min = points[j];
                } else {
                    max = points[j];
                    min = points[i];
                }                    
                for (int m = j + 1; m < length - 1; m++) {
                    if (points[m] == null) throw new java.lang.NullPointerException(); 
                    if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[m])) continue;
                    if (max.compareTo(points[m]) < 0) {
                        max = points[m];
                    } else if (min.compareTo(points[m]) > 0) {
                        min = points[m];
                    }
                    for (int n = m + 1; n < length; n++) {
                        if (points[n] == null) throw new java.lang.NullPointerException();
                        if (points[i].slopeTo(points[n]) != points[i].slopeTo(points[m])) continue;
                        if (max.compareTo(points[n]) < 0) {
                            max = points[n];
                        } else if (min.compareTo(points[n]) > 0) {
                            min = points[n];
                        }
                        ls[nos] = new LineSegment(min, max);
                        nos++;
                    }
                }
            }
        }
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
     // read the n points from a file
        // In in = new In(args[0]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

}
