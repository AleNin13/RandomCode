package Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadEx {

    public static class Consumer extends Thread {
        private List<Double> pr;
        private List<Double> rs;

        public Consumer(List<Double> pr, List<Double> rs) {
            this.pr = pr;
            this.rs = rs;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (pr) {
                    if (!pr.isEmpty()) {
                        rs.add(pr.remove(0));
                        System.out.println("Consumer" + rs);
                    }
                }
                try {
                    Thread.sleep(300);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Producer extends Thread {
        private List<Double> pr;

        public Producer(List<Double> pr) {
            this.pr = pr;
        }

        @Override
        public void run() {
            while (true) {
                synchronized(pr) {
                    pr.add((double) new Random().nextInt(100));
                    System.out.println("Producer" + pr);
                }
                try {
                    Thread.sleep(200);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static class Killer extends Thread {
        private List<Double> pr;

        public Killer(List<Double> pr) {
            this.pr = pr;
        }

        @Override
        public void run() {
            while (true) {
                    try {
                        Thread.sleep(400);

                        if (!pr.isEmpty())
                            pr.remove(0);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

            }
        }

    }

    public static void main(String[] args) {
        List<Double> lcons = new ArrayList<>();
        List<Double> lprod = new ArrayList<>();

        Consumer cons = new Consumer(lprod,lcons);
        Producer prod = new Producer(lprod);
        Killer kill = new Killer(lcons);

        prod.start();
        cons.start();
        kill.start();

        try {
            prod.join();
            cons.join();
            kill.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
