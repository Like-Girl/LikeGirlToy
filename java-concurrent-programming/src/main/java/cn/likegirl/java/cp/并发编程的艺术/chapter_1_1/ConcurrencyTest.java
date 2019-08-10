package cn.likegirl.java.cp.并发编程的艺术.chapter_1_1;

/**
 * @author LikeGirl
 * @version v1.0
 * @title: ConcurrencyTest
 * @description: TODO
 * @date 2019/5/6 10:15
 */
public class ConcurrencyTest {

    private static final long count = 10000001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    public static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency：" + time + "ms,b=" + b);
    }

    public static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial：" + time + "ms,b=" + b + ",a=" + a);
    }
}
