package JavaExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EsameNin2 {

    public static class Consumer extends Thread{
        private List<Integer> l;

        public Consumer(List<Integer> l){
            this.l=l;
        }

        @Override
        public void run() {
            while (true){
                synchronized (l) {
                    if (!l.isEmpty()) {
                        l.remove(0);
                        System.out.println("Consumer -> " + l);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static class Producer extends Thread{
        private List<Integer> l;

        public Producer(List<Integer> l){
            this.l=l;
        }

        @Override
        public void run() {
            while (true){
                synchronized (l) {
                    l.add(new Random().nextInt(100));
                    System.out.println("Producer -> " + l);
                }
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        List<Producer> lp = new ArrayList<>();
        List<Consumer> lc = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            lp.add(new Producer(l));
            lc.add(new Consumer(l));
        }

        for (Consumer c: lc) {
            c.start();
        }
        for (Producer p: lp) {
            p.start();
        }

        try{
            for (Consumer c: lc) {
                c.join();
            }
            for (Producer p: lp) {
                p.join();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
