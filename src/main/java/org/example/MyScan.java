package org.example;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyScan {

    private static final int THREADS = 100;
    private static final int TIMEOUT = 100;
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 65535;
    private static final String HOST = "192.168.31.230";
    private static final String HOST1 = "tryamkin.netlify.app";

    public static void main(String[] args)  {
        // Create an ExecutorService with a fixed pool of 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        // Submit tasks for execution
        System.out.println("Scanning");
        for (int i = MIN_PORT; i <= MAX_PORT; i++) {
            final int portnumber = i;
            executorService.submit(() -> myscan(HOST, portnumber));
        }
        // Shutdown the ExecutorService to stop accepting new tasks
        executorService.shutdown();

    }

    private static void myscan(String host, int port) {
            var inetSocketAddr = new InetSocketAddress(host, port);
            try (var socket = new Socket()) {
                socket.connect(inetSocketAddr, TIMEOUT);
                System.out.printf(Thread.currentThread().getName() + " Host %s, port %d is opened\n" , host, port );
            } catch (IOException e) {
             throw new RuntimeException(e);
            }

        }
    }
