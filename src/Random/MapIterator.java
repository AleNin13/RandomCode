package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MapIterator {

    public static <T, S> Iterator<S> mapIterator(Iterator<T> it, Function<T,S> f){
        return new Iterator<S>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public S next() {
                return f.apply(it.next());
            }
        };
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            a.add(new Random().nextInt(100));
        }

        Iterator<Double> du = mapIterator(a.iterator(),(n)-> (double) (n*2));

        while (du.hasNext()){
            System.out.println(du.next());
        }
    }
}
