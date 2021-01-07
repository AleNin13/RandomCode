package JavaExam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class EsameNin1 {
    public static class RevArray<A> extends ArrayList<A>{
        @Override
        public Iterator<A> iterator() {
             return new Iterator<A>() {
                 private int ind = size()-1;

                 @Override
                 public boolean hasNext() {
                     return ind>=0;
                 }

                 @Override
                 public A next() {
                     return get(ind--);
                 }
             };
        }


    }

    public static void main(String[] args) {

        List<Integer> l = new RevArray<>();

        for (int i =0 ; i<10; i++){
            int a = new Random().nextInt(100);
            System.out.print(a+" ");
            l.add(a);
        }
        System.out.println();
        Iterator<Integer> it = l.iterator();

        while (it.hasNext()){
            System.out.print(it.next()+" ");
        }

        /*altrimenti stampavi così
        for (Integer a: l) {
            System.out.print(a+" ");
        }*/

        

    }




}
