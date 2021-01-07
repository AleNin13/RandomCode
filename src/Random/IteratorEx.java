package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class IteratorEx {

    public static <T,R> Iterator<R> funct(Iterator<T> l, Function<T,R> f) {

        return new Iterator<R>() {
               private int i = 0;

               @Override
               public boolean hasNext() {
                   return (l.hasNext());
               }

               @Override
               public R next() {
                   try {
                       T a = l.next();
                       return f.apply(a);
                   }
                   catch (RuntimeException e) {
                       System.err.println(String.format("exception caught: %s", e.getMessage()));
                       if (hasNext()) return next();
                       else throw e;
                   }
               }
           };
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            l.add(i);
        }

        Iterator<Double> it = funct(l.iterator(),(a) -> (double) a);

        while (it.hasNext()) {
            double a= it.next();
            System.out.println(a);
        }
        for (Integer i: l) {
            System.out.println(l.get(i));
        }
    }

}


