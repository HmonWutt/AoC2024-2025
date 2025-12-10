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
        ArrayList<HashSet<Jbox>> connectedCircuits = Jbox.group(closest,limit,circuits, 0);

        ArrayList<HashSet<Jbox>> finalConnectedCircuits = Jbox.makeFinalMerge(0,connectedCircuits);
        finalConnectedCircuits.sort(Comparator.comparingInt(Set::size));
        int total = 0;
        for (HashSet<Jbox> each: finalConnectedCircuits){
            System.out.println("size : "+ each.size());
            total+= each.size();
        }
        System.out.println("Total: "+total);
    }
    private static ArrayList<HashSet<Jbox>> makeFinalMerge(int index,ArrayList<HashSet<Jbox>>connectedCircuits   ){
        ArrayList<HashSet<Jbox>> copy = new ArrayList<>();
        for (HashSet<Jbox> c : connectedCircuits) {
            copy.add(new HashSet<>(c));   // deep copy of each set
        }
        if (index == connectedCircuits.size()-1){
            return connectedCircuits;
        }

        for (int i= 0; i < connectedCircuits.size() && i!=index; i++) {
            HashSet<Jbox> circuit1 = connectedCircuits.get(i);
            HashSet<Jbox> circuit2 = connectedCircuits.get(index);
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
        connectedCircuits =  makeFinalMerge(index+1,copy);
        return connectedCircuits;

    }

    private static ArrayList<HashSet<Jbox>> group (ArrayList<DistanceJbox> allPairs,int limit, ArrayList<HashSet <Jbox>> circuits, int index){
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
        for (int i=0 ; i < circuits.size() && index!=i; i++) {
            HashSet<Jbox> eachCircuit = circuits.get(i);
            for (Jbox each: eachCircuit){
                if (each.equals(first)|| each.equals(second)) {
                    isInCircuit = true;
                    HashSet<Jbox> copy = newCircuit.get(i);
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
        return group(allPairs, limit, newCircuit,index+1);
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

//    private PriorityQueue<DistanceJbox> findClosesJboxes(ArrayList<Jbox> jboxes){
//        PriorityQueue<DistanceJbox> heap = new PriorityQueue<>(
//                Comparator.comparingDouble(p -> p.distance)
//        );
//        for (int i =0; i< jboxes.size();i++){
//            Jbox other = jboxes.get(i);
//            if (this != other ){
//                double distance = getDistanceBetweenThisAndOther(other);
//                heap.offer(new DistanceJbox(distance,other));
//            }
//        }
//        return heap;
//    }

//    private Jbox getClosestOne(ArrayList<Jbox>jboxes){
//        this.heap = this.findClosesJboxes(jboxes);
//        return this.heap.peek().otherJbox();
//    }
//
//    public static void showEachBoxClosest(ArrayList<Jbox> jboxes){
//        for (Jbox each: Jbox.allJboxes){
//            System.out.println("This Jbox: "+ each.x+","+ each.y+","+ each.z);
//            Jbox cloestOne = each.getClosestOne(jboxes);
//            System.out.println("Other Jbox: "+ cloestOne.x+","+ cloestOne.y+","+ cloestOne.z);
//            System.out.println();
//        }
//    }

    record DistanceJbox (double distance ,Jbox thisBox, Jbox otherJbox){}
}
