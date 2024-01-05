package org.example;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecutorServiceExample {

    public static void main(String[] args) {
        // Create an ExecutorService with a fixed pool of 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        // Submit tasks for execution
        for (int i = 1; i <= 500; i++) {
            final int taskNumber = i;
            executorService.submit(() -> performTask(taskNumber));
        }

        // Shutdown the ExecutorService to stop accepting new tasks
        executorService.shutdown();
    }

    private static void performTask(int taskNumber) {
        System.out.println("Task " + taskNumber + " started by thread: " + Thread.currentThread().getName());

        // Simulate some work
        try {
            Thread.sleep(2000); // Simulating task execution time of 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task " + taskNumber + " completed by thread: " + Thread.currentThread().getName());
    }
}
