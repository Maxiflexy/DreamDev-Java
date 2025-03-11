package practice.exercise.day07.lesson01;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i = 1; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

class RunnableExample{
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(), "Thread-a");
        Thread t2 = new Thread(new MyRunnable(), "Thread-b");
        t1.start();
        t2.start();
    }
}