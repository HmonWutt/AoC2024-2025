import java.util.*;

public class Beam {
    Set <Integer > beamPositions = new HashSet<>();
    ArrayList<Integer> startPosition = new ArrayList<>();
    ArrayList<String> map = new ArrayList<>();
    ArrayList<String> paths = new ArrayList<>();
    int splitterCount;
    public Beam(ArrayList<String>map){
        this.map = map;
        this.findStartPosition();
        this.iterateOverRows();
    }
    private void findStartPosition(){
        for (int i =0; i < this.map.getFirst().length();i++){
            if (map.getFirst().charAt(i) == 'S'){
                this.startPosition= new ArrayList<>(List.of(0,i));
                this.beamPositions.add(this.startPosition.getLast());
                this.paths.add(String.valueOf(this.startPosition.getFirst())+String.valueOf(this.startPosition.getLast()));
                return;
            }
        }
    }

    private void findSplittersAndAddNewColumns(Integer row){
        for (int i = 1; i < this.map.get(row).length();i++){
            if (this.map.get(row).charAt(i)=='^' && beamPositions.contains(i))   {
                this.countSplitters();
                this.beamPositions.remove((Object) i);
                if (i-1>=0){
                    this.beamPositions.add(i-1 );
                }
                if (i+1 <this.map.get(row).length()) {
                    this.beamPositions.add(i+1);
                }
            }
        }
    }

    private void iterateOverRows(){
        for (int i =2 ; i < this.map.size();i+=2){
            this.findSplittersAndAddNewColumns(i);
        }
    }

    private void countSplitters(){
        this.splitterCount+=1;
    }

    public int getSplitterCount(){
        return this.splitterCount;
    }

    public int getUniquePaths(){
        return this.paths.size();
    }

    public String checkIfPathHasBeenTakenIfNotTakeThatPath(String oldString,int row, int col){

        StringBuilder newPath= new StringBuilder(oldString+String.valueOf(row)+ col);
        if(!this.paths.contains(newPath) ){
            return newPath.toString();

        }
        return "";
    }

    public void followAllPaths( ){
        int count=0;
        int startRow = 2;
        Set <Coordinate> explored = new HashSet<>();
        int startColumn = startPosition.getLast();
        Coordinate dict = new Coordinate(startRow,startColumn);
        Queue<Coordinate>queue = new LinkedList<>();
        queue.add(dict);
        int[]leftRight = {1,-1};
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.remove();
            if (!explored.contains(coordinate)) {
                int row = coordinate.row;
                System.out.println("row: " + map.get(row));
                row += 2;
                int col = coordinate.col;
                if (row < map.size()) {
                    for (int each : leftRight) {
                        if (col + each < map.size() && col + each >= 0) {
                            System.out.println("character at posiotn: "+map.get(row).charAt(col + each));
                            if (map.get(row).charAt(col + each) == '^') {
                                Coordinate newCoordinate = new Coordinate(row, col + each);
//                           System.out.println(newCoordinate);
                                queue.add(newCoordinate);
                            } else {
                                Coordinate newCoordinate = findNextSplitter(row, col + each);
                                System.out.println(newCoordinate.row + "," + newCoordinate.col);
                                if (newCoordinate.row != 0 && newCoordinate.col != 0) queue.add(newCoordinate);
                            }

                        }

                    }
                    explored.add(coordinate);

                }
            }

            count+=1;
        }
        System.out.println("Total paths: "+count);
    }

    private Coordinate findNextSplitter(int row,int col){
        int newRow= row+2;
        while (newRow < map.size()){
            if (map.get(newRow).charAt(col)=='^'){
                return new Coordinate(newRow,col);
            }
            newRow+=2;
        }
        return new Coordinate(0,0);
    }


    private long explore(int row, int col, HashMap<Coordinate,Long>library) {
        long count = 0;
        if (row == map.size() ) {

            return count + 1;
        }
        if (library.containsKey(new Coordinate(row,col))){
            return library.get(new Coordinate(row,col));
        }

        if (map.get(row).charAt( col) == '^') {
            if (row+2 <=map.size() && col+1 < map.size() && col-1 >=0){
                count += explore(row + 2, col + 1,library);
                count += explore(row+2, col - 1,library);
            }
        } else {
            if (row+2<= map.size() ) {
                count += explore(row + 2, col,library);
            }
        }
        library.put(new Coordinate(row,col), count);
        return count;

    }

    public void run(){
        int col = this.startPosition.getLast();
        int row = 2;
        HashMap<Coordinate,Long> library = new HashMap<>();
        long count = explore(row,col,library);
        System.out.println(count);
    }

    record Coordinate(int row, int col) {};
    record Splitter<K, V>(K key, V value) {}






}
