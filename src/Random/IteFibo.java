package Random;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class IteFibo {
    public static class Ite implements Iterable<Integer> {
        private int size;

        public Ite(int size) {
            this.size = size;
        }


        @NotNull
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int i=0;
                @Override
                public boolean hasNext() {
                    return i!=size+1;
                }

                @Override
                public Integer next(){
                    return fi(i++);
                }
            };
        }
    }

    public static int fi(int n){
        if(n==0)
            return 0;
        return (n>2) ? (fi(n-2))+fi(n-1) : 1;
    }

    public static void main(String[] args) {
        int i=0;
        for (int n : new Ite(30)) {
            System.out.println(String.format("Il fibonacci di %d è -> %d",i++, n));
        }
    }
}