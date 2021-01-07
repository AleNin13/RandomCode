package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class IteNoPair {
    public static class Pair<A,B>{
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "[ " + first + ", " + second + " ]";
        }
    }

    public static <A> Iterator<A> noPair(Iterator<Pair<A,A>> it){
        List<A> l = new ArrayList<>();
        while (it.hasNext()){
            Pair<A,A> tmp =it.next();
            l.add(tmp.first);
            l.add(tmp.second);
        }

        return new Iterator<A>() {
            private int i=0;

            @Override
            public boolean hasNext() {
                return i<l.size();
            }

            @Override
            public A next() {
                return l.get(i++);
            }
        };
    }

    public static void main(String[] args) {
        List<Pair<Integer,Integer>> l = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            l.add(new Pair<Integer, Integer>(new Random().nextInt(20),new Random().nextInt(20)));
        }
        Iterator<Integer> it = noPair(l.iterator());
        System.out.println(l);

        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }
    }

}
