package Random;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReverseString {


    public static void revString(List<String> l){
        for (int i=0;i<l.size();i++) {
            StringBuilder a = new  StringBuilder(l.get(i));
            l.set(i, a.reverse().toString());
        }
    }

    public static Iterator<String> revStrIterator(Iterator<String> it){
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                return new StringBuilder(it.next()).reverse().toString();
            }
        };
    }

    public static class RevStrArray extends ArrayList<String>{
        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int i=0;
                @Override
                public boolean hasNext() {
                    return i<size();
                }

                @Override
                public String next() {
                    return new StringBuilder(get(i++)).reverse().toString();
                }
            };
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();
        l.add("uno");
        l.add("due");
        l.add("tre");
        l.add("quattro");
        l.add("cinque");

        System.out.println(l);
        revString(l);
        System.out.println(l);
        revString(l);


        Iterator<String> rv = revStrIterator(l.iterator());

        while (rv.hasNext()){
            System.out.println(rv.next());
        }

        List<String> l1 = new RevStrArray();
        l1.add("uno");
        l1.add("due");
        l1.add("tre");
        l1.add("quattro");
        l1.add("cinque");

        for (String s: l1) {
            System.out.println(s);
        }
    }

}
