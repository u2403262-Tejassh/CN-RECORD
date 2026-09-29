import java.net.*;

public class UDPClient {
	public static void main(String[] args) {
		try {
			DatagramSocket clientSocket = new DatagramSocket();

			String message = "Hello from UDP Client!";
			byte[] sendData = message.getBytes();

			InetAddress serverAddress = InetAddress.getByName("localhost");

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
			serverAddress, 9876);
			clientSocket.send(sendPacket);

			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);

			String reply = new String(receivePacket.getData()).trim();
			System.out.println("Server says: " + reply);

			clientSocket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
