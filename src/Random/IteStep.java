package Random;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteStep {

    public static class StepArrayList<A> extends ArrayList<A>{
        private final int step;

        public StepArrayList(int step) {
            this.step = step;
        }

        @Override
        public Iterator<A> iterator() {
            return new Iterator<A>() {
                private int i = (step>0) ? 0 : size()-1;

                @Override
                public boolean hasNext() {
                    if (step>0) {
                        return i < size();
                    } else {
                        return i >= 0;
                    }
                }

                @Override
                public A next() {
                    A tmp = get(i);
                    i+=step;
                    return tmp;
                }
            };
        }
    }


    public static void main(String[] args) {
        List<Integer> l = new StepArrayList<>(2);
        List<Integer> l1 = new StepArrayList<>(-3);
        for (int i = 0; i < 30; i++) {
            l.add(i);
            l1.add(i+1);
        }

        System.out.println(l);
        System.out.println(l1);

    }
}
