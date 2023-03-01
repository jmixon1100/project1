import java.util.ArrayList;

public class MyBlockingQueue<E> {
    private int size;
    private ArrayList<E> a;
    private MySemaphore s1;
    private MySemaphore s2;

    public MyBlockingQueue(int size) {
        this.size = size;
        this.a = new ArrayList<>(size);
        this.s1 = new MySemaphore(size);
        this.s2 = new MySemaphore(size);
    }

    public int getNumElements() {
        return a.size();
    }

    public int getFreeSpace() {
        return this.size - a.size();
    }

    public boolean isEmpty() {
        return a.isEmpty();
    }

    public synchronized void add(E p) {
        while (a.size() == this.size) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        s1.p();
        a.add(p);
        notify();
        s1.v();
    }

    public synchronized E remove() {
        while (a.isEmpty()) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        s2.p();
        E val = a.remove(0);
        notify();
        s2.v();

        return val;

    }

    @Override
    public String toString() {
        return a.toString();
    }
}
