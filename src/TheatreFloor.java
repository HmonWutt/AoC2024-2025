import javax.swing.*;
import java.util.*;

import static java.lang.Math.abs;

public class TheatreFloor {

    static ArrayList<Point> allRedTiles = new ArrayList<>();
    static HashSet<Integer> sides = new HashSet<>();
    static PriorityQueue<Long> squaresOrderedByArea = new PriorityQueue<>(Comparator.comparingLong(x->x));
    public static void mapAllTiles(ArrayList<String> redTileCoordinates){
        for (String redTile: redTileCoordinates){
            String[] xy = redTile.split("," );
            TheatreFloor.allRedTiles.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), new HashSet<Point>(), new HashSet<Point>()));
        }
    }

    public static void findSameXAndSameY(ArrayList<String> redTiles){
         TheatreFloor.mapAllTiles(redTiles);
         for (int i=0; i < TheatreFloor.allRedTiles.size(); i++){
             for (int j = i+1; j < TheatreFloor.allRedTiles.size(); j++){
                 Point first = TheatreFloor.allRedTiles.get(i);
                 Point second = TheatreFloor.allRedTiles.get(j);
                 int sideA = abs(first.y() - second.y())+1;
                 int  sideB = abs(first.x() - second.x())+1;
                 TheatreFloor.squaresOrderedByArea.add((long)sideA*sideB);
                 }
             }

        Long max = Collections.max(TheatreFloor.squaresOrderedByArea);
        System.out.println("Biggest: "+ max);
    }

    public static PriorityQueue<Long> findBiggestSquare(ArrayList<String> redTiles, PriorityQueue<Long> sizes){
        TheatreFloor.mapAllTiles(redTiles);
        for (int i =0 ; i < allRedTiles.size()-2; i +=1){
                Point first = (Point) allRedTiles.get(i);
                Point second = (Point) allRedTiles.get(i+1);
                Point third = (Point) allRedTiles.get(i+2);
                if (first.x == second.x ){
                    if (first.y  > second.y ) {
                        if (third.x < first.x) {
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x <= third.x && target.y >= first.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                        else{
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x >= third.x && target.y >= first.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                    }

                    if (first.y < second.y ){
                        if (third.x < first.x) {
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x <= third.x && target.y <= first.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                        else{
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x >= third.x && target.y <= first.y) {
                                    int sideA = abs(first.y() - third.y());
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                    }
                }
                else if  (first.y == second.y){
                    if (first.x > third.x) {
                        if (third.y > first.y) {
                            //check x --, y --
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x <= first.x && target.y >= third.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                        else{
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x >= first.x && target.y <= third.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                    }
                    if (first.x < third.x) {
                        if (first.y > third.y) {
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x <= first.x && target.y >= third.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                        else{
                            for (int k = i + 3; k < allRedTiles.size(); k++) {
                                Point target = (Point) allRedTiles.get(k);
                                if (target.x <= first.x && target.y <= third.y) {
                                    int sideA = abs(first.y() - third.y()) ;
                                    int sideB = abs(first.x() - third.x()) ;
                                    sizes.add((long) sideA * sideB);
                                    break;
                                }
                            }
                        }
                    }
                }

        }

        return sizes;

    }

//    private static void calculateAllAreas(){
//        for (Tile each: TheatreFloor.allRedTiles){
//            for (Tile xSame: each.sameX()){
//                for (Tile ySame: each.sameY){
//                    int area = TheatreFloor.calculateArea(each,xSame,ySame);
//                    TheatreFloor.squaresOrderedByArea.add(area);
//                }
//            }
//        }
//    }

//    public static void getBiggestArea(ArrayList<String>allRedTiles){
//        TheatreFloor.mapAllTiles(allRedTiles);
//        TheatreFloor.findSameXAndSameY();
//        TheatreFloor.calculateAllAreas();
//        System.out.println(TheatreFloor.squaresOrderedByArea.peek());
//    }
//
//    private static int calculateArea(Tile start, Tile sameX , Tile sameY){
//        int sideA = abs(start.x  - sameX.x);
//        int sideB = abs(start.y - sameY.y);
//        return sideA * sideB;
//    }

    record Point(int x, int y, HashSet<Point> sameX, HashSet<Point> sameY){};
    record Square(int area, Point one, Point two, Point three){};
}
