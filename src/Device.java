import java.util.*;

public class Device {
    Deque<String> queue = new ArrayDeque<>();
    HashMap<String, String[]> deviceAndOutput = new HashMap<>();
    int outs = 0;
    HashSet<String> paths = new HashSet<>();
    Device(ArrayList<String> input, String startingPoint) {
        for (String each : input) {
            String[] splited = each.split(":");
            String[] values = splited[1].strip().split(" ");
            this.deviceAndOutput.put(splited[0], values);
        }

        this.outs = this.traverse(startingPoint);
        System.out.println("Outs: "+outs);

    }

    private int traverse(String startingDevice){
        int outs = 0;
        HashSet<String> visited = new HashSet<>();
        this.queue.addAll(List.of(this.deviceAndOutput.get(startingDevice)));
        while (!queue.isEmpty()){
            String currentDevice = queue.poll();
            if (currentDevice.equals("out")) {
                outs++;
            }
            else {
                    String[] nextDevices = this.deviceAndOutput.get(currentDevice);
                    this.queue.addAll(List.of(nextDevices));

            }
        }
        return outs;

    }

}