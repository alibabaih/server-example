package my.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created by l-vasil on 20.01.15.
 */
public class SimpleExecutorService {
    private final ThreadGroup group = new ThreadGroup("");
    private final Collection<Thread> workersPool = new ArrayList<Thread>();
    private final BlockingQueue<Callable> taskQueue;

    //можно ли поставить private static class Worker?
    private class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    //what's difference between — take, remove, poll, element, peek
                    Callable nextTask = taskQueue.take(); //method take blocking
                    nextTask.call();
                } catch (InterruptedException e) { //why InterruptedException
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public SimpleExecutorService(int threadGroup, final BlockingQueue<Callable> taskQueue) {
        this.taskQueue = taskQueue;
        for(int k = 0; k < threadGroup; k++) {
            Thread worker = new Thread(group, new Worker());
            worker.start();
            workersPool.add(worker);
        }
    }

    public <T> void submit (Collection<T> task) throws InterruptedException {
        taskQueue.put(task);

    }
    public void shutdown() {
        group.interrupt();
    }
}
