import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Beam {
    Set <Integer > beamPositions = new HashSet<>();
    ArrayList<Integer> startPosition = new ArrayList<>();
    ArrayList<String> map = new ArrayList<>();
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
}
