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

    private PriorityQueue<DistanceJbox> findClosesJboxes(ArrayList<Jbox> jboxes){
        PriorityQueue<DistanceJbox> heap = new PriorityQueue<>(
                Comparator.comparingDouble(p -> p.distance)
        );
        for (int i =0; i< jboxes.size();i++){
            Jbox other = jboxes.get(i);
            if (this != other ){
                double distance = getDistanceBetweenThisAndOther(other);
                heap.offer(new DistanceJbox(distance,other));
            }
        }
        return heap;
    }

    private Jbox getClosestOne(ArrayList<Jbox>jboxes){
        this.heap = this.findClosesJboxes(jboxes);
        return this.heap.peek().otherJbox();
    }



    public static void showEachBoxClosest(ArrayList<Jbox> jboxes){
        for (Jbox each: Jbox.allJboxes){
            System.out.println("This Jbox: "+ each.x+","+ each.y+","+ each.z);
            Jbox cloestOne = each.getClosestOne(jboxes);
            System.out.println("Other Jbox: "+ cloestOne.x+","+ cloestOne.y+","+ cloestOne.z);
            System.out.println();
        }
    }

    public static ArrayList<HashSet<Jbox>> groupJboxes(ArrayList<Jbox> allJboxes,  ArrayList<HashSet<Jbox>> circuits){
        ArrayList<HashSet<Jbox>> newCircuits = new ArrayList<>();
        while (true) {
            for (int i = 0; i < circuits.size(); i++) {
                for (int j = i+1; j < circuits.size(); j++) {
                    if (circuits.get(i)!=circuits.get(j)) {
                        HashSet<Jbox> combined = compareSets(circuits.get(i), circuits.get(j), allJboxes);
                        if (!combined.isEmpty()) {
                            newCircuits.add(combined);
                        }
                        else{
                            newCircuits.add(circuits.get(i));
                            newCircuits.add(circuits.get(j));
                        }
                    }
                }
            }
            if (newCircuits == circuits){
                return circuits;
            }
            circuits = newCircuits;
        }
    }
    public static HashSet<Jbox> compareSets(HashSet<Jbox> one, HashSet<Jbox> two, ArrayList<Jbox> allJboxes){
        HashSet<Jbox> intersection = one.stream().filter(two::contains).collect(Collectors.toCollection(HashSet::new));
        if (!intersection.isEmpty()){
            HashSet<Jbox> bothSets = new HashSet<>();
            bothSets.addAll(one);
            bothSets.addAll(two);
            return bothSets;
        }
        for (Jbox each:one){
            if (two.contains(each.getClosestOne(allJboxes) )){
                HashSet<Jbox> bothSets = new HashSet<>();
                bothSets.addAll(one);
                bothSets.addAll(two);
                return bothSets;
            }
        }
        for (Jbox each:two){
            if (one.contains(each.getClosestOne(allJboxes) )){
                HashSet<Jbox> bothSets = new HashSet<>();
                bothSets.addAll(one);
                bothSets.addAll(two);
                return bothSets;
            }
        }
        return new HashSet<>();

    }

    record DistanceJbox (double distance , Jbox otherJbox){}
}
