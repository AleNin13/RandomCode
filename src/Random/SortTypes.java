package Random;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SortTypes {
     public static <A extends Comparable<? super A>> void sort(List<A> l){

     }

     public static <A> void sort1(List<A> l , Comparator<? super A> c){
         l.sort(c);
     }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            l.add(new Random().nextInt(i+1));
        }
        System.out.println(l);

        sort(l);
        sort1(l, (o1, o2) -> o1-o2);
        System.out.println(l);

    }

}
