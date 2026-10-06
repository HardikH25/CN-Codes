import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;
public class EchoClient {
    public static void main(String[] args) throws IOException {
        // Step 1: Connect to the server's "doorway" on localhost, port 5089. 
        // The returned 'server' object is the client's local end of the connection pipe.
        Socket server = new Socket("127.0.0.1", 5089); 
        
        // Step 2: Setup Streams (Perspective: We are the Client)
        // server.getInputStream() -> The stream of data flowing INTO the client from the pipe.
        // (This is data that the server pushed into its OutputStream).
        Scanner input = new Scanner(server.getInputStream()); 
        
        // server.getOutputStream() -> The stream of data flowing OUT OF the client into the pipe.
        // (This data will land in the server's InputStream).
        PrintStream output = new PrintStream(server.getOutputStream()); 
        
        // Step 3: Read a message from the user's keyboard
        Scanner sc = new Scanner(System.in); 
        String message = sc.nextLine(); 
        
        // Step 4: Push the message OUT OF the client, into the pipe towards the server
        output.println(message); 
        
        // Step 5: Read the response coming INTO the client from the server
        String reply = input.nextLine(); 
        System.out.println("Reply from server: " + reply); 
        // Step 6: Close the connection
        server.close(); 
    }
}