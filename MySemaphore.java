/**
 * Implements a multi-token semaphore.
 * 
 * Authors:Jennica Joseph, Johnny Mixon
 */
public class MySemaphore {
    // the number of tokens available
    private int numTokens;

    public synchronized void p() {
        while (this.numTokens <= 0) {
            try {wait();} catch (Exception e) {}
        }
        this.numTokens--;
       
    }

    public synchronized void v() {
        this.numTokens++;
        if (this.numTokens >= 1)
            notify();
    }
    
    public MySemaphore(int numTokens) {
        this.numTokens = numTokens;
    }

    public int getNumTokens() {
        return numTokens;
    }

    @Override
    public String toString() {
        return Integer.toString(this.numTokens);
    }
}