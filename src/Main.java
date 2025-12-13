
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        AoCInputDownloader inputDownloader = new AoCInputDownloader();
        ReadFromFile scanner = new ReadFromFile();

        //////-------------------------------------DAY ONE------------------------------------------------////////
       /* inputDownloader.downloadInput("https://adventofcode.com/2024/day/1/input", "dayOneInput");
        ArrayList<String> dayOneInputRaw = scanner.loadAsArray("dayOneInput");
        LeftAndRightComparison dayOneInputCleaned = new LeftAndRightComparison(dayOneInputRaw);
        int partOneTotal = dayOneInputCleaned.getTotalDifference();
        //part-one
        System.out.println("Part one answer: " + partOneTotal);
        //part-two
        Dictionary rightAppearsInTheLeft = new Dictionary(dayOneInputCleaned);
        System.out.println("Part two answer: " + rightAppearsInTheLeft.multiplyKeysAndValues());*/

        //////-------------------------------------DAY TWO------------------------------------------------////////
      /*  inputDownloader.downloadInput("https://adventofcode.com/2024/day/2/input", "dayTwoInput");
        ArrayList<String> dayTwoInputRaw = scanner.loadAsArray("dayTwoInput");
        DayTwo totalReports = new DayTwo(dayTwoInputRaw);
        int totalSafeReports = totalReports.getSafeReports();
        System.out.println(totalSafeReports);
        int allowedOneUnsafe = totalReports.allowOneUnsafe();
        System.out.println(allowedOneUnsafe);
*/
        //////-------------------------------------DAY THREE------------------------------------------------////////
     /*   inputDownloader.downloadInput("https://adventofcode.com/2024/day/3/input", "dayThreeInput");
        String dayThreeInputRaw = scanner.loadAsString("dayThreeInput");
        String entireMatchPattern = "mul\\((-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)\\)";
        int total = DayThree.checkInputStringForMultiplications(entireMatchPattern, dayThreeInputRaw);
        System.out.println("Day three part one: "+total);
        String entireMatchPatternSecond = "do\\(\\)|don\\'t\\(\\)|mul\\((-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)\\)";
        int totalTwo = DayThree.findAll(entireMatchPatternSecond, dayThreeInputRaw);
        System.out.println("Day three part two: "+ totalTwo);*/

        //////-------------------------------------DAY FOUR------------------------------------------------////////
      /*  inputDownloader.downloadInput("https://adventofcode.com/2024/day/4/input", "dayFourInput");
        ArrayList<String> originalMatrix = scanner.loadAsArray("dayFourInput");
        //ArrayList<String> originalMatrix = scanner.loadAsArray("dayFourTest");
        int totalXMASES = 0;
        String partOnePattern = "XMAS";
        totalXMASES+=DayFour.findXMAS(partOnePattern,originalMatrix);
        ArrayList<String> diagnolaisedOriginalMatrix = DayFour.diagnoalise(originalMatrix);
        totalXMASES+=DayFour.findXMAS(partOnePattern,diagnolaisedOriginalMatrix);
        ArrayList<String> rowsAndColsSwappedMatrix = DayFour.swapRowsAndCols(originalMatrix);
        totalXMASES+=DayFour.findXMAS(partOnePattern,rowsAndColsSwappedMatrix);
        ArrayList<String> swappedRowsAndColsReversed = DayFour.reverse(rowsAndColsSwappedMatrix);
        ArrayList<String> diagnolaisedSwappedRowsAndCols = DayFour.diagnoalise(swappedRowsAndColsReversed);
        totalXMASES+=DayFour.findXMAS(partOnePattern,diagnolaisedSwappedRowsAndCols);
        System.out.println("Day four part one: "+ totalXMASES);
        int totalFourTwo=0;
        totalFourTwo+= DayFour.findCrossedMas(originalMatrix);
        System.out.println(totalFourTwo);*/

        /////////////////////////////////////////DAY FIVE//////////////////////////////////
        //inputDownloader.downloadInput("https://adventofcode.com/2024/day/5/input", "dayFiveInput");
        //ArrayList<String> dayFiveInput = scanner.loadAsArray("dayFiveInput");
        //Page all = new Page(dayFiveInput);
        //System.out.println("All pages in order: "+all.checkPagesInOrder());
        //for(String each:all.bottomHalf)System.out.println(each);
        //////////////////////////////////////////Day SIX///////////////////////////////////////
        // inputDownloader.downloadInput("https://adventofcode.com/2024/day/6/input", "daySixInput");
        //ArrayList<String> daySixInput = scanner.loadAsArray("daySixInput");
        //Guard guard = new Guard();
        //guard.navigateMatrix(daySixInput, true);
        //System.out.println("unique spots: "+numberOfUniqueSpots);
        //guard.decidePositionOfObsticle(daySixInput);
        /////////////////////////////////Day Eight////////////////////////////////
        //inputDownloader.downloadInput("https://adventofcode.com/2024/day/7/input", "daySevenInput");
        //ArrayList<String> daySevenInput = scanner.loadAsArray("daySevenInput");

        //BigInteger countTrueTests = TestFile.addTrueTestValues(daySevenInput,true);/////change to false to get part one answer
        //System.out.println("Tests that are true: "+countTrueTests);
        /////////////////////////////////Day Eight///////////////////////////////////
        /*inputDownloader.downloadInput("https://adventofcode.com/2024/day/8/input", "dayEightInput");
        ArrayList<String> dayEightInput = scanner.loadAsArray("dayEightInput");
        ArrayList<String> matrix = new ArrayList<>(dayEightInput);
        ArrayList<String> matrixPartOne;
        ArrayList<String> matrixResonant;
        int bound = matrix.size();
        ArrayList<Node> nodes = Uti.getNodes(matrix);
        matrixPartOne = Uti.makeNewMatrices(nodes,dayEightInput,bound,true);
        matrixResonant = Uti.makeNewMatrices(nodes,dayEightInput,bound,false);
        int totalOne = Uti.countAntinodesPartOne(matrixPartOne);
        Uti.printMatrix(matrixPartOne);
        System.out.println("Part one answer: "+totalOne+"\n");
        int total = Uti.countAntinodesPartTwo(matrixResonant);
        Uti.printMatrix(matrixResonant);
        System.out.println("Part two answer: "+total);*/
        ////////////////////////////////////////////////////Day Nine/////////////////////////////////////////////////
        //inputDownloader.downloadInput("https://adventofcode.com/2024/day/9/input", "dayNineInput");
        // String dayNineInput = scanner.loadAsString("dayNineInput");
        //String inptStr ="2333133121414131402";
        /*String newString = DayNine.constructString(inptStr);
        //String newString = DayNine.constructString(dayNineInput);
        ArrayList<String> input = new ArrayList<String> (List.of(newString.split(",")));
        int countUp = 0;
        int countDown = input.size();
        while (countDown > 0  && countUp < input.size()){
            countDown = input.size()-1;
             String onLeft = input.get(countUp);
             String onRight = input.get(countDown);
             if (onLeft.equals(".") && onRight.equals(".")) input.remove(countDown);
             else {
                 if (!onLeft.equals(".") && !onRight.equals(".")) countUp+=1;
                 else if (onLeft.equals(".")){
                     input.set(countUp, onRight);
                     input.remove(countDown);
                     countUp+=1;
                 }
                 else input.remove(countDown);
             }
             }

       int index = 0;
        BigInteger total  = BigInteger.valueOf(0);
        while(index < input.size() ){
            BigInteger temp = BigInteger.valueOf((long) index * Integer.parseInt(input.get(index)));
            total = total.add(temp);
            index+=1;
        }
        System.out.println(total);*/
        //ArrayList<Integer> output = DayNine.fillInSpaces(inptStr);
        //System.out.println(output);
        //////////////////////////////////////////////Day 17//////////////////////////////////////////////////
