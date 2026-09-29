import java.io.*;
import java.net.*;

public class FTPClient {

	public static void main(String[] args) throws Exception {

		Socket s = new Socket("localhost", 5000);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		File file = new File("test.txt");
		dos.writeUTF(file.getName());
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		int bytesRead;
		
		while ((bytesRead = fis.read(buffer)) != -1) {
			dos.write(buffer, 0, bytesRead);
		}

		fis.close();
		dos.close();
		s.close();
		System.out.println("File sent successfully.");
		
	}

}
