package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UndJoin {
    public static class ThrSy extends Thread{
        private int n;
        private List<Integer> l;

        public ThrSy(int n, List<Integer> l) {
            this.n = n;
            this.l = l;
        }

        @Override
        public void run() {
            System.out.println("Thread "+ n +" partito");

            synchronized (l) {

                System.out.println("Sto lavorando con il Thread " + n);
                int s = new Random().nextInt(2000);
                System.out.println("Secondi " + n + " -> " + s*10);
                l.add(s*10);
                try {
                    Thread.sleep(s*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Ho finito di lavorare con il Thread " + n);
            }
            System.out.println("Thread " + n + " finito");

        }
    }


    public static void main(String[] args) throws InterruptedException {
        List<Thread> st = new ArrayList<>();
        List<Integer> l =new ArrayList<>();
        int ntr = 3;//Integer.parseInt(args[0]);
        long time;

        for (int i = 0; i < ntr; i++) {
            st.add(new ThrSy(i,l));
                    /*int finalI = i;
                    Thread(()->{
                System.out.println("Thread "+ finalI +" partito");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread "+ finalI +" finito");
            }));*/
        }
        time= System.currentTimeMillis();
        for (Thread a: st) {
            a.start();

        }
        for (Thread a: st) {
            a.join();
        }
        time= (System.currentTimeMillis())-time;

        System.out.println("Il tuo computer ha impiegato " + (float)time/1000 + " secondi per finire");
        System.out.println(l);
    }
}
