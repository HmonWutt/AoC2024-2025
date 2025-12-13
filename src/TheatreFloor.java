import javax.swing.*;
import java.util.*;

import static java.lang.Math.abs;

public class TheatreFloor {

    static HashSet<Integer> sides = new HashSet<>();
    static PriorityQueue<Long> squaresOrderedByArea = new PriorityQueue<>(Comparator.comparingLong(x->x));
    public static ArrayList<Point> mapAllTiles(ArrayList<String> redTileCoordinates){
        ArrayList<Point> allRedTiles = new ArrayList<>();
        for (String redTile: redTileCoordinates){
            String[] xy = redTile.split("," );
            allRedTiles.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), new HashSet<Point>(), new HashSet<Point>()));
        }
        return allRedTiles;
    }

    public static void findSameXAndSameY(ArrayList<String> redTiles){
         ArrayList<Point> allRedTiles = TheatreFloor.mapAllTiles(redTiles);
         for (int i=0; i < allRedTiles.size(); i++){
             for (int j = i+1; j < allRedTiles.size(); j++){
                 Point first = allRedTiles.get(i);
                 Point second = allRedTiles.get(j);
                 int sideA = abs(first.y() - second.y())+1;
                 int  sideB = abs(first.x() - second.x())+1;
                 TheatreFloor.squaresOrderedByArea.add((long)sideA*sideB);
                 }
             }

        Long max = Collections.max(TheatreFloor.squaresOrderedByArea);
        System.out.println("Biggest: "+ max);
    }

    public static PriorityQueue<Long> findBiggestSquare(ArrayList<String> redTiles, PriorityQueue<Long> sizes){
        ArrayList<Point> allRedTiles = TheatreFloor.mapAllTiles(redTiles);
        for (int i =0 ; i < allRedTiles.size(); i ++){

              for ( int j = i+1;j < allRedTiles.size() ; j++){
                  boolean isContainedOne = false;
                  boolean isContainedTwo = false;
                  Point first = allRedTiles.get(i);
                  Point second = allRedTiles.get(j);
                  if (first.y > second.y ){
                      for (int k =0; k < allRedTiles.size() && k!=j && k!=i; k++){
                          Point target = allRedTiles.get(k);
                          int biggerX = first.x;
                          int smallerX = second.x;
                          if (first.x > second.x){
                              biggerX = second.x;
                              smallerX = first.x;
                          }

                          if (target.x<= smallerX && target.y <= second.y ){
                              isContainedOne = true;
                          }
                          if (target.x >= biggerX && target.y >= first.y){
                              isContainedTwo = true;
                          }
                      }
                  }
                  if (first.y < second.y){
                      int biggerX = first.x;
                      int smallerX = second.x;
                      if (first.x > second.x){
                          biggerX = second.x;
                          smallerX = first.x;
                      }
                      for (int k =0; k < allRedTiles.size() && k!=j && k!=i; k++){
                          Point target = allRedTiles.get(k);
                          if (target.x>= biggerX && target.y <= first.y ){
                              isContainedOne = true;
                          }
                          if (target.x <= smallerX && target.y >= second.y){
                              isContainedTwo = true;
                          }
                      }
                  }
                  if (isContainedOne && isContainedTwo){
                      int sideA = abs(first.y() - second.y())+1;
                      int  sideB = abs(first.x() - second.x())+1;
                      System.out.println(first.x+","+first.y+"..."+second.x +","+second.y);
                      System.out.println(sideA+","+sideB);
                      sizes.add((long)sideA*sideB);
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