/*        String programme = "2,4,1,1,7,5,1,5,4,2,5,5,0,3,3,0";

        int len = programme.length()/2;
        Operator operator = new Operator(0,0,0,programme);
       // System.out.println("Part one answer: "+operator.execute());
        long num = (long)Math.pow(10,(len))/8;
        System.out.println(num);
        long num2 = num-117340;
        operator = new Operator(num,0,0,programme);
        String output= operator.execute();
        output = output.substring(0,output.length()-1);
       while(!programme.equals(output)&& num >0) {
           num= num-1;
           operator = new Operator(num, 0, 0, programme);
           output = operator.execute();
           output = output.substring(0,output.length()-1);
           if (num>num2) System.out.println(num+"\n"+output+",\n"+programme);
       }

       System.out.println(num);*/

        // inputDownloader.downloadInput("https://adventofcode.com/2024/day/2/input", "dayTwoInput");
     /*   ArrayList<String> dayTenInputRaw = scanner.loadAsArray("dayTenTest");
        // Day10Recursion.countTrails(dayTenInputRaw);
        String input = "337 42493 1891760 351136 2 6932 73 0";
        //String input = "125 17";
        Integer limitPartOne = 25;
        Integer limitPartTwo = 75;
        DayEleven.printTotalSpawns(limitPartTwo, input);
*/
/*        inputDownloader.downloadInput("https://adventofcode.com/2024/day/12/input", "dayTwelveInput");
        ArrayList<String> dayTwelveInputRaw = scanner.loadAsArray("dayTwelveInput");
        HashMap<Integer, ArrayList<Integer>> dictionary = GardenGroup.findGroups(dayTwelveInputRaw,0,0);
        Integer total = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : dictionary.entrySet()) {// Get the key
            ArrayList<Integer> values = entry.getValue(); // Get the value
            total+=(values.get(0)*values.get(1));
        }
        System.out.println(total);*/
  /*
        inputDownloader.downloadInput("https://adventofcode.com/2024/day/13/input", "dayThirteenInput");
        String dayThirteenInputRaw = scanner.keepLineBreaks("dayThirteenInput");
        ArrayList<String> input = new ArrayList<>(Arrays.asList(dayThirteenInputRaw.split("\n\s*\n")));
        Object totalTokens = DayThirteen.countTokens(input,false);
        System.out.println("Day 13 part one: "+totalTokens);
        Object hugeTotalTokens = DayThirteen.countTokens(input,true) ;
        System.out.println("Day 13 part two: "+hugeTotalTokens);*/
