import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathOperation {
    private static long sum(long a, long b){
        return a+b;
    }

    private static long multiply(long a, long b){
        return a*b;
    }

    private static long doOperation(String operator, long a, long b){
        if (operator.equals("*")){
            return multiply(a, b);
        }
        else return sum(a,b);
    }

    public static long calculate(Matrix matrix){
        long total = 0l;
        for (int i=0; i < matrix.getRowLength(); i++){
            List<String> row = matrix.getRow(i);
            String operator = row.getLast();
            long result = Long.parseLong(row.getFirst());
            for (int j = 1; j < row.size()-1;j++) {
                long currentNum = Long.parseLong(row.get(j));
                result = doOperation(operator, currentNum, result);
            }
            total+=result;
        }
        return total;
    }


    static public ArrayList<Integer>  getFixedWidth(String string){
        ArrayList<Integer> fixedWidths = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\*\\s+|\\+\\s+");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()){
            fixedWidths.add(matcher.group().length());
        }
        return fixedWidths;
    }

    public static ArrayList<ArrayList<String>> splitStringsUsingFixedWidth(String[] array){
        ArrayList<Integer> fixedWidths = MathOperation.getFixedWidth(array[array.length-1]);
        ArrayList<String> rows = new ArrayList<>();
        String eachNumber = "";
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        int index = 0;
        while (!fixedWidths.isEmpty()) {
            int fixedWidth =  fixedWidths.removeFirst();
            for (int i = 0; i < array.length-1; i++){
                eachNumber = array[i].substring(index, index + fixedWidth-1);
                rows.add(eachNumber);
            }
            index+=fixedWidth;
            matrix.add(rows);
            rows = new ArrayList<>();

        }
        return matrix;

    }



}
