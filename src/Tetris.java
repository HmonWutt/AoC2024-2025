import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tetris {
    List<String[]> shapes = new ArrayList<>();
    List<Integer> areaSizes = new ArrayList<>();
    List<int[]> amountOfShapes  = new ArrayList<>();
    List<Integer> sizes = new ArrayList<>();
    Tetris(String input){
        String[] splited = input.split("\n\n");
        for (int i = 0 ; i < splited.length -1; i++){
            String eachShapeString= splited[i].split(":")[1];
            String[] eachShape = eachShapeString.strip().split("\n");
            shapes.add(eachShape);
        }
        String  secondHalf = splited[splited.length-1];
        String[] areaAndAmounts = secondHalf.split("\n");
        for (String each: areaAndAmounts){
            String [] areaAndAmount = each.split(":");
            String[] area = areaAndAmount[0].split("x");
            areaSizes.add(Integer.parseInt(area[0]) * Integer.parseInt(area[1]));
            String[] amount = areaAndAmount[1].strip().split(" ");
            int[] temp = Arrays.stream(amount).mapToInt(Integer::parseInt).toArray();
            amountOfShapes.add(temp);

        }
        for (String[] each: shapes){
            Shape shape = new Shape(each);
            sizes.add(shape.area);
        }

    }

    public static void checkIfShapesFitInArea(String input){
        Tetris tetris = new Tetris(input);
        int count = 0;
        for (int i = 0 ; i < tetris.amountOfShapes.size(); i++){
            int areaSize = tetris.areaSizes.get(i);
            int totalSizeNeededToFitShapes = 0;
            int[] amounts = tetris.amountOfShapes.get(i);
            for (int j = 0; j < amounts.length; j++){
                int amount = amounts[j];
                int shapeSize = tetris.sizes.get(j);
                int totalSizeNeeded = amount * shapeSize;
                totalSizeNeededToFitShapes+=totalSizeNeeded;

            }
            if (areaSize > totalSizeNeededToFitShapes *1.2) count+=1;
        }
        System.out.println("count: "+count);
    }

    class Shape{
        int area;
        Shape(String[] shape){
            for (int i = 0 ; i < 3; i++){
                for (int j = 0; j < 3; j++){
                     if (shape[i].charAt(j)  =='#'){
                         area+=1;
                     }
                }
            }
        }
    }
}
