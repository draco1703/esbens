/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SP;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Esben
 */
public class FibProd implements Runnable {

    boolean running = true;
    BlockingQueue<Integer> nums;
    BlockingQueue<Integer> res;

    public FibProd(BlockingQueue<Integer> nums, BlockingQueue<Integer> res) {
        this.nums = nums;
        this.res = res;
    }

    @Override
    public void run() {
        while (running) {
            Integer next = nums.poll();
            if (next == null) {
                running = false;
            } else {
                try {
                    res.put(fib(next));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
