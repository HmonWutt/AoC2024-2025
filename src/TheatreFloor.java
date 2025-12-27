import javax.management.MBeanAttributeInfo;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class TheatreFloor {
    static HashMap<Integer,Integer> bigYtoSmall = new HashMap<>();
    static HashMap<Integer,Integer> bigXtoSmall = new HashMap<>();
    static HashMap<Integer,Integer> smallXtoBig = new HashMap<>();
    static HashMap<Integer,Integer> smallYtoBig= new HashMap<>();
    static Map<Integer, List<Integer>> rowColsRange = new HashMap<>();
    static Map<Integer, List<Integer>> colRowsRange= new HashMap<>();
    static int xLimit =0;
    static int yLimit = 0;

    public static ArrayList<Point> mapAllTiles(ArrayList<String> redTileCoordinates){
        ArrayList<Point> allRedTiles = new ArrayList<>();
        for (String redTile: redTileCoordinates){
            String[] xy = redTile.split("," );
            int x = Integer.parseInt(xy[1]);
            int y = Integer.parseInt(xy[0]);
            allRedTiles.add(new Point(x,y));
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

    private static ArrayList<List<Integer>> mapOuterBound(List<int[]> shrunkTiles) {
        HashSet<List<Integer>> outerBound = new HashSet<>();
        int len = shrunkTiles.size();
        int counter =0;
        for (int i = 0; i < len-1   ; i++) {
                int[] first = shrunkTiles.get(i);
                int[] second = shrunkTiles.get(i + 1);
                int firstX = first[0];
                int firstY = first[1];
                int secondX = second[0];
                int secondY = second[1];
                if (firstX == secondX) {
                    int min = Math.min(firstY, secondY);
                    int max = Math.max(firstY, secondY);
                    for (int y = min; y <=max; y++) {
                        outerBound.add(new ArrayList<>(List.of(firstX, y)));
                    }
                    counter++;

                }
                if (firstY == secondY) {
                    int min = Math.min(firstX, secondX);
                    int max = Math.max(firstX, secondX);
                    for (int k = min; k <=max; k++) {
                        outerBound.add(new ArrayList<>(List.of(k, firstY)));
                    }
                    counter++;
                }
                /*******************************find and close the gap*******************************/
                if (firstX != secondX && secondY!=firstY) {
                    System.out.println("DId not fit: "+"("+firstX+","+firstY+")"+"("+secondX+","+secondY+")");
                    int min = Math.min(firstX-1, secondX);
                    int max = Math.max(firstX-1, secondX);
                    for (int k = min; k <=max; k++) {
                        outerBound.add(new ArrayList<>(List.of(k, firstY)));
                    }
                }
            }
        int[] first = shrunkTiles.getFirst();
        int[] second = shrunkTiles.getLast();
        System.out.println(Arrays.toString(first));
        System.out.println(Arrays.toString(second));
        int firstX = first[0];
        int firstY = first[1];
        int secondX = second[0];
        int secondY = second[1];
        if (firstX == secondX) {
            int min = Math.min(firstY, secondY);
            int max = Math.max(firstY, secondY);
            for (int y = min; y <= max; y++) {
                outerBound.add(new ArrayList<>(List.of(firstX, y)));
            }

        }
        if (firstY == secondY) {
            int min = Math.min(firstX, secondX);
            int max = Math.max(firstX, secondX);
            for (int k = min; k <= max; k++) {
                outerBound.add(new ArrayList<>(List.of(k, firstY)));
            }
        }

        System.out.println("count "+counter);
        System.out.println("xlimit"+xLimit);
        System.out.println("ylimit"+yLimit);

       return new ArrayList<>(outerBound);
    }

    private static HashSet<List<Integer>> floodFill(List<List<Integer>> outerBound){
        Queue<List<Integer>> queue = new ArrayDeque<>();
        HashSet<List<Integer>> outside = new HashSet<>();
        queue.add(new ArrayList<>(List.of(0, 0)));
        while (!queue.isEmpty()) {
            List<Integer> current = queue.poll();
            int currentX = current.getFirst();
            int currentY = current.getLast();
            if (!outerBound.contains(new ArrayList<>(List.of(currentX,currentY))) && !outside.contains(List.of(currentX,currentY))&&currentX>=0 && currentX<xLimit+10 && currentY>=0 && currentY <yLimit+10){
                outside.add(new ArrayList<>(List.of(currentX, currentY)));
                queue.add(new ArrayList<>(List.of(currentX -1, currentY)));
                queue.add(new ArrayList<>(List.of(currentX + 1, currentY)));
                queue.add(new ArrayList<>(List.of(currentX, currentY - 1)));
                queue.add(new ArrayList<>(List.of(currentX, currentY + 1)));
            }

        }

        for (int x = 0; x <=xLimit; x++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int y = 0; y <= yLimit ; y++){
                if (outside.contains(new ArrayList<>(List.of(x,y)))){
                    stringBuilder.append(".");
                }
                else{
                    stringBuilder.append("X");
                }

//                System.out.println(x+","+y);
            }
            System.out.println(stringBuilder.toString());
        }
        return outside;
    }
//
    private static void getMinMax (List<int[]>allValidTiles){
        Map<Integer, List<Integer>> rowCols= new HashMap<>();
        Map<Integer, List<Integer>> colRows= new HashMap<>();
        for (int[]tile : allValidTiles) {
                rowCols.computeIfAbsent(tile[0], k -> new ArrayList<>())
                        .add(tile[1]);
            }
        for (int[]tile : allValidTiles) {
            colRows.computeIfAbsent(tile[1], k -> new ArrayList<>())
                    .add(tile[0]);
        }

        for (Integer key: rowCols.keySet()){
            List<Integer> value = rowCols.get(key);
            if (value.size()>2) {
//                System.out.println("row"+key+":More than 2 bounding points");
            }
            Integer min = Collections.min(value);
            Integer max = Collections.max(value);
            rowColsRange.put(key,new ArrayList<>(List.of(min,max)));
//            System.out.println("row: "+key+",col: ("+min+","+max+")");
        }


    }
    private static List<int[]> shrink(ArrayList<String> redTiles){
        List<int[]> coordinates = new ArrayList<>();
        Set<Integer> xes = new HashSet<>();
        Set<Integer> yes = new HashSet<>();
        for (String redTile: redTiles){
            String[] xy = redTile.split("," );
            coordinates.add(new int[]{Integer.parseInt(xy[0]), Integer.parseInt(xy[1])});
            xes.add(Integer.parseInt(xy[0]));
            yes.add(Integer.parseInt(xy[1]));

        }

        List<int[]> shrunkCoordinates = new ArrayList<>();

        int x = 1;
        List<Integer> xesSorted = new ArrayList<>(xes);
        Collections.sort(xesSorted);
        List<Integer> yesSorted = new ArrayList<>(yes);
        Collections.sort(yesSorted);
        for (Integer xBig: xesSorted){
            TheatreFloor.bigXtoSmall.put(xBig,x);
            TheatreFloor.smallXtoBig.put(x,xBig);
            x ++;
        }
        int y = 1;
        for (Integer yBig: yesSorted){
            TheatreFloor.bigYtoSmall.put(yBig,y);
            TheatreFloor.smallYtoBig.put(y,yBig);
            y++;
        }
        for (int[]each:coordinates){
            shrunkCoordinates.add(new int[] {bigXtoSmall.get(each[0]),bigYtoSmall.get(each[1])});
        }

        xLimit = Collections.max(smallXtoBig.keySet());
        yLimit = Collections.max(smallYtoBig.keySet());

        return shrunkCoordinates;
//        return coordinates;

    }
    public static void run (ArrayList<String> input){
//        TheatreFloor.findBiggestSquareWithoutRestraint(input);
//        TheatreFloor.findBiggestSquareWithRestraint(input);
//        long max = TheatreFloor.getBiggestSquare(input);
//        System.out.println("Biggest squre: "+max);

        List<int[]> shrunkTiles = TheatreFloor.shrink(input);
        List<List<Integer>> outerBound = TheatreFloor.mapOuterBound(shrunkTiles);
        HashMap<Long, List<int[]>> sizeAndCoordinate = new HashMap<>();
        HashSet<List<Integer>> outside = TheatreFloor.floodFill(outerBound);
        for (int i = 0; i < shrunkTiles.size(); i++){
            for (int j=i+1; j < shrunkTiles.size();j++){
                boolean isClosed = true;
                boolean isClosed1 = true;
                int[] first = shrunkTiles.get(i);
                int[] second = shrunkTiles.get(j);
                int firstX = first[0];
                int firstY = first[1];
                int secondX = second[0];
                int secondY = second[1];
                int minX = Math.min(firstX,secondX);
                int maxX = Math.max(firstX,secondX);
                int minY= Math.min(firstY,secondY);
                int maxY = Math.max(firstY,secondY);
//                System.out.println("----------------"+"("+firstX+","+firstY+"),"+"("+secondX+","+secondY+")");
                for (int x = minX; x <=maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        if (outside.contains(List.of(x,y))){
                            isClosed = false;
                            break;
                        }
                    }
                    if (!isClosed) break;
                }

                if (isClosed ){
                    int side = Math.abs(firstX-secondX)+1;
                    int side2  = Math.abs(firstY-secondY)+1;
                    long size = side2 * side;
                    sizeAndCoordinate.put(size,List.of(first,second));
//                    System.out.println("("+firstX+","+firstY+"),"+"("+secondX+","+secondY+")");
                }
            }
        }
        Long max = 0L;
        for (Long size: sizeAndCoordinate.keySet()){
//            System.out.println("ends................"+"("+sizeAndCoordinate.get(size).getFirst()[0]+","+sizeAndCoordinate.get(size).getFirst()[1]+"),"+"("+sizeAndCoordinate.get(size).getLast()[0]+","+sizeAndCoordinate.get(size).getLast()[1]+")");
            max = Math.max(size,max);
        }
        List<int[]> coordinates = sizeAndCoordinate.get(max);
        for (int[] each: coordinates)
            {System.out.println("last: "+smallXtoBig.get(each[0])+"," +smallYtoBig.get(each[1]));}
        long sideA = (long) Math.abs(smallXtoBig.get(coordinates.getFirst()[0]) - smallXtoBig.get(coordinates.getLast()[0]))+1;
        long sideB = (long) Math.abs(smallYtoBig.get(coordinates.getFirst()[1])- smallYtoBig.get(coordinates.getLast()[1]))+1;
        System.out.println("Part two: "+sideA*sideB);

//        long sideA = Math.abs(coordinates.getFirst()[0]-coordinates.getLast()[0])+1;
//        long sideB = Math.abs(coordinates.getFirst()[1]-coordinates.getLast()[1])+1;
//
//        System.out.println("ends................"+"("+coordinates.getFirst()[0]+","+coordinates.getFirst()[1]+"),"+"("+coordinates.getLast()[0]+","+coordinates.getLast()[1]+")");
//        System.out.println(sideA*sideB);


    }

    record Point(int x, int y){};
}
