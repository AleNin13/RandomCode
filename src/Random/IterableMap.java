package Random;



import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class IterableMap {

    public static <S,T> Iterable<S> mapIterator(Iterable<T> i , Function<T,S> f){
        return new Iterable<S>() {
            @Override
            public Iterator<S> iterator() {
                return new Iterator<S>() {
                    private Iterator<T> it = i.iterator();
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
        };
    }

    public static void main(String[] args) {
        Iterable<Double> it = mapIterator(List.of(1,2,3,4,5,6,7,8,9,10),(a)-> (double) a*a);
        for (Double n: it) {
            System.out.println(n);
        }
    }
}
