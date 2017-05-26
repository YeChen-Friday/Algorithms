import edu.princeton.cs.algs4.StdOut;

public class LinkedQueueOfStrings {
    private Node first, last;
    
    public LinkedQueueOfStrings() {
        first = null;
        last = null;
    }
    
    private class Node{
        String item;
        Node next;
    }
    public boolean isEmpty() {
        return first == null;
    }
    
    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
    }
    
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        String item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkedQueueOfStrings lq = new LinkedQueueOfStrings();
        lq.enqueue("test");
        String item = lq.dequeue();
        StdOut.println(item);
        StdOut.println(lq.isEmpty());
        String item2 = lq.dequeue();
        StdOut.println(item2);
        StdOut.println(lq.isEmpty());
    }

}
