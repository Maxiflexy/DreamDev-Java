package practice.exercise.day07.lesson01;

public class MyThread extends Thread{

    @Override
    public void run(){
        for(int i = 1; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

}

class ThreadExample {
    public static void main(String[] args) {

        Thread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();

    }
}
