import java.lang.classfile.constantpool.StringEntry;
import java.util.*;

public class Device {
    Deque<String> queue = new ArrayDeque<>();
    static HashMap<String, String[]> deviceAndOutput = new HashMap<>();
    Device(ArrayList<String> input) {
        for (String each : input) {
            String[] splited = each.split(":");
            String[] values = splited[1].strip().split(" ");
            deviceAndOutput.put(splited[0], values);
        }
    }

    private static int traverse(  Queue<String> queue){
        int outs = 0;
        while (!queue.isEmpty()){
            String startingDevice = queue.poll();
            if (startingDevice.equals("out")) {
                outs++;
            }
            if (deviceAndOutput.get(startingDevice)!=null){
                queue.addAll(List.of(deviceAndOutput.get(startingDevice)));
            }

        }
        return outs;

    }

    private static long traverse2(String node,  boolean containsfft, boolean containsdac, HashMap<Pair, Long> dict){
        Pair device = new Pair(node, containsfft, containsdac);
        long count = 0;
        if (dict.containsKey(device)){
            count = dict.get(device);
            return count;
        }
        if (node.equals("out")) {
            return containsfft && containsdac?1:0;

        }
        String[] neighbours = deviceAndOutput.get(node);
        containsfft = node.equals("fft") || containsfft;
        containsdac = node.equals("dac") || containsdac;
        for (String each: neighbours){
                count +=traverse2(each, containsfft, containsdac, dict);

        }
        Pair newDevice = new Pair(node,containsfft,containsdac);
        dict.putIfAbsent(newDevice,count);
        return count;
    }


    public static void run(){
        Queue<String> queue = new PriorityQueue<>();
        queue.offer("you");
        int count = traverse( queue);
        System.out.println("Part one: "+count);
        long count2 = traverse2("svr", false, false, new HashMap<>())  ;
        System.out.println("Part two: "+count2);
    }
    record Pair(String node, boolean fft, boolean dac) {}

}
