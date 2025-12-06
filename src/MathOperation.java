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
        ArrayList<ArrayList<String>> numbersByColumn = new ArrayList<>();
        int index = 0;
        while (!fixedWidths.isEmpty()) {
            int fixedWidth =  fixedWidths.removeFirst();
            System.out.println("Fiexed width: "+fixedWidth);
            for (int i = 0; i < array.length-1; i++){
                if (i <array.length-2){
                eachNumber = array[i].substring(index, index + fixedWidth-1);
                }
                else{
                    eachNumber = array[i].substring(index, index+fixedWidth);
                }
                System.out.println("Each numer: "+eachNumber);
                rows.add(eachNumber);
            }
            index+=fixedWidth;
            numbersByColumn.add(rows);
            rows = new ArrayList<>();

        }
        return numbersByColumn;

    }

    private static String extractOperators(String string){
        int index = 0;
        StringBuilder operators = new StringBuilder();
        while (index < string.length()){
            if (string.charAt(index)=='*' || string.charAt(index)=='+'){
                operators.append(string.charAt(index));
                index+=1;

            }
        }
        return operators.toString();

    }

    private static String replaceSpaceWithZeros(String string){
        return string.replaceAll(" ","0");
    }

    public  static void calculateTwo(String[] array){
        ArrayList<ArrayList<String>> numbersByColumn = MathOperation.splitStringsUsingFixedWidth(array);
//        String operators = MathOperation.extractOperators(array[array.length-1]);
        ArrayList<ArrayList<String>> eachNumberInColumn = new ArrayList<>();
        for (int i = 0; i < numbersByColumn.size(); i++){
            String[] row = numbersByColumn.get(i).toArray(new String[0]);
            for (String each: row)System.out.println("Row: "+ each);
            ArrayList<String> rows = new ArrayList<>();
            for (int j = 0; j < row.length; j++){

                StringBuilder numberInEachColumn = new StringBuilder();
                for (String each: row){
                    if (each.charAt(j)!=' '){
                        numberInEachColumn.append(each.charAt(j)) ;
                    }

                }
                rows.add(numberInEachColumn.toString());
//                System.out.println(rows);
            }
            eachNumberInColumn.add(rows);
        }
        System.out.println(eachNumberInColumn);
    }


}
