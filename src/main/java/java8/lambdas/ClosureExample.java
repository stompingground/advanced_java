package java8.lambdas;

public class ClosureExample {

    static class Closure{
        private int mRes;

        /**
         * This factory method creates a closure that will run in a
         * background thread.
         *
         * @return The background thread reference
         */
        Thread makeThreadClosure(String string, int n) {
            return new Thread(() -> System.out.println(string + (mRes += n)));
        }
         Closure() throws InterruptedException{
             Thread t = makeThreadClosure("result = ", 10);
             t.start();
             t.join();
         }
        }
    public static void main(String[] args) throws InterruptedException {
      new Closure();
    }
}
