import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;
    public RandomizedQueue()                 // construct an empty randomized queue
    {   
        a = (Item[]) new Object[2];
        n = 0;
    }
    public boolean isEmpty()                 // is the queue empty?
    {
        return n == 0;
    }
    public int size()                        // return the number of items on the queue
    {
        return n;
    }
    
    private void resize(int capacity) {
        // a = java.util.Arrays.copyOf(a, capacity); this fails the timing test
        Item[] temp = (Item[]) new Object[capacity];
        for (int i =0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new java.lang.NullPointerException();
        if (n == a.length) {
            resize(2*a.length);
        }
        a[n++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int j = StdRandom.uniform(n);
        Item item = a[j];
        Item[] temp = (Item[]) new Object[a.length];
        for (int i = 0; i < n-1; i++)
        {
            if (i < j) {
                temp[i] = a[i];
            }
            else
            {
                temp[i] = a[i+1];
            }
        }
        a = temp;
        n--;
        if (n > 0 && n == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }
    public Item sample()                     // return (but do not remove) a random item
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int j = StdRandom.uniform(n);
        Item item = a[j];
        return item;
    }
    
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item> {
        private RandomizedQueue<Item> rq = new RandomizedQueue<Item>();

        public ReverseArrayIterator() {
            for (int i = 0; i < n; i++)
            {
                rq.enqueue(a[i]);
            }
        }

        public boolean hasNext() {
            return rq.size() > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return rq.dequeue();
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
//        StdOut.println(rq.isEmpty());
//        rq.enqueue(24);
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.isEmpty());
//        rq.enqueue(247);
//        StdOut.println(rq.dequeue());
//        StdOut.println(rq.size());
//        StdOut.println(rq.isEmpty());
//        rq.enqueue(360);
        rq.enqueue("test 1");
        rq.enqueue("test 2");
        rq.enqueue("test 3");
        rq.enqueue("test 4");
        rq.enqueue("test 5");
        rq.enqueue("test 6");
        rq.enqueue("test 7");
        rq.enqueue("test 8");
//        for (int i = 0; i < 9; i++){
//            String j = rq.sample();
//            StdOut.println(j);
//        }
//        StdOut.println("\n");
//        for (int i = 0; i < 8; i++){
//            String j = rq.dequeue();
//            StdOut.println(j);
//        }
//        StdOut.println("\n");
        for (String s: rq) {
            StdOut.println("outer " + s);
            StdOut.println("\n");
            for (String t : rq) {
                StdOut.println("inner " + t);
            }
        }
        
    }

}
