/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package D1;

/**
 *
 * a) Yes. After 53 attempts it finally fucked up
 *
 * b) From further attempts very rarely. Second time came after an additional 72
 * tries. While two errors arent't good enough for a statistical basis, it's too
 * rare for me to get 100 examples with a tight time constraint
 *
 * c) SyncEven
 * 
 * d) Adding "synchronized" to the method will fix this. It makes it impossible for more than one thread to molest the object at one time
 *
 * @author Esben
 */
public class Ex3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runEven task = new runEven();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

    }

    static class runEven implements Runnable {

        int counter;
        final SyncEven even = new SyncEven();

        @Override
        public void run() {
            for (counter = 1; counter < 1000; counter++) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }
                System.out.println(even.next());
            }
        }
    }

    public static class Even {
        private int n = 0;
        public int next() {
            n++;
            n++;
            return n;
        }
    }

    public static class SyncEven {
        private int n = 0;
        public synchronized int next() {
            n++;
            n++;
            return n;
        }
    }
}
