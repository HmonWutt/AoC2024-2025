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
//    Pattern pattern1 = Pattern.compile(regexCurlyBraces);
//    Matcher matcher = pattern1.matcher(text);
//            if (matcher.find()) {
//        System.out.println(matcher.group());
//    }
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
    record ButtonSetAndPresses(Integer presses, List<String> buttonSet){};

}
