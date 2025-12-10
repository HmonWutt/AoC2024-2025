import java.util.*;

import static java.lang.Math.abs;

public class TheatreFloor {

    static ArrayList<Tile> allRedTiles = new ArrayList<>();
    static HashSet<Integer> sides = new HashSet<>();
    static PriorityQueue<Long> squaresOrderedByArea = new PriorityQueue<>(Comparator.comparingLong(x->x));
    public static void mapAllTiles(ArrayList<String> redTileCoordinates){
        for (String redTile: redTileCoordinates){
            String[] xy = redTile.split("," );
            TheatreFloor.allRedTiles.add(new Tile(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), new HashSet<Tile>(), new HashSet<Tile>()));
        }
    }

    public static void findSameXAndSameY(ArrayList<String> redTiles){
         TheatreFloor.mapAllTiles(redTiles);
         for (int i=0; i < TheatreFloor.allRedTiles.size(); i++){
             for (int j = i+1; j < TheatreFloor.allRedTiles.size(); j++){
                 Tile first = TheatreFloor.allRedTiles.get(i);
                 Tile second = TheatreFloor.allRedTiles.get(j);
                 int sideA = abs(first.y() - second.y())+1;
                 int  sideB = abs(first.x() - second.x())+1;
                 TheatreFloor.squaresOrderedByArea.add((long)sideA*sideB);
                 }
             }

        Long max = Collections.max(TheatreFloor.squaresOrderedByArea);
        System.out.println("Biggest: "+ max);
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

    record Tile(int x, int y, HashSet<Tile> sameX, HashSet<Tile> sameY){};
    record Square(int area, Tile one, Tile two, Tile three){};
}
