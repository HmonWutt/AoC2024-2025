import java.io.*;
import java.util.ArrayList;
public class ReadFromFile {

    public ArrayList<String> loadAsArray(String filename) throws Exception {
        ArrayList<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
           while (br.ready()){
               input.add(br.readLine());
           }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return input;
    }
    public String loadAsString(String filename) throws Exception {

        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            while(br.ready()){
                output.append(br.readLine());

            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return output.toString();
    }
    public String keepLineBreaks(String filename) throws Exception{
        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return output.toString();
    }



}