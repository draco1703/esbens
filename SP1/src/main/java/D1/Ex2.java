
package D1;

/**
 *
 * a) The output is quite random. And yes, I expected schedulers to work as they work
 * 
 * @author Esben
 */
public class Ex2 {

    
    public static void main(String[] args) {
        createNThreads(10);
    }

    public static void createNThreads(int n) {
        if (n > 0) {
            for (int threadCounter = 0; threadCounter < n; threadCounter++) {
                Thread t1 = new Thread(new SpamBot(), "T" + threadCounter+1);
                t1.start();
            }
        }
    }

    static class SpamBot implements Runnable {        
        @Override
        public void run() {
            for (int x = 1; x <= 100; x++) {
                System.out.println(Thread.currentThread().getName()+ " = " + x);
            }
        }
    }
}
