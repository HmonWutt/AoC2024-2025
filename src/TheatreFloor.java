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
    private static void shrink(ArrayList<String> redTiles){
        List<int[]> coordinates = new ArrayList<>();
        for (String redTile: redTiles){
            String[] xy = redTile.split("," );
            coordinates.add(new int[]{Integer.parseInt(xy[1]), Integer.parseInt(xy[0])});
        }
        HashMap <Integer, Integer> smallXToBig = new HashMap<>();
        HashMap <Integer, Integer> smallYToBig = new HashMap<>();
        Set<Integer> xes = new HashSet<>();
        Set<Integer> yes = new HashSet<>();
        List<int[]> shrunkCoordinates = new ArrayList<>();
        for (int[] each: coordinates){
            xes.add(each[0]);
            yes.add(each[1]);
        }
        int x = 0;
        for (Integer xBig: xes){
            smallXToBig.put(xBig,x);
            x +=1;
        }
        int y = 0;
        for (Integer yBig: yes){
            smallYToBig.put(yBig,y);
        }
        for (int[] each: coordinates){
            shrunkCoordinates.add(new int[]{smallXToBig.get(each[0]), smallYToBig.get(each[1])});
        }
        for (int[]each: shrunkCoordinates){
            System.out.println(Arrays.toString(each));
        }

    }
    public static void run (ArrayList<String> input){
//        TheatreFloor.findBiggestSquareWithoutRestraint(input);
//        TheatreFloor.findBiggestSquareWithRestraint(input);
//        long max = TheatreFloor.getBiggestSquare(input);
//        System.out.println("Biggest squre: "+max);
        TheatreFloor.shrink(input);

    }

    record Point(int x, int y){};
}
