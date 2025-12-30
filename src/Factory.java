import javax.lang.model.element.NestingKind;
import java.util.*;
import java.util.logging.XMLFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Factory {
    static String regexSquareBrackets = "\\[([^\\]]+)\\]";
    static String regexCurlyBraces = "\\{([^\\}]+)\\}";
    static String regexBrackets = "\\(([^\\(]+)\\)";

    public static void run(String input) {
        String[] inputSplit = input.split("\n");
        int totalButtonPresses = 0;
        for (String text: inputSplit) {
            Pattern pattern = Pattern.compile(regexBrackets);
            List<String> buttonSetsString = pattern.matcher(text).results().map(m -> m.group(1)).toList();
            List<List<String>> buttonSets = buttonSetsString.stream().map(e-> List.of(e.split(","))).toList();
            Pattern pattern1 = Pattern.compile(regexSquareBrackets);
            Matcher m = pattern1.matcher(text);
            String targetString = "";
            if (m.find()){
                targetString+=m.group(1);
            }
            List<String> target =symbolsToDigits(targetString);
            int count = countButtonPresses(target,buttonSets);
            System.out.println(count);
            totalButtonPresses+=count;
        }
        System.out.println("Total: "+totalButtonPresses);
    }

    public static void solvePartTwo(String input) {
        String[] inputSplit = input.split("\n");
        int totalButtonPresses = 0;
        Pattern pattern1 = Pattern.compile(regexCurlyBraces);
        Pattern pattern = Pattern.compile(regexBrackets);
        for (String text : inputSplit) {
            List<String> buttonSetsString = pattern.matcher(text).results().map(m -> m.group(1)).toList();
            List<int[]> buttonSets = buttonSetsString.stream()
                    .map(e -> Arrays.stream(e.split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray())
                    .toList();
            for (int[]each: buttonSets) System.out.println(Arrays.toString(each));
            Matcher matcher = pattern1.matcher(text);
            String joltageString = "";
            if (matcher.find()) {
                joltageString+= matcher.group(1);
            }
            int[] joltages = Arrays.stream(joltageString.split(",")).mapToInt(Integer::parseInt).toArray();
            if (buttonSets.size() == joltages.length){
                List<int[]> matrix = buildMatrix(buttonSets);

                for (int[]each: matrix) System.out.println(Arrays.toString(each));
                List<int[]> solvedMatrix = solveNiceEquations(0,matrix,joltages );
                for (int[]each: solvedMatrix) System.out.println(Arrays.toString(each));
                int total = getNiceEquationsAnswer(solvedMatrix,joltages);

            }
        }

    }
    private static int countButtonPresses(List<String> target, List<List<String>> buttonSets){
        PriorityQueue<ButtonSetAndPresses> minHeap = new PriorityQueue<>(Comparator.comparingInt(e->e.presses));
        Set<List<String>> seen = new HashSet<>();
        for (List<String> each: buttonSets) {
            minHeap.add(new ButtonSetAndPresses(1, each));
        }
        while (!minHeap.isEmpty()){
            ButtonSetAndPresses current = minHeap.poll();
            if (current.buttonSet.equals(target)) return current.presses;
            Set<String> XOR = new HashSet<>(current.buttonSet) ;
            for (List<String> buttonSet: buttonSets){
                Set<String> buttons = new HashSet<>(buttonSet);
                Set<String> copy = new HashSet<>(XOR);
                for (String each : buttons) {
                    if (!copy.add(each)) {
                        copy.remove(each);
                    }
                }
                if (!copy.isEmpty()) {
                    if (!seen.contains(new ArrayList<>(copy))) {
                        minHeap.add(new ButtonSetAndPresses(current.presses + 1, new ArrayList<>(copy)));
                        seen.add(new ArrayList<>( copy));
                    }
                }

            }
        }
        return 0;
    }

    private static List<String>symbolsToDigits(String symbols){
        List<String> lights = new ArrayList<>();
        for (int i=0; i < symbols.length(); i++){
            if (symbols.charAt(i) == '#'){
                lights.add(String.valueOf(i));
            }
        }
        return lights;
    }
    private static List<int[]> buildMatrix(List<int[]> buttonSets){
        List<int[]> matrix = new ArrayList<>();
        for (int k = 0; k < buttonSets.size(); k++){
            int[] temp = new int[buttonSets.size()];
            Arrays.fill(temp,0);
            matrix.add(temp);
        }
        for (int j = 0; j < buttonSets.size(); j++) {
            for (int i = 0; i < buttonSets.size(); i++) {
                int[] buttonSet = buttonSets.get(i);
                int target = i;
                if (Arrays.stream(buttonSet).anyMatch(value -> value== target)){
                    matrix.get(j)[i]= 1;
                }
            }
        }
        return matrix;
    }
    private static List<int[]> solveNiceEquations(int row, List<int[]> matrix, int[] joltages) {
        if (row == matrix.size() - 1) return matrix;
        if (matrix.get(row)[row] != 1) {
            for (int i = row + 1; i < matrix.size(); i++) {
                if (matrix.get(i)[row] == 1) {
                    int[] tempRow = Arrays.copyOf(matrix.get(row), matrix.get(row).length);
                    matrix.set(row, Arrays.copyOf(matrix.get(i), matrix.get(i).length));
                    matrix.set(i, tempRow);
                    int temp = joltages [row];
                    joltages[row] =  joltages[i];
                    joltages[i] = temp;
                }
            }
        }
        for (int i = row + 1; i < matrix.size(); i++) {
            if (matrix.get(i)[row] == 1) {
                for (int j= i; j < matrix.size(); j++){
                    matrix.get(i)[j] = matrix.get(row)[j] - matrix.get(i)[j];
                }
                joltages [i] = joltages[row] - joltages[i];
            }
        }
        return solveNiceEquations(row+1, matrix, joltages);

    }

    private static int getNiceEquationsAnswer(List<int[]> solvedMatrix, int[] joltages){
        int[] answers = new int[joltages.length];
        for (int i = solvedMatrix.size()-1; i >=0;i--){
            if (i <= solvedMatrix.size()-1) {
                for (int j = solvedMatrix.size()-1; j >=0; j--){
                    joltages[i] -= answers[j];
                }
                answers[i] = joltages[i];
            }
        }
        return Arrays.stream(answers).sum();
    }
    record ButtonSetAndPresses(Integer presses, List<String> buttonSet){};

}
