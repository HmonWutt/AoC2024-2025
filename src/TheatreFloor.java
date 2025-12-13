import java.util.*;

import static java.lang.Math.abs;

public class TheatreFloor {

    public static ArrayList<Point> mapAllTiles(ArrayList<String> redTileCoordinates){
        ArrayList<Point> allRedTiles = new ArrayList<>();
        for (String redTile: redTileCoordinates){
            String[] xy = redTile.split("," );
            allRedTiles.add(new Point(Integer.parseInt(xy[1]), Integer.parseInt(xy[0]), new HashSet<Point>(), new HashSet<Point>()));
        }
        return allRedTiles;
    }

    private static void findBiggestSquareWithoutRestraint(ArrayList<String> redTiles){
        PriorityQueue<Long> sizes = new PriorityQueue<>();
         ArrayList<Point> allRedTiles = TheatreFloor.mapAllTiles(redTiles);
         for (int i=0; i < allRedTiles.size(); i++){
             for (int j = i+1; j < allRedTiles.size(); j++){
                 Point first = allRedTiles.get(i);
                 Point second = allRedTiles.get(j);
                 int sideA = abs(first.y() - second.y())+1;
                 int  sideB = abs(first.x() - second.x())+1;
                 sizes.add((long)sideA*sideB);
                 }
             }

        Long max = Collections.max(sizes);
        System.out.println("Day 9 part 1: "+ max);
    }

    private static void findBiggestSquareWithRestraint(ArrayList<String> redTiles){
        ArrayList<Point> allRedTiles = TheatreFloor.mapAllTiles(redTiles);
        PriorityQueue<Long> sizes = new PriorityQueue<>();
        for (int i =0 ; i < allRedTiles.size(); i ++){

              for ( int j = i+1;j < allRedTiles.size() ; j++){
                  boolean isContainedOne = false;
                  boolean isContainedTwo = false;
                  Point first = allRedTiles.get(i);
                  Point second = allRedTiles.get(j);
                  if (first.y  > second.y ){
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
//                      System.out.println(first.x+","+first.y+"..."+second.x +","+second.y);
//                      System.out.println(sideA+","+sideB);
                      sizes.add((long)sideA*sideB);
                  }
            }
        }
        System.out.println("Day 9 part 2: "+Collections.max(sizes));
    }

    public static void run (ArrayList<String> input){
        TheatreFloor.findBiggestSquareWithoutRestraint(input);
        TheatreFloor.findBiggestSquareWithRestraint(input);
    }

    record Point(int x, int y, HashSet<Point> sameX, HashSet<Point> sameY){};
}
