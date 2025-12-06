import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    List<List<String>> data;
    private int rowLength;
    private int colLength;
    public static Matrix makeMatrix(ArrayList<String> stringArray){
        List<List<String>> data = new ArrayList<>();
        for (String each: stringArray){
            String[] row = each.split("\\s+");
//            System.out.println(Arrays.toString(row));
            data.add(List.of(row));
        }
        Matrix matrix = new Matrix();
        matrix.data = data;
        matrix.colLength = matrix.data.size();
        matrix.rowLength = matrix.data.getFirst().size();
        return matrix;
    }

    public String getItem(int row, int col){
        return data.get(row).get(col);
    }
    public int getRowLength(){
        return rowLength;
    }
    public List<String> getRow(int row){
        return data.get(row);
    }

    public Matrix transpose(){
        List<String> oneRow= new ArrayList<>();
        List<List<String>> newData = new ArrayList<>();
        int col = 0;
        while (col < rowLength){
            for (int row = 0; row < colLength; row++){
                String item = getItem(row, col);
                oneRow.add(item);
            }
            newData.add(oneRow);
//            System.out.println(oneRow);
            oneRow = new ArrayList<>();
            col++;
        }
        Matrix matrix = new Matrix();
        matrix.data = newData;
        matrix.rowLength = matrix.data.size();
        matrix.colLength = matrix.data.getFirst().size();
        return matrix;
    }
}
