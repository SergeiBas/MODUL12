import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {
    private int n;
    private int count;
    private Lock lock = new ReentrantLock();

    public FizzBuzz(int n) {
        this.n = n;
        this.count = 1;
    }

    private void fizz() {
        while (count < n) {
            lock.lock();
            if (count % 3 == 0 && count % 5 != 0 && count <= n) {
                print("fizz");
                count();
            }
            lock.unlock();
        }
    }

    private void buzz() {
        while (count < n) {
            lock.lock();
            if (count % 3 != 0 && count % 5 == 0 && count <= n) {
                print("buzz");
                count();
            }
            lock.unlock();
        }
    }

    private void fizzBuzz() {
        while (count < n) {
            lock.lock();
            if (count % 3 == 0 && count % 5 == 0 && count <= n) {
                print("fizzbuzz");
                count();
            }
            lock.unlock();
        }
    }

    private void number() {
        while (count <= n) {
            lock.lock();
            if (count % 3 != 0 && count % 5 != 0 && count <= n) {
                print(String.valueOf(count));
                count();
            }
            lock.unlock();
        }
    }

    private void print(String str) {
        System.out.print(str);
        if (count != n) System.out.print(", ");
    }

    private void count(){
        lock.lock();
        count++;
        lock.unlock();
    }


    public void programStart() {
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(() -> fizzBuzz.fizz());
        executor.execute(() -> fizzBuzz.buzz());
        executor.execute(() -> fizzBuzz.fizzBuzz());
        executor.execute(() -> fizzBuzz.number());
        executor.shutdown();
    }
}
