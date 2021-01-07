package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class IteBiFunction {

    public static <X, Y, Z> Iterator<Z> iteBiFunct(Iterator<X> it, Iterator<Y> it2, BiFunction<X,Y,Z> f){
        return new Iterator<Z>() {
            @Override
            public boolean hasNext() {
                return it.hasNext() && it2.hasNext();
            }

            @Override
            public Z next() {
                return f.apply(it.next(),it2.next());
            }
        };
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        List<Double> l2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            l.add(new Random().nextInt(100));
            l2.add((double)new Random().nextInt(100));
        }

        Iterator<String> it = iteBiFunct(l.iterator(),l2.iterator(), (a, b)-> "I numeri negli iterator sono -> " + a + " " + b);

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
