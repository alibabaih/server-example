package my.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by l-vasil on 20.01.15.
 */
public class Server30 implements HttpHandler {
    public static void main(String[] args) throws IOException {
        //look for ScheduleExecutorService, ForkJoinPool
        ExecutorService threadPool = new ThreadPoolExecutor
                (
                    4,
                    64,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(256) //threads push in queues
                );
        ServerSocket serverSocket = new ServerSocket(80);

        while (true) {
            final Socket socket = serverSocket.accept();
            //submit return Future, explain why
            threadPool.submit(new HttpHandler(socket));
        }

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
