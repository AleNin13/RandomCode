package Random;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Game {

    public static abstract class Player{
        private final String name;
        private int hp;
        protected Weapon w;


        public Player(String name, int hp, Weapon w) {
            this.name = name;
            this.hp = hp;
            this.w  = w;
        }

        public String getName() {
            return name;
        }

        public int getHp() {
            return hp;
        }

        public void setW(Weapon w) {
            this.w = w;
        }

        public abstract void hit(Player p);
    }

    public static class Weapon implements Comparable<Weapon> {
        private final String name;
        public int damage;

        public Weapon(String name, int damage) {
            this.name = name;
            this.damage = damage;
        }

        private void updateW() {
            this.damage+=1;
        }

        @Override
        public String toString() {
            return "Weapon{" +
                    "name='" + name + '\'' +
                    ", damage=" + damage +
                    '}';
        }

        @Override
        public int compareTo(@NotNull Weapon o) {
            return this.damage-((Weapon) o).damage;
        }
    }

    public static class Enemy extends Player{

        public Enemy(String name, int hp, Weapon w) {
            super(name, hp, w);
        }


        @Override
        public void hit(Player p) {
            p.hp -= this.w.damage;
            System.out.println("Ememy "+ this.getName() + " hit "+ p.getName()+ " with a "+ w.name + " -> damage " + w.damage);
        }

        @Override
        public String toString() {
            return "Enemy{" +
                    "name='" + getName() + '\'' +
                    ", hp=" + getHp() +
                    ", w=" + w +
                    '}';
        }
    }

    public static class PlOne extends Player{

        public PlOne(String name, int hp, Weapon w) {
            super(name, hp, w);
        }

        @Override
        public void hit(Player p) {
            int d= this.w.damage+5;
            p.hp -= d;
            System.out.println("Player "+ this.getName() + " hit "+ p.getName()+ " with a "+ w.name + " -> damage " + d);
        }

        @Override
        public String toString() {
            return "PlOne{" +
                    "name='" + getName() + '\'' +
                    ", hp=" + getHp() +
                    ", w=" + w +
                    '}';
        }



    }

    public static class Mondo extends Thread{
        private final Player p;

        public Mondo(Player p) {
            this.p = p;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                if (p.hp - 5 > 0) {
                    p.hp -= 5;
                }
                else {
                    System.out.println(p.name + " is dead by World Damage!");
                    break;
                }
                try {
                    Thread.sleep(10000);
                    System.out.println("World Damage -5hp to "+ p );
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void changeWeapons(List<Player> l, List<Weapon> w){
        if(!l.isEmpty()){
            for (Player p : l) {
                p.setW(rndElem(w));
                System.out.println(p.getName().toUpperCase() + " change weapon -> " + p.w.name);
                if(new Random().nextInt()%3==0){
                    p.w.updateW();
                }
            }}
    }



    public static void battle(List<Player> l, List<Player> n, List<Weapon> w) throws InterruptedException {
        while (!l.isEmpty() && !n.isEmpty()) {
            rndElem(l).hit(rndElem(n));
            n.removeIf(player -> player.hp<=0);
            Thread.sleep(1000);
            if(n.isEmpty())
                continue;
            rndElem(n).hit(rndElem(l));
            l.removeIf(player -> player.hp<=0);
            Thread.sleep(3000);
            changeWeapons(l,w);
            shield(l);
            shield(n);
            Thread.sleep(new Random().nextInt(3000));
        }
    }

    private static void shield(List<Player> n) {
        if (!n.isEmpty())
            rndElem(n).hp +=5;
    }

    public static <T> T rndElem(List<T> n){
        return n.get(new Random().nextInt(n.size()));
    }


    public static void main(String[] args) {
        List<Weapon> lw = new ArrayList<>();
        List<Player> vill = new ArrayList<>();
        List<Player> good = new ArrayList<>();


        lw.add(new Weapon("Pistola", 5));
        lw.add(new Weapon("Granata", 10));
        lw.add(new Weapon("Ascia", 3));
        lw.add(new Weapon("Rpg", 15));
        lw.add(new Weapon("MG", 7));
        lw.add(new Weapon("Spada", 2));

        lw.sort(new Comparator<Weapon>() {  //Lambda ((o1,o2)->o1.damage-o2-damage)  ----- Comparator.comparingInt(o->o.damage)
            @Override
            public int compare(Weapon o1, Weapon o2) {
                return o1.damage-o2.damage;
            }
        });
        System.out.println(lw);

        vill.add(new Enemy("AleNin", 200, rndElem(lw)));
        vill.add(new Enemy("Bianchini", 200, rndElem(lw)));
        vill.add(new Enemy("Martina", 200, rndElem(lw)));
        good.add(new PlOne("Matteo", 150, rndElem(lw)));
        good.add(new PlOne("Laura", 150, rndElem(lw)));
        good.add(new PlOne("Daniele", 150, rndElem(lw)));
        System.out.println(vill);
        System.out.println(good);

        try {
            int i = 5;
            while(i>0){
                System.out.print("Battle start in "+ i-- +" second!\r");
                Thread.sleep(1000);
            }
            System.out.println("!BATTLE!");
            battle(vill,good,lw);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (good.isEmpty()) {
            System.out.println("Enemy team WIN!");
            System.out.println(vill);
        } else {
            System.out.println("Players team WIN!");
            System.out.println(good);
        }

    }
}
