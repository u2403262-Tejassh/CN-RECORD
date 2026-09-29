import java.io.*;
import java.net.*;
public class TCPClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 5000);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println("Hello from Client!");
			
			String serverMessage = in.readLine();
			System.out.println("Server says: " + serverMessage);
			
			socket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
