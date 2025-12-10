import java.lang.module.FindException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jbox {
    static ArrayList<Jbox> allJboxes = new ArrayList<>();
    int x;
    int y;
    int z;
    PriorityQueue<DistanceJbox> heap = new PriorityQueue<>(
            Comparator.comparingDouble(p -> p.distance)
    );
    Jbox closestOne;

    Jbox(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public static PriorityQueue<DistanceJbox> findClosestJboxes(ArrayList<Jbox> allJboxes){
        PriorityQueue<DistanceJbox> heap = new PriorityQueue<>(Comparator.comparingDouble(x->x.distance));
        for (int i = 0; i < allJboxes.size(); i++){
            for (int j = i+1; j < allJboxes.size(); j++){
                Jbox first = allJboxes.get(i);
                Jbox second = allJboxes.get(j);
                Double distance = first.getDistanceBetweenThisAndOther(second);
                DistanceJbox twoJboxes = new DistanceJbox(distance, first,second);
                heap.offer(twoJboxes);

                }
        }
        return heap;
    }

    public static void findTopClosestBoxes(int limit, ArrayList<Jbox> allJboxes){
        int index = 0;
        PriorityQueue<DistanceJbox> heap = Jbox.findClosestJboxes(allJboxes);
        ArrayList<DistanceJbox > closest = new ArrayList<>();
        ArrayList<HashSet<Jbox>> circuits = new ArrayList<>();
        System.out.println("heap size: "+heap.size());
        while (index <limit && !heap.isEmpty()){
            DistanceJbox each = heap.poll();
            System.out.println(each.thisBox.x+","+each.thisBox.y+","+each.thisBox.y+"---"+ each.otherJbox.x+","+each.otherJbox.y+","+each.otherJbox.z);
            System.out.println(each.distance);
            System.out.println();
            closest.add(each);
            index++;
        }
        ArrayList<HashSet<Jbox>> connectedCircuits = Jbox.group(closest,circuits, 0);

        ArrayList<HashSet<Jbox>> finalConnectedCircuits = Jbox.makeFinalMerge(0,10, 0,connectedCircuits);
        finalConnectedCircuits.sort(Comparator.comparingInt(Set::size));
        int total = 0;
        for (HashSet<Jbox> each: finalConnectedCircuits){
            System.out.println("size : "+ each.size());
            total+= each.size();
        }
        System.out.println("Total: "+total);
        System.out.println("Part two");
//        ArrayList<HashSet<Jbox> allCircuits = Jbox.group(new ArrayList<>(List.of(heap)), limit,)
//        ArrayList<HashSet<Jbox>> allConnected = Jbox.makeFinalMerge(0,10,0,)
    }
    private static ArrayList<HashSet<Jbox>> makeFinalMerge(int index,int limit,int start, ArrayList<HashSet<Jbox>>connectedCircuits   ){
        if (start == limit ){
            return connectedCircuits;
        }
        ArrayList<HashSet<Jbox>> copy = new ArrayList<>();
        for (HashSet<Jbox> c : connectedCircuits) {
            copy.add(new HashSet<>(c));   // deep copy of each set
        }
        int len = connectedCircuits.size();
        for (int i= 0; i < len ; i++) {
            for (int j=i+1; j < len ; j++) {
                HashSet<Jbox> circuit1 = connectedCircuits.get(i);
                HashSet<Jbox> circuit2 = connectedCircuits.get(j);
                Set<Jbox> intersection =
                        circuit1.stream()
                                .filter(circuit2::contains)
                               .collect(Collectors.toSet());
                if (!intersection.isEmpty()) {
                    HashSet<Jbox> union = (HashSet<Jbox>) Stream.concat(circuit1.stream(), circuit2.stream())
                            .collect(Collectors.toSet());
                    copy.add(union);
                    copy.remove(circuit1);
                    copy.remove(circuit2);
                    index--;
                    break;
                }
            }

        }
        connectedCircuits = makeFinalMerge(index+1,limit,  start+1,copy);
        return connectedCircuits;
    }

    private static ArrayList<HashSet<Jbox>> group (ArrayList<DistanceJbox> allPairs, ArrayList<HashSet <Jbox>> circuits, int index){
        if (index == allPairs.size()){
            return circuits;
        }
        DistanceJbox pair = allPairs.get(index);
        Jbox first = pair.thisBox;
        Jbox second = pair.otherJbox;
        ArrayList<HashSet<Jbox>> newCircuit = new ArrayList<>();
        for (HashSet<Jbox> c : circuits) {
            newCircuit.add(new HashSet<>(c));   // deep copy of each set
        }
        boolean isInCircuit = false;
        int len = circuits.size();
        for (int i=0 ; i < len && index!=i; i++) {
            HashSet<Jbox> eachCircuit = circuits.get(i);
            HashSet<Jbox>copy = newCircuit.get(i);
            for (Jbox each: eachCircuit){
                if (each.equals(first)|| each.equals(second)) {
                    isInCircuit = true;
                    copy.add(first);
                    copy.add(second);
                }
            }
        }
        if (!isInCircuit) {
            HashSet<Jbox> temp = new HashSet<>();
            temp.add(first);
            temp.add(second);
            newCircuit.add(temp);
        }
        return group(allPairs, newCircuit,index+1);
    }
    public static void setAllJboxes(ArrayList<String> input){
        for (String  each: input){
            String[] split = each.split(",");
            Jbox jbox = new Jbox(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            Jbox.allJboxes.add(jbox);
        }
    }

    public double getDistanceBetweenThisAndOther(Jbox other){
        return Math.sqrt(Math.pow(this.x - other.x,2)+Math.pow(this.y-other.y,2)+ Math.pow(this.z-other.z,2));
    }

    record DistanceJbox (double distance ,Jbox thisBox, Jbox otherJbox){}
}
