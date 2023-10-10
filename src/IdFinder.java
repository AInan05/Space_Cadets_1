// This is my attempt on the Space Cadets Challenge 1 - Reading and Writing Strings from Files and URLs
// import necessary libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class IdFinder {
    // main method
    public static void main(String[] args) throws IOException {
        // Buffered reader object for reading the user input
        BufferedReader input_reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter ID: ");
        String id = input_reader.readLine();
        // Checks if the entered text is not empty string
        if (!id.isEmpty()) {
            // initiate URL object and assign the complete url
            URL id_url = new URL("https://www.ecs.soton.ac.uk/people/" + id);
            // open url connection
            HttpURLConnection url_connection = (HttpURLConnection) id_url.openConnection();
            url_connection.setRequestMethod("GET");
            // Buffer Reader object for reading html
            BufferedReader webReader = new BufferedReader(new InputStreamReader(url_connection.getInputStream()));
            boolean found = false;
            // Read line before entering the while loop to check if the line is null
            String line = webReader.readLine();
            // Check through every line of the html
            while (line != null) {
                // The line that contains the name contains og:title
                if (line.contains("og:title")) {
                    found = true;
                    int length = line.length();
                    // THe name is within these boundaries
                    String name = line.substring(35, (length - 5));
                    System.out.println("Name found: " + name);
                    break;
                }
                line = webReader.readLine();
            }
            if (!found) {
                System.out.println("Name couldn't be found...");
            }
        } else {
            System.out.println("Please enter an id...");
        }
    }
}