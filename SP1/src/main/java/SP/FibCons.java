/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SP;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Esben
 */
public class FibCons implements Runnable {

    boolean running = true;
    BlockingQueue<Integer> res;
    ArrayList<Integer> consumedRes = new ArrayList<>();

    public FibCons(BlockingQueue<Integer> res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (running) {
            Integer next = res.poll();
            if (next == null) {
                running = false;
                long sum = 0;
                for (int n = 0; n < consumedRes.size(); n++) {
                    System.out.println(n + "=" + consumedRes.get(n));
                    sum += consumedRes.get(n);
                }
                System.out.println("Sum="+sum);
            } else {
                System.out.println(next);
                consumedRes.add(next);
            }
        }
    }
}
