import java.util.concurrent.atomic.AtomicInteger;

public class TwoThreadPrinterAtomic {
    private static final int MAX = 10;
    private final AtomicInteger current = new AtomicInteger(1);

    public void printOdd() {
        while (true) {
            int num = current.get();
            if (num > MAX) break;
            if (num % 2 != 0) {
                if (current.compareAndSet(num, num + 1)) {
                    System.out.println(Thread.currentThread().getName() + ": " + num);
                }
            }
        }
    }

    public void printEven() {
        while (true) {
            int num = current.get();
            if (num > MAX) break;
            if (num % 2 == 0) {
                if (current.compareAndSet(num, num + 1)) {
                    System.out.println(Thread.currentThread().getName() + ": " + num);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadPrinterAtomic printer = new TwoThreadPrinterAtomic();

        Thread t1 = new Thread(printer::printOdd, "Odd-Thread");
        Thread t2 = new Thread(printer::printEven, "Even-Thread");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Done!");
    }
}
