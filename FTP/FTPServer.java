import java.io.*;
import java.net.*;

public class FTPServer {

	public static void main(String[] args) throws Exception {

		ServerSocket ss = new ServerSocket(5000);
		System.out.println("Server started...");
		System.out.println("Waiting for client...");
		Socket s = ss.accept();
		System.out.println("Client connected.");
		DataInputStream dis = new DataInputStream(s.getInputStream());
		String fileName = dis.readUTF();
		System.out.println("Receiving file: " + fileName);
		FileOutputStream fos = new FileOutputStream("received_" + fileName);
		byte[] buffer = new byte[4096];
		int bytesRead;
		
		while ((bytesRead = dis.read(buffer)) != -1)	{
			fos.write(buffer, 0, bytesRead);
		}

		fos.close();
		dis.close();
		s.close();
		ss.close();
		System.out.println("File received successfully.");

	}

}
