package D1;

/**
 *
 * @author Esben
 */
public class Ex1 {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            long res = 0;
            for (int i = 1; i <= 1000000000l; i++) {
                res += i;
                System.out.println("T1:" + res);
            }
        });

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 4; i++) {
                    System.out.println("t2: " + i + 1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(new Ten());

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(10000);
        
        t3.interrupt();
    }

    static class Ten implements Runnable {

        int num = 10;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("t3: " + num);
                num++;
            }
        }
    }
}
