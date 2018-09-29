package test.thread;

import org.junit.Test;
import test.TestBase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建
 */
public class TestThreadCreate  extends TestBase {

    //线程创建-继承Thread
    @Test
    public void testThread(){
        ThreadThread threadThread = new ThreadThread();

        threadThread.setName("此为继承Thread创建的线程");
        threadThread.start();
        logger.info("当前启动的线程为：" + Thread.currentThread().getName());
    }



    class ThreadThread extends Thread{
        @Override
        public void run() {
            super.run();
            logger.info("当前启动的线程为：" + Thread.currentThread().getName());
        }
    }

    //线程创建-实现Runnable
    @Test
    public void testRunnable(){
        RunnableThread runnableThread = new RunnableThread();
        Thread t1 = new Thread(runnableThread);

        t1.setName("此为实现Runnable创建的线程");
        t1.start();
        logger.info("当前启动的线程为：" + Thread.currentThread().getName());
    }

    class RunnableThread implements Runnable{

        @Override
        public void run() {
            logger.info("当前启动的线程为：" + Thread.currentThread().getName());
        }
    }

    //线程创建-通过Callable和FutureTask创建线程；可以捕获线程异常和线程结束返回的参数
    @Test
    public void testCallable() throws ExecutionException{
        //a:创建Callable接口的实现类 ，并实现Call方法
        //b:创建Callable实现类的实现，使用FutureTask类包装Callable对象，该FutureTask对象封装了Callable对象的Call方法的返回值
        //c:使用FutureTask对象作为Thread对象的target创建并启动线程
        //d:调用FutureTask对象的get()来获取子线程执行结束的返回值

        Callable<Integer> callable = new CallableThread();
        FutureTask<Integer> futureTask = new FutureTask(callable);
        Thread t2 = new Thread(futureTask);

        t2.setName("此为实现Callable接口通过FutureTask包装器来创建的线程");
        t2.start();
        logger.info("当前启动的线程为：" + Thread.currentThread().getName());

        try {
            int i = futureTask.get();
            logger.info("拿到线程：" + t2.getName() + ",返回的值为" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("testCallable---" ,e );
        }

    }

    class CallableThread implements Callable<Integer>{

        //重写call方法
        @Override
        public Integer call() throws Exception {
            logger.info("当前启动的线程为：" + Thread.currentThread().getName());
            return 1;
        }
    }

    //线程优先级继承-线程的优先级有继承关系，比如A线程中创建了B线程，那么（由A创建的，不包含其他线程创建的）B线程将和A具有相同的优先级。
    @Test
    public void testPriority(){
        PriorityThread2 priorityThread2 = new PriorityThread2();
        priorityThread2.setPriority(8);
        priorityThread2.start();

        PriorityThread1 priorityThread1 = new PriorityThread1();
        priorityThread1.start();
    }

    class PriorityThread1 extends Thread{
        @Override
        public void run() {
            Thread.currentThread().setName("PriorityThread1");
            super.run();
            logger.info("当前启动的线程为：" + Thread.currentThread().getName() + ",优先级为：" + Thread.currentThread().getPriority());
        }
    }

    class PriorityThread2 extends Thread{
        @Override
        public void run() {
            Thread.currentThread().setName("PriorityThread2");
            PriorityThread1 priorityThread1 = new PriorityThread1();
            priorityThread1.start();
            super.run();
            logger.info("当前启动的线程为：" + Thread.currentThread().getName() + ",优先级为：" + Thread.currentThread().getPriority());
        }
    }


}
