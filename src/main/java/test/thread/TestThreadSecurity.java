package test.thread;

import org.junit.Test;
import test.TestBase;


/**
 * 线程安全
 */
public class TestThreadSecurity extends TestBase {
    static int i = 0;
    static AccountingSyncRnunnbale instance = new AccountingSyncRnunnbale();

    //修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
    @Test
    public void testSynchronized1() throws Exception{
        AccountingSync instance=new AccountingSync();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logger.info("--------" + i + "------------");
    }

    //修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
    //同时创建了两个新实例AccountingSyncBad，然后启动两个不同的线程对共享变量i进行操作，但很遗憾操作结果是1452317而不是期望结果2000000，
    //因为上述代码犯了严重的错误，虽然我们使用synchronized修饰了increase方法，但却new了两个不同的实例对象，这也就意味着存在着两个不同的实例对象锁
    @Test
    public void testSynchronized2() throws Exception{
        //new新实例
        Thread t1=new Thread(new AccountingSync());
        //new新实例
        Thread t2=new Thread(new AccountingSync());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();

        logger.info("********" + i + "*********");

    }

    class AccountingSync implements Runnable {
        //共享资源(临界资源)

        public synchronized void increase() {
            i++;
        }

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                increase();
            }
        }
    }

    @Test
    //修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
    //由于synchronized关键字修饰的是静态increase方法，与修饰实例方法不同的是，其锁对象是当前类的class对象。
    // 注意代码中的increase4Obj方法是实例方法，其对象锁是当前实例对象，如果别的线程调用该方法，将不会产生互斥现象，毕竟锁对象不同，
    // 但我们应该意识到这种情况下可能会发现线程安全问题(操作了共享静态变量i)。
    public void testSynchronized3() throws Exception{
        //new新实例
        Thread t1=new Thread(new AccountingSyncClass());
        //new心事了
        Thread t2=new Thread(new AccountingSyncClass());
        //启动线程
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        logger.info("********" + i + "*********");
    }

    static class AccountingSyncClass implements Runnable {

        /**
         * 作用于静态方法,锁是当前class对象,也就是
         * AccountingSyncClass类对应的class对象
         */
        public static synchronized void increase() {
            i++;
        }

        /**
         * 非静态,访问时锁不一样不会发生互斥
         */
        public synchronized void increase4Obj() {
            i++;
        }

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                increase();
            }
        }

    }

    @Test
    //修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
    public void testSynchronized4() throws Exception{
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logger.info("********" + i + "*********");
    }


    static class AccountingSyncRnunnbale implements Runnable {

        @Override
        public void run() {
            //省略其他耗时操作....
            //使用同步代码块对变量i进行同步操作,锁对象为instance
            synchronized (instance) {
                for (int j = 0; j < 1000000; j++) {
                    i++;
                }
            }

//            //this,当前实例对象锁
//            synchronized(this){
//                for(int j=0;j<1000000;j++){
//                    i++;
//                }
//            }
//
//            //class对象锁
//            synchronized(AccountingSync.class){
//                for(int j=0;j<1000000;j++){
//                    i++;
//                }
//            }
        }
    }
}
