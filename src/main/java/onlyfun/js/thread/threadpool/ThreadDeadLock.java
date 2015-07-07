package onlyfun.js.thread.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/7/4.
 */
public class ThreadDeadLock {
    public  static ExecutorService exec = Executors.newCachedThreadPool();

    public class Caculator implements Callable<Integer> {

        public Integer call() throws Exception {
            Future<Integer> num1, num2;
            num1 = exec.submit(new LoadNumTask());
            num2 = exec.submit(new LoadNumTask());
            Integer result1 = num1.get();
            Integer result2 = num2.get();
            System.out.println("result1 = " + result1 + " result2 = " +result2);
            return result1 + result2;
        }
    }

    public class LoadNumTask implements Callable<Integer> {

        public Integer call() throws Exception {
            Random random = new Random();
            return random.nextInt(10);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Future<Integer> result = exec.submit(new ThreadDeadLock().new Caculator());
        System.out.println(result.get(10, TimeUnit.SECONDS));
        exec.shutdown();
    }
}
