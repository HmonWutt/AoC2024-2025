import java.util.ArrayList;
import java.util.List;

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
//            System.out.println("start: "+result);
            for (int j = 1; j < row.size()-1;j++) {
                long currentNum = Long.parseLong(row.get(j));
//                System.out.println("Secon num "+currentNum);
                result = doOperation(operator, currentNum, result);
//                System.out.println("result" + result);
            }
            total+=result;
        }
        return total;
    }
}
