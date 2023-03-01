import java.util.ArrayList;

public class MyBlockingQueue<E> {
    private int size;
    private ArrayList<E> a;
    private MySemaphore s1;
    private MySemaphore s2;

    public MyBlockingQueue(int size) {
        this.size = size;
        this.a = new ArrayList<>(size);
        this.s1 = new MySemaphore(size-1);
        this.s2 = new MySemaphore(size-1);
    }

    public int getNumElements() {
        return a.size();
    }

    public int getFreeSpace() {
        return this.size - a.size();
    }

    public boolean isEmpty() {
        return a.size() == 0;
    }

    public void add(E p) {
        s1.p(); 
        a.add(p);
        s1.v();
    }

    public E remove() {
        s2.p();
        E val = a.remove(0);
        s2.v();
        return val;

    }

    @Override
    public String toString() {
        return a.toString();
    }
}
