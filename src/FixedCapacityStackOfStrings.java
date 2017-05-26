

public class FixedCapacityStackOfStrings {
    
    private String[] s;
    private int N = 0;
    
    // a cheat to ask client to pass the capacity, stay tuned
    // overflow: use resizing array for array implementation(stay tuned)
    public FixedCapacityStackOfStrings(int capacity ){
        s = new String[capacity];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    // Null item: we allow null item to be inserted
    public void push(String item) {
        s[N++] = item;
    }
    
    // underflow: throw exception if pop from an empty stack
    // loitering: holding a reference to an object when it is no longer needed
    public String pop() {
        if (N == 0) throw new IndexOutOfBoundsException("stack is empty");
        return s[--N];
        // this version avoids loitering, garbage collector can reclaim memory only if no outstanding reference
        // String item = s[--N];
        // s[N] = null;
        // return item;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
