package Random;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteRev {

    public static class RevArray<A> extends ArrayList<A> {
        @Override
        public Iterator<A> iterator() {
            return new Iterator<A>() {
                private int ind = size() - 1;

                @Override
                public boolean hasNext() {
                    return ind >= 0;
                }

                @Override
                public A next() {
                    return get(ind--);
                }
            };
        }

    }

    public static <A> Iterator<A> iteRev(List<A> l){
        return new Iterator<A>() {
            private int ind = l.size()-1;

            @Override
            public boolean hasNext() {
                return ind>=0;
            }

            @Override
            public A next() {
                return l.get(ind--);
            }
        };
    }



    public static void main(String[] args) {

        List<Integer> l = new RevArray<>();

        for (int i =0 ; i<10; i++){
            l.add(i);
        }


        System.out.println(l);
        Iterator<Integer> it = iteRev(l);

        while (it.hasNext()){
            System.out.print(it.next());
        }

        /*altrimenti stampavi così
        for (Integer a: l) {
            System.out.print(a+" ");
        }*/



    }





}
