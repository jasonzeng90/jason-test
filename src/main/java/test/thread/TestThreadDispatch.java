package test.thread;

import org.junit.Test;
import test.TestBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程调度
 */
public class TestThreadDispatch extends TestBase {

    @Test
    //join-主线程等待子线程的终止;在很多情况下，主线程生成并起动了子线程，如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，
    // 但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。
    public void join(){
        logger.info(Thread.currentThread().getName()+"主线程运行开始!");
        Thread1 mTh1=new Thread1("A");
        Thread1 mTh2=new Thread1("B");
        mTh1.start();
        mTh2.start();

        try {
            mTh1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("加入线程异常1",e);
        }
        try {
            mTh2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("加入线程异常2",e);
        }

        logger.info(Thread.currentThread().getName()+ "主线程运行结束!");
    }


    class Thread1 extends Thread{
        private String name;
        public Thread1(String name) {
            super(name);
            this.name=name;
        }
        public void run() {
            logger.info(Thread.currentThread().getName() + " 线程运行开始!");
            for (int i = 0; i < 5; i++) {
               logger.info("子线程"+name + "运行 : " + i);
                try {
                    sleep((int) Math.random() * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info(Thread.currentThread().getName() + " 线程运行结束!");
        }
    }


    @Test
    //CountDownLatch-倒计时锁（我们在玩LOL英雄联盟时会出现十个人不同加载状态，
    //但是最后一个人由于各种原因始终加载不了100%，于是游戏系统自动等待所有玩家的状态都准备好，才展现游戏画面。）
    public void countDownLatch(){
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(){
            public void run() {
                try {
                    Thread.currentThread().setName("A");
                    logger.info("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    logger.info("子线程"+Thread.currentThread().getName()+"执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            public void run() {
                try {
                    Thread.currentThread().setName("B");
                    logger.info("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    logger.info("子线程"+Thread.currentThread().getName()+"执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            logger.info("等待2个子线程执行完毕...");
            countDownLatch.await();
            logger.info("2个子线程已经执行完毕");
            logger.info("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    //CyclicBarrier-回环栅栏,通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
    public void cyclicBarrier(){
        final int THREAD_COUNT_NUM = 7;

        //设置第一个屏障点，等待召集齐7位法师
        CyclicBarrier callMasterBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                logger.info("7个法师召集完毕，同时出发，去往不同地方寻找龙珠！");
                summonDragon();
            }
        });
        //召集齐7位法师
        for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    logger.info("召集第" + index + "个法师");
                    callMasterBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    logger.error("异常",e);
                }
            }).start();
        }



    }

    /**
     * 召唤神龙：1、收集龙珠；2、召唤神龙
     */
    private void summonDragon() {
        final int THREAD_COUNT_NUM = 7;
        //设置第二个屏障点，等待7位法师收集完7颗龙珠，召唤神龙
        CyclicBarrier summonDragonBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                logger.info("集齐七颗龙珠！召唤神龙！");
            }
        });
        //收集7颗龙珠
        for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    logger.info("第" + index + "颗龙珠已收集到！");
                    summonDragonBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    logger.error("异常",e);
                }
            }).start();
        }
    }

    @Test
    //Semaphore- 信号量,Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
    //假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
    public void semaphore(){
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<N;i++){
            new Worker(i,semaphore).start();
        }

    }

    class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                logger.info("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                logger.info("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("异常",e);
            }
        }
    }

    @Test
    //Phaser-多阶段线程同步工具，把多个线程写作执行的任务划分成多个阶段（phase）；
    //使用Phaser类同步三个并发任务。这三个任务将在三个不同的文件夹及其子文件夹中查找过去24小时内修改过扩展为为.log的文件。这个任务分成以下三个步骤：
    //1、在执行的文件夹及其子文件夹中获取扩展名为.log的文件
    //2、对每一步的结果进行过滤，删除修改时间超过24小时的文件
    //3、将结果打印到控制台
    public void phaser(){
        Phaser phaser = new Phaser(3);

        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

        Thread systemThread = new Thread(system, "System");
        systemThread.start();
        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();
        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Terminated: %s\n", phaser.isTerminated());
    }


    class FileSearch implements Runnable {
        private String initPath;

        private String end;

        private List<String> results;

        private Phaser phaser;

        public FileSearch(String initPath, String end, Phaser phaser) {
            this.initPath = initPath;
            this.end = end;
            this.phaser=phaser;
            results=new ArrayList<>();
        }
        @Override
        public void run() {

            phaser.arriveAndAwaitAdvance();//等待所有的线程创建完成，确保在进行文件查找的时候所有的线程都已经创建完成了

            logger.info("%s: Starting.\n",Thread.currentThread().getName());

            // 1st Phase: 查找文件
            File file = new File(initPath);
            if (file.isDirectory()) {
                directoryProcess(file);
            }

            // 如果查找结果为false，那么就把该线程从Phaser中移除掉并且结束该线程的运行
            if (!checkResults()){
                return;
            }

            // 2nd Phase: 过滤结果，过滤出符合条件的（一天内的）结果集
            filterResults();

            // 如果过滤结果集结果是空的，那么把该线程从Phaser中移除，不让它进入下一阶段的执行
            if (!checkResults()){
                return;
            }

            // 3rd Phase: 显示结果
            showInfo();
            phaser.arriveAndDeregister();//任务完成，注销掉所有的线程
            logger.info("%s: Work completed.\n",Thread.currentThread().getName());
        }
        private void showInfo() {
            for (int i=0; i<results.size(); i++){
                File file=new File(results.get(i));
                logger.info("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
            }
            // Waits for the end of all the FileSearch threads that are registered in the phaser
            phaser.arriveAndAwaitAdvance();
        }
        private boolean checkResults() {
            if (results.isEmpty()) {
                logger.info("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),phaser.getPhase());
                logger.info("%s: Phase %d: End.\n",Thread.currentThread().getName(),phaser.getPhase());
                //结果为空，Phaser完成并把该线程从Phaser中移除掉
                phaser.arriveAndDeregister();
                return false;
            } else {
                // 等待所有线程查找完成
                logger.info("%s: Phase %d: %d results.\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
                phaser.arriveAndAwaitAdvance();
                return true;
            }
        }
        private void filterResults() {
            List<String> newResults=new ArrayList<>();
            long actualDate=new Date().getTime();
            for (int i=0; i<results.size(); i++){
                File file=new File(results.get(i));
                long fileDate=file.lastModified();

                if (actualDate-fileDate<TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)){
                    newResults.add(results.get(i));
                }
            }
            results=newResults;
        }
        private void directoryProcess(File file) {
            // Get the content of the directory
            File list[] = file.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i].isDirectory()) {
                        // If is a directory, process it
                        directoryProcess(list[i]);
                    } else {
                        // If is a file, process it
                        fileProcess(list[i]);
                    }
                }
            }
        }
        private void fileProcess(File file) {
            if (file.getName().endsWith(end)) {
                results.add(file.getAbsolutePath());
            }
        }
    }

}