//        inputDownloader.downloadInput("https://adventofcode.com/2024/day/14/input", "dayFourteenInput");
//        ArrayList<String> dayFourteenInputRaw = scanner.loadAsArray("dayFourteenInput");
       /* ArrayList<ArrayList<Integer>> robotDetails = new ArrayList<>();
        ArrayList<Robot> allRobots = new ArrayList<>();
        *//*Integer height = 6;///for test
        Integer width = 10;*//*
        Integer height = 102;
        Integer width = 100;
        Board newBoard = new Board(height,width);
        ArrayList<Robot> robotsGroupOne = DayFourteen.putRobotsAtStartingPosition(dayFourteenInputRaw,height,width);
        System.out.println("Part one answer: "+newBoard.countRobots(100,robotsGroupOne));
        ArrayList<Robot> robotsGroupTwo = DayFourteen.putRobotsAtStartingPosition(dayFourteenInputRaw,height,width);
        newBoard.findXmasTree(20000,robotsGroupTwo);*/

//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/1/input", "011225");
//        ArrayList<String> dayOne= scanner.loadAsArray("011225");
//        Safe.run(dayOne);
//
//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/2/input", "021225");
//
//        String dayTwo = scanner.loadAsString("021225");
//        ArrayList<String> input = new ArrayList<>(Arrays.asList(dayTwo.split(",")));
//        ArrayList<Long> answer1 = new ArrayList<>();
//        ArrayList<Long> answer2 = new ArrayList<>();
//
//        for (String each: input){
//
//            List<String> list = new ArrayList<>(Arrays.asList(each.split("-")));
//            long start =Long.parseLong(list.getFirst());
//            long end = Long.parseLong(list.get(1));
//            IdRange range = new IdRange(start,end);
//            range.findInvalid();
//            answer1.addAll(range.invalidIds1);
//            answer2.addAll(range.invalidIds);
//        }
////        System.out.println(answer);
//        long total = 0;
//        for (long each: answer1){
//            total+=each;
//
//        }
//        System.out.println("Day 2 part one: "+total);
//        long total2 = 0;
//        for (long each: answer2){
//            total2+=each;
//
//        }
//        System.out.println("Day 2 part two: "+total2);
//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/3/input", "031225");
//
//        ArrayList<String>dayThree = scanner.loadAsArray("031225");
//
//        long sum = 0;
//        for (String each: dayThree){
//            sum+= Battery.findBiggest12Digit(each);
//
//        }
////        System.out.println(sum);
//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/4/input", "041225");
//
//        ArrayList<String>dayFour = scanner.loadAsArray("041225");
//        String[] grid = dayFour.toArray(new String[0]);
//        PaperRoll.makeGrid(grid);
//        int gridLen = grid.length;
//        int count = -1;
//        int old_count = 0;
//        while (count!=old_count) {
//            count = run(gridLen, old_count);
//        }
//        System.out.println(count);
//        int finalCount = 0;
//        for (int x = 0; x < gridLen; x++) {
//            for (int y = 0; y < gridLen; y++)
//                if (PaperRoll.grid[x].charAt(y)=='X') finalCount+=1;
//        }
//        System.out.println(finalCount);

