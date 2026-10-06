import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;

public class client {
    public static void main(String[] args) throws IOException {
        // Step 1: Connect to the server's "doorway" on localhost, port 6000
        Socket pipeToServer = new Socket("127.0.0.1", 6000); 

        // Step 2: Setup Streams (Perspective: We are the Client)
        // Data flowing INTO the client from the pipe
        Scanner input = new Scanner(pipeToServer.getInputStream()); 
        
        // Data flowing OUT OF the client into the pipe
        PrintStream output = new PrintStream(pipeToServer.getOutputStream()); 
        
        // Step 3: Read a player name from the user's keyboard
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter a cricketer's name to check ODI centuries: ");
        String playerName = sc.nextLine(); 
        
        // Step 4: Push the name OUT into the pipe towards the server
        output.println(playerName); 
        
        // Step 5: Read the response coming INTO the client from the server
        String serverResponse = input.nextLine(); 
        System.out.println("Server says: " + serverResponse); 
        
        // Step 6: Close the connection
        pipeToServer.close(); 
    }
}
