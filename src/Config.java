import java.util.ArrayList;
import java.util.List;

public class Machine {
    private List<List<Integer>> symbolTo(List<String> symbols){
        List<List<Integer>> list = new ArrayList<>();
         for (String symbolString: symbols){
             List<Integer>  numberList = new ArrayList<>();
             for (int i = 0; i< symbolString.length(); i++){
                 if (symbolString.charAt(i) == '#'){
                     numberList.add(i);
                 }
             }
             list.add(numberList);
         }
         return list;
    }

    
}
