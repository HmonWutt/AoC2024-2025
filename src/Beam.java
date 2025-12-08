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

}
