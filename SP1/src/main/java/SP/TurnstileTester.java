package SP;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TurnstileTester {

  static final int NUMBER_OF_TURNSTILES = 40;
  static Turnstile[] turnStiles = new Turnstile[NUMBER_OF_TURNSTILES];

  public static void main(String[] args) throws InterruptedException {
    //This is the shared Counter used by all turnstilles
    TurnstileCounter sharedCounter = new TurnstileCounter();

    for (int i = 0; i < NUMBER_OF_TURNSTILES; i++) {
      turnStiles[i] = new Turnstile(sharedCounter);
    }

    //This example uses a ThreadPool to handle threads
    ExecutorService es = Executors.newCachedThreadPool();

    for (int i = 0; i < NUMBER_OF_TURNSTILES; i++) {
      es.execute(turnStiles[i]);
    }

    es.shutdown();
    es.awaitTermination(10, TimeUnit.SECONDS);

    System.out.println("All turnstiles are done");
    //Print the updated value
    System.out.println(sharedCounter.getValue());
  }
}
/**
 * 1) My results is endning up at 37091 and not 40000 as was expected. 
 * While in the real world there might have been an issue of people jumping the turnstiles in this program it is a case of a race condition.
 * 
 * 2) There is a race condition in the TurnstileCounter. The incr() can be used by multiple at once.
 * 
 * 3) The issue is the incr() method in the TurnstileCounter class, which is molested by all the turnstiles at once.
 * A simple fix would be to make it synchronized

 */