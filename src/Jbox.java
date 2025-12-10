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
        ArrayList<HashSet<Jbox>> closest = new ArrayList<>();
        System.out.println("heap size: "+heap.size());
        while (!heap.isEmpty() && index<limit){
            DistanceJbox each = heap.poll();
//            System.out.println(each.thisBox.x+","+each.thisBox.y+","+each.thisBox.y+"---"+ each.otherJbox.x+","+each.otherJbox.y+","+each.otherJbox.z);
//            System.out.println(each.distance);
//            System.out.println();
            Jbox first = each.thisBox;
            Jbox second = each.otherJbox;
            closest.add(new HashSet<>(List.of(first,second)));
            index++;
        }
        ArrayList<HashSet<Jbox>> finalConnectedCircuits = Jbox.makeFinalMerge(closest.size(), closest);
        finalConnectedCircuits.sort(Comparator.comparingInt(Set::size));
        int total = 1;
        for (int i = 0; i <3; i++){
            total*=finalConnectedCircuits.removeLast().size();
        }
        System.out.println("Total: "+total);
    }
    private static ArrayList<HashSet<Jbox>> makeFinalMerge( int oldNumberOfCircuits, ArrayList<HashSet<Jbox>>connectedCircuits   ){

        ArrayList<HashSet<Jbox>> copy = new ArrayList<>();
        for (HashSet<Jbox> c : connectedCircuits) {
            copy.add(new HashSet<>(c));   // deep copy of each set
        }
        boolean inCircuit = false;
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
                    break;
                }
            }
//            if (inCircuit) break;

        }
        if (oldNumberOfCircuits == copy.size() ){
            return connectedCircuits;
        }

        return makeFinalMerge(copy.size(), copy);
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
