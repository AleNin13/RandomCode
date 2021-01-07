package Random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Poli {
    public static class Point{
        public final double x;
        public final double y;

        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Poligono implements Iterable<Point>{
        private List<Point> myPoints = new ArrayList<>();

        public Poligono(List<Point> points){
            this.myPoints = points;
        }

        @Override
        public Iterator<Point> iterator() {
            return myPoints.iterator();
            /*return new Iterator<Point>() {
                int position = 0;
                @Override
                public boolean hasNext() {
                    return position <= myPoints.size() - 1;
                }

                @Override
                public Point next() {
                    Point currPoint = myPoints.get(position);
                    position++;
                    return currPoint;
                }
            };*/
        }
    }

    public static void m(int x){}
    public static void m(double x, double y){}

    public static void main(String[] args){
        Random rnd = new Random();
        List<Point> pointsList = new ArrayList<>();
        for( int i = 0 ; i < 4; ++i){
            pointsList.add(new Point(rnd.nextInt(10), rnd.nextInt(10)));
        }
        Poligono myPol = new Poligono(pointsList);
        for(Point p : myPol){
            System.out.println(String.format("x: %f, y: %f", p.x , p.y));
        }
    }
}
