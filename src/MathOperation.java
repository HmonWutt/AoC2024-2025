import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathOperation {
    private static BigInteger sum(BigInteger a, BigInteger b){
        return a.add(b);
    }

    private static BigInteger multiply(BigInteger a, BigInteger b){
        return a.multiply(b);
    }

    private static BigInteger doOperation (String operator, BigInteger a, BigInteger b){
        if (operator.equals("*")){
            return multiply(a, b);
        }
        else return sum(a,b);
    }

    public static BigInteger calculate(Matrix matrix){
        BigInteger total = new BigInteger("0");
        for (int i=0; i < matrix.getRowLength(); i++){
            List<String> row = matrix.getRow(i);
            String operator = row.getLast();
            BigInteger result = new BigInteger(row.getFirst());
            for (int j = 1; j < row.size()-1;j++) {
                BigInteger currentNum = new BigInteger(row.get(j));
                result = doOperation(operator, currentNum, result);
            }
            total.add(result);
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
        System.out.println(fixedWidths.size()+","+array.length);
        while (!fixedWidths.isEmpty()) {
            int fixedWidth =  fixedWidths.removeFirst();
            for (int i = 0; i < array.length-1; i++){
                if (fixedWidths.size() ==0){
                    eachNumber = array[i].substring(index);
                }
                else{
                    eachNumber = array[i].substring(index, index + fixedWidth-1);
                }
                rows.add(eachNumber);
            }
            index+=fixedWidth;
            numbersByColumn.add(rows);
            rows = new ArrayList<>();

        }
        return numbersByColumn;

    }

    private static String extractOperators(String string){
        StringBuilder operators = new StringBuilder();
        for (int i = 0; i < string.length(); i++){
            if (string.charAt(i)=='*' || string.charAt(i)=='+'){
                operators.append(string.charAt(i));
            }
        }
        return operators.toString();

    }

    private static String replaceSpaceWithZeros(String string){
        return string.replaceAll(" ","0");
    }

    public  static void calculateTwo(String[] array){
        ArrayList<ArrayList<String>> numbersByColumn = MathOperation.splitStringsUsingFixedWidth(array);
        ArrayList<ArrayList<String>> eachNumberInColumn = new ArrayList<>();
        for (int i = 0; i < numbersByColumn.size(); i++){

            String[] row = numbersByColumn.get(i).toArray(new String[0]);
            ArrayList<String> rows = new ArrayList<>();
            for (int j = 0; j < row.length; j++) {
                    StringBuilder numberInEachColumn = new StringBuilder();
                    for (String each : row) {
                        try {
                            if (each.charAt(j) != ' ') {
                                numberInEachColumn.append(each.charAt(j));
                            }
                        } catch (Exception e) {
                            ;
                        }
                    }
                    if (!numberInEachColumn.isEmpty()) {
                        rows.add(numberInEachColumn.toString());
                    }
//                System.out.println(rows);
                }
            eachNumberInColumn.add(rows);
        }
//        System.out.println(eachNumberInColumn);
        MathOperation.getCalculation(eachNumberInColumn,array[array.length-1]);
    }

    public static void getCalculation (ArrayList<ArrayList<String>> numbersByCol, String lastRow){
        String operators = MathOperation.extractOperators(lastRow);
        BigInteger total= new BigInteger("0");
        for (int i = 0; i < operators.length();i++){
            ArrayList<String> numbers = numbersByCol.get(i);
            BigInteger first = new BigInteger(numbers.getFirst());
            String operator = String.valueOf(operators.charAt(i));
            for (int j = 1; j < numbers.size(); j++){
                    first = MathOperation.doOperation(operator, first, new BigInteger(numbers.get(j)));
            }
            total = total.add(first);

        }
        System.out.println("Total: "+ total);
    }


}
