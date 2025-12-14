import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class AoCInputDownloader {
    public void downloadInput( String urlString, String outputFileName) throws IOException {
        // Create a URL object
        URL url = new URL(urlString);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the User-Agent header (sometimes required by AoC)
        connection.setRequestProperty("User-Agent", "AoC Java Downloader");

        // Set the Cookie header with your session ID
        connection.setRequestProperty("Cookie", "session=" + System.getProperty("SessionID"));

        // Connect to the URL
        connection.connect();

        // Read the input data from the URL
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            // Write the data to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    writer.write(inputLine);
                    writer.newLine();
                }
            }
        }

        System.out.println("Input data downloaded and saved to " + outputFileName);
    }
}


