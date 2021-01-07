package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ItePredicate {

    
    public static <A> Iterator<A> iteFilter(Iterator<A> it, Predicate<A> p){
        return new Iterator<A>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public A next() {
                A e = it.next();
                while (!p.test(e) && it.hasNext()){
                    e=it.next();
                }
                return p.test(e) ? e : null;
            }
        };
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            l.add(i);
        }
        Iterator<Integer> it = iteFilter(l.iterator(), i -> i%2==0);
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
