package practice.exercise.day07.lesson01;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void produce(int item) throws InterruptedException {
        int CAPACITY = 5;
        while (queue.size() == CAPACITY){
            wait();
        }

        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()){
            wait();
        }

        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notifyAll();
    }
}

class ProducerConsumerExample{

    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();

        Thread producer = new Thread( () -> {
            for(int i = 1; i <= 20; i++){
                try {
                    sharedQueue.produce(i);
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });


        Thread consumer = new Thread( () -> {
            for(int i = 1; i <= 20; i++){
                try {
                    sharedQueue.consume();
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }

}
