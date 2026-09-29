import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClientChat {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 5000);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			Scanner scanner = new Scanner(System.in);
			
			while (true) {
				System.out.print("Send to Server: ");
				String msg = scanner.nextLine(); 
				out.println(msg);
				
				String serverMessage = in.readLine();
				System.out.println("Server says: " + serverMessage);
				if ("exit".equalsIgnoreCase(msg)) {
				    System.out.println("Disconnecting from server...");
				    break; 
               		}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
