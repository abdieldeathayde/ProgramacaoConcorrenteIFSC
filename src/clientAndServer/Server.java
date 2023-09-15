package clientAndServer;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String args[]) {
		DatagramSocket aSocket = null;

		while (true) {
			try {

				// Create a socket on port 6789
				aSocket = new DatagramSocket(6789);

				Thread clientThread = new Thread(new Client(aSocket));
				clientThread.start();
				// Prepare an empty message
				byte[] buffer = new byte[1000];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);

				// Receive a message
				aSocket.receive(request);

				// Prepare a response
				String resp = "pong " + new String(buffer);
				byte[] m = resp.getBytes();
				// Obtain response address/port from request
				DatagramPacket response = new DatagramPacket(m, resp.length(), request.getAddress(), request.getPort());

				// Send response
				System.out.println("Msg: " + resp);
				Thread.sleep(1000);
				aSocket.send(response);
			} catch (java.net.BindException e) {
				System.err.println("A porta " + aSocket + " Já está em uso");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				aSocket.close();
			}
		}
	}
}
