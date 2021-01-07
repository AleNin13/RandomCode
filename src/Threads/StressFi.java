package Threads;

import java.util.ArrayList;
import java.util.List;

public class StressFi {
    public static long fi(long n){
        if(n==0)
            return 0;
        return (n>2) ? (fi(n-2))+fi(n-1) : 1;
    }

    public static long getTime(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> st = new ArrayList<>();
        int ntr = Runtime.getRuntime().availableProcessors();
        long time;

        for (int i = 0; i < ntr; i++) {
            st.add(new Thread(()->fi(50)));
        }
        time= getTime();
        for (Thread a: st) {
            a.start();
        }
        for (Thread a: st) {
            a.join();
        }
        time= getTime()-time;

        System.out.println("Il tuo computer ha impiegato " + (float)time/1000 + " secondi per finire");
        System.out.println("Punteggio -> " + (10000*ntr-(double)time));
    }
}
