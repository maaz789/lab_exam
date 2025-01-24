
package javaapplication1;

class OddThread extends Thread {
    private final Object lock;
    
    public OddThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            for (int num = 1; num <= 20; num++) {
                if (num % 2 != 0) {
                    synchronized (lock) {
                        System.out.println("Odd: " + num);
                    }
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Error in OddThread: " + e.getMessage());
        }
    }
}

class EvenThread extends Thread {
    private final Object lock;

    public EvenThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            for (int num = 1; num <= 20; num++) {
                if (num % 2 == 0) { 
                    synchronized (lock) {
                        System.out.println("Even: " + num);
                    }
                    Thread.sleep(10000);
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Error in EvenThread: " + e.getMessage());
        }
    }
}

public class JavaApplication1 {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread oddThread = new OddThread(lock);
        Thread evenThread = new EvenThread(lock);

        oddThread.start();
        evenThread.start();

        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            System.err.println("Error in main thread: " + e.getMessage());
        }

        System.out.println("Both threads have completed.");
    }
}



