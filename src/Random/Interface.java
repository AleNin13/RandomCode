package Random;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Interface {

    public static class Animal{
        private int weight;

        public Animal(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static class Array<T> extends ArrayList<T> {
        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return ++i < 5;
                }

                @Override
                public T next() {
                    return get(i);
                }
            };
        }
    }

    public static void main(String[] args) {
        List<Animal> l = new Array<>();
        for (int i = 0; i < 10; i++) {
            l.add(new Animal(new Random().nextInt(40)));
        }
        for (Animal a : l) {
            System.out.println(a.getWeight());
        }
    }

}
