package networkingHell;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
	DatagramSocket socket = null;

	public UDPServer() {
	}
	

	public void createAndListenSocket() {
		try {
			socket = new DatagramSocket(8765);
			byte[] incomingData = new byte[1024];
	

			while (true) {
				DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
				socket.receive(incomingPacket);
				byte[] data = incomingPacket.getData();
				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				try {
					VisionObject visionObj = (VisionObject) is.readObject();
					System.out.println("Received = "+visionObj);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				InetAddress IPAddress = incomingPacket.getAddress();
				int port = incomingPacket.getPort();
				String reply = "Ack";
				byte[] replyBytea = reply.getBytes();
				DatagramPacket replyPacket = new DatagramPacket(replyBytea, replyBytea.length, IPAddress, port);
				socket.send(replyPacket);
				Thread.sleep(1000);
				System.exit(0);
			}

			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException i) {
				i.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	

	public static void main(String[] args) 
	{
		UDPServer server = new UDPServer();
		server.createAndListenSocket();
	}
}
