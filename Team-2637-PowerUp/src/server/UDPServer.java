package server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	static DatagramSocket socket = null;
	static byte[] incomingData;
	public UDPServer() {
		try {
			socket = new DatagramSocket(2637);
			incomingData = new byte[1024];
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static VisionObject getDatagramPacket() {
		try {
				DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
				socket.receive(incomingPacket);
				byte[] data = incomingPacket.getData();
				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				VisionObject visionObj = (VisionObject) is.readObject();
				return visionObj;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
		return null;
	}
	
}