package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItePair {
    public static class Pair<A,B>{
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "[ " + first + ", " + second + " ]";
        }
    }

    public static <A> Iterator<Pair<A,A>> pairing(Iterator<A> it){
        return new Iterator<Pair<A, A>>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Pair<A, A> next() {
                A first = it.next();
                A second = null;
                if(it.hasNext()){
                    second=it.next();
                }
                return new Pair<A, A>(first,second);
            }
        };

    }

    public static void main(String[] args) {
        List<Integer> l =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i+1);
        }

        Iterator<Pair<Integer,Integer>> it = pairing(l.iterator());

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