//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/5/input", "051225");
//        String dayFive = scanner.keepLineBreaks("051225");
//        String[] splits = dayFive.split("\\R\\s*\\R");
//
//        Ingredients ranges = new Ingredients(splits[0]);
//        Ingredients.makeHeap();
//        Ingredients.loopOver(Ingredients.ranges);
//        int count = ranges.run(splits[1]);
//        System.out.println(count);

//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/6/input", "061225");
//        ArrayList<String> daySix = scanner.loadAsArray("061225");
//        Matrix matrix = Matrix.makeMatrix(daySix, "\\s+");
        String daySixAsString = scanner.keepLineBreaks("061225");
//        String addZerosInfront = daySixAsString.replaceAll(" +(?=\\d)", "0");
//        String addZerosBehind = addZerosInfront.replaceAll("\\d(?=\\s+)", "0");
//        System.out.println(addZerosBehind);
//        String[] split = daySixAsString.split("\\n");
//        MathOperation.calculateTwo(split);
//        System.out.println(split[0].length());
//        System.out.println(newMatrix.data);

//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/7/input", "071225");
//        ArrayList<String> daySeven = scanner.loadAsArray("071225");
//        Beam beam = new Beam(daySeven);
//        int splittersCount = beam.getSplitterCount();
//        System.out.println(splittersCount);
//        beam.run();

//        System.out.println(matrix.data);
//        Matrix transposedMatrix = matrix.transpose();
//        long result = MathOperation.calculate(transposedMatrix);
//        System.out.println(result);

//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/8/input", "081225");
        ArrayList<String> dayEight = scanner.loadAsArray("081225");
//        Jbox.run(dayEight);


//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/11/input", "111225");
        ArrayList<String> dayEleven = scanner.loadAsArray("test11");
        Device device = new Device(dayEleven,"svr");

        ArrayList<String> dayTen = scanner.loadAsArray("test10");
        Config config = new Config();
//        config.splitInputIntoMachineAndButtonsAndJoltage(dayTen);

//        inputDownloader.downloadInput("https://adventofcode.com/2025/day/9/input", "091225");
        ArrayList<String> dayNine = scanner.loadAsArray("test9");
        TheatreFloor floor = new TheatreFloor();
        TheatreFloor.findSameXAndSameY(dayNine);
        PriorityQueue<Long> sizes = TheatreFloor.findBiggestSquare(dayNine, new PriorityQueue<>());
        System.out.println("biggest: "+Collections.max(sizes));

        ArrayList <String> lastThree = new ArrayList<>();
        lastThree.add(dayNine.get(dayNine.size()-2));
        lastThree.add(dayNine.get(dayNine.size()-1));
        lastThree.add(dayNine.getFirst());
        PriorityQueue<Long> sizes1 = TheatreFloor.findBiggestSquare(lastThree,sizes);
        System.out.println(sizes1.peek());

    }

//    public static int run(int gridLen, int old_count){
//        int count = old_count;
//        for (int x = 0; x < gridLen; x++) {
//            for (int y = 0; y < gridLen; y++) {
//                if (PaperRoll.grid[x].charAt(y) == '@') {
//                    PaperRoll paperRoll = new PaperRoll(x, y);
//                    int neighbours = paperRoll.getNeighbours();
//                    if (neighbours <= 4) {
//                        count += 1;
//                        String[] gridCopy = Arrays.copyOf(PaperRoll.grid, gridLen);
//                        StringBuilder stringBuilder = new StringBuilder(gridCopy[x]);
//                        stringBuilder.setCharAt(y, 'X');
//                        gridCopy[x] = stringBuilder.toString();
//                        PaperRoll.makeGrid(gridCopy);
////                        System.out.println("Paper rolls with less than 4 neighbours: " + count);
////                        for (String each : PaperRoll.grid) {
////                            System.out.println(each);
////                        }
//
//                    }
//                }
//            }
//
//        }
//        return count;
//    }


}