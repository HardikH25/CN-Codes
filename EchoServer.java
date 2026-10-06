import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;
public class EchoServer {
    public static void main(String[] args) throws IOException {
        // Step 1: Open the server's "doorway" (ServerSocket) on port 5089
        ServerSocket server = new ServerSocket(5089);
        
        // Step 2: Wait for a client to connect. 
        // The returned 'client' object is the server's local end of the connection pipe.
        Socket client = server.accept();  //the pipe itself - the established connection 
        
        // Step 3: Setup Streams (Perspective: We are the Server)
        // client.getInputStream() -> The stream of data flowing INTO the server from the pipe.
        // We use a Scanner to easily read this incoming text.
        Scanner input = new Scanner(client.getInputStream()); 
        
        // client.getOutputStream() -> The stream of data flowing OUT OF the server into the pipe.
        // We use a PrintStream to easily push text out.
        PrintStream output = new PrintStream(client.getOutputStream());

        // Step 4: Read data that arrived INTO the server (sent by the client)
        String message = input.nextLine(); 
        System.out.println("Message from client: " + message); 
        
        // Step 5: Write data OUT OF the server (which flows directly to the client's InputStream)
        output.println(message); 
        
        // Step 6: Close the connection
        client.close(); 
        server.close(); 
    }
}