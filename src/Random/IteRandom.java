package Random;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class IteRandom {
    public static class RndList<A> extends ArrayList<A> {
        @NotNull
        @Override
        public Iterator<A> iterator() {
            return new Iterator<A>() {
                @Override
                public boolean hasNext() {
                    return !isEmpty();
                }

                @Override
                public A next() {
                    return RndList.this.remove(new Random().nextInt(size()));
                }
            };
        }
    }
    public static void main(String[] args) {

        List<Integer> list = new RndList<>();

        for (int i = 0; i < 30; i++) {
            list.add(i);
        }

        System.out.println(list);



    }
}
