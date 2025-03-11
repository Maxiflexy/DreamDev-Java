package practice.exercise.day07.lesson01;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueueExercise {

    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void produce(int item) throws InterruptedException {
        int CAPACITY = 5;

        if (queue.size() == CAPACITY) {
            System.out.println("Queue full, producer waiting...");
        }

        while (queue.size() == CAPACITY){
            wait();
            System.out.println("Producer notified and resumed, producing item: " + item);
        }

        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll();
    }

    public synchronized void consume(int consumerNumber) throws InterruptedException {
        if (queue.isEmpty()) {
            System.out.println("Queue empty, consumer " + consumerNumber + " waiting...");
        }
        while (queue.isEmpty()){
            wait();
            System.out.println("Consumer " + consumerNumber + " notified and resumed");
        }

        int item = queue.poll();
        System.out.println("Consumer" + consumerNumber + ": " + item);
        notifyAll();
    }
}

class ProducerConsumerExercise{
    public static void main(String[] args) {

        SharedQueueExercise sharedQueueExercise = new SharedQueueExercise();

        Thread producer = new Thread( () -> {
            for(int i = 1; i <= 20; i++){
                try {
                    sharedQueueExercise.produce(i);
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });


        Thread consumer1 = new Thread( () -> {
            for(int i = 1; i <= 10; i++){
                try {
                    sharedQueueExercise.consume(1);
                    Thread.sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });


        Thread consumer2 = new Thread( () -> {
            for(int i = 1; i <= 10; i++){
                try {
                    sharedQueueExercise.consume(2);
                    Thread.sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
