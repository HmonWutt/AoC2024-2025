import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;

public class TheatreFloor {
    static HashMap<Integer, HashSet<List<Integer>>> rowColList = new HashMap<>();
    static HashMap<Integer, HashSet<List<Integer>>> colRowList = new HashMap<>();
    static ArrayList<Point> lines = new ArrayList<>();

    public static ArrayList<Point> mapAllTiles(ArrayList<String> redTileCoordinates){
        ArrayList<Point> allRedTiles = new ArrayList<>();
        for (String redTile: redTileCoordinates){
            String[] xy = redTile.split("," );
            allRedTiles.add(new Point(Integer.parseInt(xy[1]), Integer.parseInt(xy[0])));
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

    private static HashSet<int[]> getAllValidTiles(ArrayList<String> redTiles) {
        List<Point> allRedTilesAsPoints = TheatreFloor.mapAllTiles(redTiles);

        HashSet<int[]> allValidTiles = new HashSet<>();
        int len = allRedTilesAsPoints.size();
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                    Point first = allRedTilesAsPoints.get(i);
                    Point second = allRedTilesAsPoints.get(j);
                    if (first.x == second.x) {
                        int min = Math.min(first.y, second.y);
                        int max = Math.max(first.y, second.y);
                        for (int y= min; y<= max; y++) {
                            allValidTiles.add(new int[]{first.x, y});
                        }
                    }
                    if (first.y == second.y) {
                        int min = Math.min(first.x, second.x);
                        int max = Math.max(first.x, second.x);
                        for (int k = min; k <= max; k++) {
                            allValidTiles.add(new int[]{k, first.y});
                        }
                    }
                }
            }

       return allValidTiles;
    }
    private static Map<Integer, List<Integer>> getMinMax (HashSet<int[]> allValidTiles){
        List <int[]> allValidTilesList = new ArrayList<int[]>(allValidTiles);
        Map<Integer, List<Integer>> rowCols = new HashMap<>();

        for (int[] tile : allValidTilesList) {
                rowCols.computeIfAbsent(tile[0], k -> new ArrayList<>())
                        .add(tile[1]);
            }


        return rowCols;
    }
    private static long getBiggestSquare(ArrayList<String> redTiles){
        ArrayList<Point> allRedTilesAsPoints = TheatreFloor.mapAllTiles(redTiles) ;
        allRedTilesAsPoints.sort(Comparator.comparingInt(Point::y));
        Point longest = allRedTilesAsPoints.getLast();
        allRedTilesAsPoints.sort(Comparator.comparingInt(Point::y));
        Point tallest = allRedTilesAsPoints.getLast();
        System.out.println("Longest: "+longest.x+","+longest.y);
        long sizes = 0L;
        HashSet<int[]> allValidTils = TheatreFloor.getAllValidTiles(redTiles);
        Map<Integer,List<Integer>> minMaxes = TheatreFloor.getMinMax(allValidTils);
        for (int i =0; i < redTiles.size();i+=1 ){
             for (int j = i+1; j < redTiles.size();j++){
                 Point first = allRedTilesAsPoints.get(i);
                 Point second = allRedTilesAsPoints.get(j);
                 if (first.x != second.x && first.y != second.y){
                     Point target  = new Point(first.x, second.y);
                     Point target1 = new Point(second.x, first.y);
                     boolean isBoundedOne = false;
                     boolean isBoundedTwo  = false;
                     List<Integer> minMax = minMaxes.get(target.x);
                     List<Integer> minMax1 = minMaxes.get(target1.x);
                     int min1 = Collections.min(minMax);
                     int min2 = Collections.min(minMax1);
                     int max1= Collections.max(minMax);
                     int max2 = Collections.max(minMax1);
                     if (target.y >= min1 && target.y<=max1) isBoundedOne = true;
                     if (target1.y >= min2 && target1.y<=max2) isBoundedTwo = true;
                     if (isBoundedOne & isBoundedTwo){
                             long sideA = abs(first.y() - second.y()) + 1;
                             long sideB = abs(first.x() - second.x()) + 1;
                             System.out.println("(" + first.y + "," + first.x + "),(" + second.y + "," + second.x + ")");
                             System.out.println(sideA * sideB);
                             sizes = Math.max(sizes, sideA * sideB);
                     }
                 }
             }
        }
        return sizes;
    }
    public static void run (ArrayList<String> input){
//        TheatreFloor.findBiggestSquareWithoutRestraint(input);
//        TheatreFloor.findBiggestSquareWithRestraint(input);
        long max = TheatreFloor.getBiggestSquare(input);
        System.out.println("Biggest squre: "+max);
    }

    record Point(int x, int y){};
}
