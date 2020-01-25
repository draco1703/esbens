/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SP;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Esben
 */
public class FibTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BlockingQueue<Integer> nums = new ArrayBlockingQueue(11);
        BlockingQueue<Integer> res = new ArrayBlockingQueue(11);

        nums.add(4);
        nums.add(5);
        nums.add(8);
        nums.add(12);
        nums.add(21);
        nums.add(22);
        nums.add(34);
        nums.add(35);
        nums.add(36);
        nums.add(37);
        nums.add(42);

    }

    public static void starter(int prod, int cons, BlockingQueue<Integer> nums, BlockingQueue<Integer> res) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < prod; i++) {
            exec.execute(new FibProd(nums, res));
        }

        for (int i = 0; i < cons; i++) {
            exec.execute(new FibCons(res));
        }

        exec.shutdown();
    }

}

/**
 * 1) Threads are utilized for tasks that take up a long time and needs to be
 * run times in succession. When you make it parallel instead of sequential you
 * can cut out a lot of time, kind of like a sweatshop can make 100 t-shirts
 * faster than a single person would make 100 t-shirts
 *
 * 2) A race condition is when several threads are trying to perform the same
 * action on the same element. This can cause issues, although it's not
 * guaranteed that it will cause issues, as it's a timing issue. It can be
 * solved in many ways, but one of the simplest solutions is to add the
 * "synchronized" to the method.
 *
 * 3) Waiting times. The issue is that producers might produce faster than it's
 * getting consumed and will have to wait until the consumer is ready for more
 * results, or the other way around, that things get consumed at a much higher
 * rate and the producers can't keep up. This can be fixed by adjusting the
 * amount of producers or consumers to match the other since they can work from
 * different threads and not have all in sequence.
 *
 * 4) Waiting until a condition is true. IT's bad because it's simply wasting
 * the resources that could be executing other things.
 *
 * 5) The name really says it all. It's a queue with capabilities of blocking
 * and waiting for inserting and retrieving.
 *
 *
 *
 */
