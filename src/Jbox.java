import java.util.*;
import java.util.stream.Collectors;

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
        HashSet<DistanceJbox > closest = new HashSet<>();
        while (index < limit && !heap.isEmpty()){
            DistanceJbox each = heap.poll();
            System.out.println(each.thisBox.x+","+each.thisBox.y+","+each.thisBox.y+"---"+ each.otherJbox.x+","+each.otherJbox.y+","+each.otherJbox.z);
            System.out.println(each.distance);
            System.out.println();
            closest.add(each);
        }
        group(closest);
    }


    public static void group (HashSet<DistanceJbox> all){
        ArrayList<HashSet<Jbox>> groups = new ArrayList<>();
        for (DistanceJbox each: all){
            for (DistanceJbox each1: all){
                if (each!=each1){
                    HashSet<Jbox> circuit = new HashSet<>();
                    HashSet<Jbox> temp = new HashSet<>(Arrays.asList(each.thisBox, each.otherJbox));
                    HashSet<Jbox> temp1 = new HashSet<>(Arrays.asList(each1.thisBox, each1.otherJbox));
                    HashSet<Jbox> intersection = temp.stream().filter(temp1::contains).collect(Collectors.toCollection(HashSet::new));
                    if (!intersection.isEmpty()){
                        circuit.addAll(intersection);
                        System.out.println(circuit.size());
                    }
                }
            }
        }
        System.out.println(groups);
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
