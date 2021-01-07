package Threads;

import java.util.ArrayList;
import java.util.List;


public class StressPrimi {

    public static boolean primo(double n){
        for (int i=2;i<n;i++){
            if (n%i==0)
                return false;
        }
        return true;
    }

    public static class Prime extends Thread{
        private Pair<Double,Integer> pri;

        public Prime(Pair<Double, Integer> pri) {
            this.pri = pri;
        }

        @Override
        public void run() {
            while (pri.first<=200000){
                synchronized (pri){
                    if(primo(pri.first)){
                        pri.second++;
                    }
                    pri.first++;
                }

            }
        }
    }
    public static class Pair<A,B>{
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Da 0 a " + first + " ci sono " + second + " numeri primi";
        }
    }



    public static void main(String[] args) throws InterruptedException {
        List<Thread> st = new ArrayList<>();
        int ntr = 100;
        Pair<Double, Integer> pri = new Pair<>(2d,0);
        long time;

        for (int i = 0; i < ntr; i++) {
            st.add(new Prime(pri));
        }
        time= System.currentTimeMillis();
        for (Thread a: st) {
            a.start();
        }
        for (Thread a: st) {
            a.join();
        }
        time= (System.currentTimeMillis())-time;
        System.out.println(pri);
        System.out.println("Il tuo computer ha impiegato " + (float)time/1000 + " secondi per finire");
        //System.out.println("Punteggio -> " + ((pri.first/10)-(double)time));
    }
}
