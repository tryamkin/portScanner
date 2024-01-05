package org.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int THREADS = 10;
    private static final int TIMEOUT = 100;
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 65535;

    private static void scan(String host) {
        System.out.println("Scanning");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            for (int port = MIN_PORT; port <= MAX_PORT; port++) {
                var inetSocketAddr = new InetSocketAddress(host, port);
                try (var socket = new Socket()) {
                    socket.connect(inetSocketAddr, TIMEOUT);
                    System.out.printf("Host %s, port %d is opened\n", host, port);
                } catch (IOException ignored) {
                    //                System.out.println(ignored.getMessage());
                    System.out.println("Task " + port + " completed by thread: " + Thread.currentThread().getName());
                }
            }
        });

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("finish");

    }


    public static void main(String[] args) {
        //   scan( "192.168.31.230");
        scan("tryamkin.netlify.app");
    }
}