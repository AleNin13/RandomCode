package Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThrFatt {

    public static List<Long> factList(List<Long> l){
        List<Thread> th = new ArrayList<>();
        List<Long> end = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            final int a = i;
            Thread t = new Thread(()->end.add(fatt(l.get(a))));
            th.add(t);
        }
        for (Thread t: th ) {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return end;
    }

    public static long fatt(long n){
        return (n==0) ? 1 : n * fatt(n-1);
    }

    public static void main(String[] args) {
        List<Long> l = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            l.add((long) new Random().nextInt(20));
        }

        System.out.println(l);
        List<Long> results = factList(l);
        System.out.println(results);
    }
}
