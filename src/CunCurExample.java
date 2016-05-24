
import java.util.Random;
import java.util.concurrent.Callable;


/**
 * Процессы и потоки
 * 1 процесс - n потоков
 * 100 листов - 1 процессор работает - 3 процессора простивают - N времени -- 100 сеукнд
 * 100 листов - 4 процессора работают - 0 процессов простаивают - n*2, N\4
 */

public class CunCurExample {
    //100 $
// 20
// 120
    private final int SLEEP_TIME = 5;
    private final int REPEAT_TIME = 100;

    private static int usersOnSite;
    private static final Object monitor = new Object();

    public static void callMeinCon() {
        synchronized (monitor) {

            System.out.println(usersOnSite); //1
            usersOnSite = usersOnSite + 1; //2
        }
        int L = 1005000 + 1003330;
        long m = L * 100500 + 2;
        synchronized (monitor) {
            usersOnSite = usersOnSite + 1;
            usersOnSite = usersOnSite - 1;
        }
    }

    class THExample extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < REPEAT_TIME; i++) {
                callMeinCon();
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    Runnable runInterfaces = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < REPEAT_TIME; i++) {
                callMeinCon();
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Callable<Integer> callExample = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < REPEAT_TIME; i++) {
                System.out.println("from callable int:" + new Random(20).nextInt());
            }
            return 0;
        }
    };


        public synchronized void example () {
        THExample thExample = new THExample();
        Thread runnable = new Thread(runInterfaces);
        thExample.start();
        runnable.start();
        try {
            Thread.sleep(SLEEP_TIME * REPEAT_TIME * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("stop here");

    }
}
