public class DeadlockExample {
    // Define two shared resources as locks
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1: Holding Lock A...");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println("Thread 1: Waiting for Lock B...");
                synchronized (lockB) {
                    System.out.println("Thread 1: Acquired Lock B!");
                }
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 2: Holding Lock A...");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println("Thread 2: Waiting for Lock B...");
                synchronized (lockB) {
                    System.out.println("Thread 2: Acquired Lock B!");
                }
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
