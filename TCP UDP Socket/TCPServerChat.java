import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServerChat {
	public static void main(String[] args) {
		try {	
			ServerSocket serverSocket = new ServerSocket(5000);
			System.out.println("Server is listening on port 5000...");
			
			Socket socket = serverSocket.accept();
			System.out.println("Client connected.");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner scanner = new Scanner(System.in);
			
			while (true) {
				String clientMessage = in.readLine();
				System.out.println("Client says: " + clientMessage);
				
				System.out.print("Send to client: ");
				String msg = scanner.nextLine();
				out.println(msg);
				
				if ("exit".equalsIgnoreCase(msg)) {
		               	System.out.println("Server shutting down connection...");
		                	break;
				}
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
