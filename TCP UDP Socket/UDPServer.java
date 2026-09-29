import java.net.*;
public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            
            System.out.println("Server is running and waiting for a message...");
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            
            String clientMessage = new String(receivePacket.getData()).trim();
            System.out.println("Client says: " + clientMessage);
            
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            
            String reply = "Hello from UDP Server!";
            byte[] sendData = reply.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
       
            serverSocket.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

