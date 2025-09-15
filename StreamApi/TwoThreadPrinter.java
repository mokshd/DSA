public class TwoThreadPrinter {
    private static final int MAX = 10;
    private int current = 1;

    // synchronized print method: each thread calls this with its role (printOdd = true for odd-thread)
    public synchronized void printNumbers(boolean printOdd) {
        while (current <= MAX) {
            // wait while it's not this thread's turn (or exit if done)
            while (current <= MAX && (current % 2 == 0) == printOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (current > MAX) break; // re-check after wakeup
            System.out.println(Thread.currentThread().getName() + ": " + current);
            current++;
            notifyAll(); // wake the other thread
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadPrinter printer = new TwoThreadPrinter();

        Thread oddThread = new Thread(() -> printer.printNumbers(true), "Odd-Thread");
        Thread evenThread = new Thread(() -> printer.printNumbers(false), "Even-Thread");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();

        System.out.println("Done!");
    }
}
