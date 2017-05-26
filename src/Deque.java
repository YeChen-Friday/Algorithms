import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next;
    }
    public Deque()                           // construct an empty deque
    {
        first = null;
        last = null;
        n = 0;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return n == 0;
    }
    public int size()                        // return the number of items on the deque
    {
        return n;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        n++;
    }
    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) 
            first = last;
        else 
            oldlast.next = last;
        n++;
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = last.item;
        n--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else {
            Node node = first;
            while (node.next.next != null)
            {
                node = node.next;
            }
            last = node;
            last.next = null;
        }
        return item;
        

    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Deque<String> dq = new Deque<String>();
        dq.addFirst("TEST 1");
        dq.addLast("test 2");
        dq.addFirst("test 0");
        dq.addLast("test 3");
        for (String s : dq)
            StdOut.println("1 " + s);
        dq.removeFirst();
        for (String s : dq)
            StdOut.println("2 " + s);
        dq.removeLast();
        for (String s : dq)
            StdOut.println("3 " + s);
        dq.removeLast();
        for (String s : dq)
            StdOut.println("4 " + s);
        // dq.removeLast();
        dq.removeFirst();
        for (String s : dq)
            StdOut.println("5 " + s);
        // dq.removeLast();
        // dq.removeFirst();
    }

}
