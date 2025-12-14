import java.util.ArrayList;

public class DayNine {

//    public ArrayList<Group> analyseInputString(String inptStr) {
//        int spaceNeeded = 1;
//        int start = 0;
//        int end = 0;
//        int index = 1;
//        ArrayList<String> startEndSpace = new ArrayList<>();
//        ArrayList<Integer> freeSpaceSizes = new ArrayList<>();
//        ArrayList<Group> groups = new ArrayList<>();
//        String id = "";
//        char previous = inptStr.charAt(0);
//        while (index < inptStr.length()) {
//
//            char current = inptStr.charAt(index);
//            while (current == previous) {
//                current = inptStr.charAt(index);
//                spaceNeeded += 1;
//                index += 1;
//            }
//            groups.add(new Group(current, spaceNeeded));
//            previous = current;
//            int space = spaceNeeded;
//
//            spaceNeeded = 0;
//
//        }
//        for (Group each: groups){
//            System.out.println("Char: "+ each.getCharacter()+","+"Length: "+each.getGroupLength());
//        }
//        return groups;
//    }
//
//    public static String constructString(String input){
//        int runningIndex = 0;
//        StringBuilder newString= new StringBuilder();
//        for (int i = 0; i < input.length(); i++ ){
//            int spaceNeededForCurrentNum= DayNineUtil.getFreeSpaceNeeded(input,i);
//            if (i%2==0){
//               for (int j = 0; j <spaceNeededForCurrentNum; j++ ) newString.append(runningIndex+",");
//               runningIndex+=1;
//            }
//            else{
//                for (int j = 0; j <spaceNeededForCurrentNum; j++ ) newString.append(".,");
//            }
//
//        }
//        return newString.toString();
//    }
}

