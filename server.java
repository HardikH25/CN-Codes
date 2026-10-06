import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;
import java.util.HashMap;

public class server {
    public static void main(String[] args) throws IOException {
        // Prepare our data source
        HashMap<String, Integer> odiCenturies = new HashMap<>();
        odiCenturies.put("Sachin Tendulkar", 49);
        odiCenturies.put("Virat Kohli", 50);
        odiCenturies.put("Ricky Ponting", 30);
        odiCenturies.put("Rohit Sharma", 31);
        odiCenturies.put("Sanath Jayasuriya", 28);

        // Step 1: Open the server's "doorway" on port 6000
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Server is running and waiting for a connection...");
        
        // Step 2: Wait for a client to connect and grab our end of the pipe
        Socket pipeToClient = serverSocket.accept();  
        System.out.println("Client connected!");
        
        // Step 3: Setup Streams (Perspective: We are the Server)
        // Data flowing INTO the server from the pipe
        Scanner input = new Scanner(pipeToClient.getInputStream()); 
        
        // Data flowing OUT OF the server into the pipe
        PrintStream output = new PrintStream(pipeToClient.getOutputStream());
        
        // Step 4: Read the player name requested by the client
        String playerName = input.nextLine(); 
        System.out.println("Client asked for: " + playerName); 
        
        // Step 5: Process the request
        if (odiCenturies.containsKey(playerName)) {
            int centuries = odiCenturies.get(playerName);
            // Push the result OUT into the pipe
            output.println(playerName + " has " + centuries + " ODI centuries."); 
        } else {
            // Push an error message OUT into the pipe
            output.println("Error: Player '" + playerName + "' not found in database.");
        }
        
        // Step 6: Close the connection
        pipeToClient.close(); 
        serverSocket.close(); 
    }
}
