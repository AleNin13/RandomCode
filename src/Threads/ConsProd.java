package Threads;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsProd{

    public static class Prod extends Thread{
        private List<String> l;

        public Prod(List<String> l) {
            this.l = l;
        }

        Random r = new Random();

        @Override
        public void run() {
            while (true){
                synchronized(l){
                    l.add(String.valueOf(r.nextInt(125)));
                    System.out.println("Produco ->" +l);
                }
                try{
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static class Cons extends Thread{
        private List<String> l;

        public Cons(List<String> l) {
            this.l = l;
        }

        Random r = new Random();

        @Override
        public void run() {
            while (true){
                synchronized(l){
                    if(!l.isEmpty()){
                        System.out.println("Consumo ->"+ l);
                        l.remove(l.size()-1);
                    }
                }
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();
        Cons c = new Cons(l);
        Prod p = new Prod(l);


        p.start();
        c.start();
        try{

            p.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}