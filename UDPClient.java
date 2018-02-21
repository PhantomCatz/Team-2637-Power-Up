package networkingHell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;


public class UDPClient {
	DatagramSocket Socket;

	public UDPClient() {

	}

	public void createAndListenSocket() {
		try {
			Socket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			byte[] incomingData = new byte[1024];
			VisionObject visionObj = new VisionObject(30,120);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outputStream);
			os.writeObject(visionObj);
			byte[] data = outputStream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 8765);
			Socket.send(sendPacket);
			System.out.println("Msg sent from client");
			DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
			Socket.receive(incomingPacket);
			String response = new String(incomingPacket.getData());
			System.out.println("Server Response:" + response);
			Thread.sleep(2000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UDPClient client = new UDPClient();
		client.createAndListenSocket();
	}
}
