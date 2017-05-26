import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Scanner;


public class Permutation {

    public static void main(String[] args) {
//        // TODO Auto-generated method stub
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int n = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        for (int i = 0; i < n; i++)
          {
              StdOut.println(rq.dequeue());
          }
    }
}