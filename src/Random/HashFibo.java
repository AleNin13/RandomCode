package Random;

import java.util.HashMap;

public class HashFibo {
    public static int fib(int n){
        if(n==0)
            return 0;
        return (n>2) ? (fib(n-2))+fib(n-1) : 1;
    }

    public static class FibCache{
        private final HashMap<Integer,Integer> h;

        public FibCache() {
            this.h = new HashMap<>();
        }

        public int fibonacci(int k){
            if(k<=2){
                return 1;
            } else {
                if(h.containsKey(k)) {
                    return h.get(k);
                } else {
                    int ris = fib(k);
                    h.put(k,ris);
                    return ris;
                }
            }
        }
    }

    public static long fibFast(int n, FibCacheEx f){
        if(f.containsKey(n)) {
            System.out.print("Gia calcolato Fibo (" + n + ") -> ");
            return f.get(n);
        }
        if(n==0)
            return 0;
        return (n>2) ? (fibFast(n-2,f))+fibFast(n-1,f) : 1;
    }

    public static class FibCacheEx extends HashMap<Integer,Long> {

        public long fibonacci(int k){
            if(containsKey(k)) {
                System.out.print("Gia calcolato ("+ k +") -> ");
                return get(k);
            } else {
                long ris = fibFast(k,this);
                put(k,ris);
                return ris;
            }
        }
    }


    public static void main(String[] args) {
        FibCache f = new FibCache();
        for (int i = 0; i < 20; i++) {
            f.fibonacci(i);
        }

        System.out.println(f.fibonacci(20));



        FibCacheEx a = new FibCacheEx();
        Thread thCalc =new Thread(()-> {
            System.out.println("Il Thread calcolatore ha iniziato a calcolare");
            for (int i = 0; i <= 100; i++) {
                a.fibonacci(i);
                /*int finalI = i;
                new Thread(()-> a.fibonacci(finalI)).start();*/
            }
            System.out.println("Il Thread calcolatore ha finito");
        });
        thCalc.start();


        for (int i = 1; i < 101; i++) {
            System.out.println(i + ": " + a.fibonacci(i));
        }
    }

}
