package my.server;

import com.sun.deploy.net.HttpUtils;
import sun.nio.cs.US_ASCII;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server10 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(81);
        while (true) {
                System.out.println("Wait for TCP connection.");
            Socket socket = serverSocket.accept(); //hang on in method (операция блокирующая)
                System.out.println("Get 1 connection.");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            //read request
            byte[] request =  HttpUtils.readRequestFully(in);


            System.out.println("------------------------");
                System.out.println(new String(request));
                System.out.println("------------------------");
            //write response
            byte[] response = new String("Hi everyone!").getBytes(); //convert in case to put to byte[]
            System.out.println(response);

            //socket.close();
        }
    }

}
//на каждый болкирующий вызов нужен поток

