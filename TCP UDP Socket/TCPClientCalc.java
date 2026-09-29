import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TCPClientCalc {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 5000);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			Scanner scanner = new Scanner(System.in);
			
			while (true) {
				System.out.print("Send to Calculator: ");
				String msg = scanner.nextLine(); 
				out.println(msg);
				
				String serverMessage = in.readLine();
				System.out.println(serverMessage);
				if ("exit".equalsIgnoreCase(msg)) {
				    System.out.println("Disconnecting from Calculator...");
				    break; 
               		}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
