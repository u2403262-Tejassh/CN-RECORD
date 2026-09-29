import java.io.*;
import java.net.*;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import java.util.List;

public class TCPServerCalc {
	public static void main(String[] args) {
		try {	
			JShell jshell = JShell.create();
			ServerSocket serverSocket = new ServerSocket(5000);
			System.out.println("Calculator is listening on port 5000...");
			
			Socket socket = serverSocket.accept();
			System.out.println("User connected.");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			while (true) {
				String clientMessage = in.readLine();
				System.out.println("User says: " + clientMessage);


				if ("exit".equalsIgnoreCase(clientMessage)) {
		               	System.out.println("Calculator shutting down connection...");
		                	break;
				}

				List<SnippetEvent> events = jshell.eval(clientMessage);
				
				if (!events.isEmpty()) {
		                	SnippetEvent event = events.get(0);
		                
		                	// Check if the calculation was successful
		                	if ("VALID".equals(event.status().name()) && event.value() != null) {
		                    	out.println("Result: " + event.value());
		                    	System.out.println("Result: " + event.value());
		                	} else {
		                    	out.println("Error: Invalid syntax or incomplete expression.");
		           		System.out.println("Error: Invalid syntax or incomplete expression.");
		                	}
                    		} 
                    		else {
                        	out.println("Error: Could not parse input.");
				}
		
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

