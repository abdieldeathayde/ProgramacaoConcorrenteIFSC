package clientAndServer;
import java.net.*;
import java.io.*;

public class Client implements Runnable {
	public DatagramSocket aSocket = null;

	public Client(DatagramSocket aSocket) {
		this.aSocket = aSocket;
	}

	public static void main(String args[]) {
		// args give message contents and server hostname
		DatagramSocket aSocket = null;
		
		
			
		while (true) {
			try {
			
				aSocket = new DatagramSocket();
				
				String msg = "ping";
				byte[] m = msg.getBytes();
				InetAddress aHost = InetAddress.getByName("localhost");
				int serverPort = 6789;
				DatagramPacket request = new DatagramPacket(m, msg.length(),
						aHost, serverPort);
				
				// Send a message
				Thread.sleep(1000);
				aSocket.send(request);
				
				// Prepare a empty message for a response
				byte[] buffer = new byte[1000];
				DatagramPacket response = new DatagramPacket(buffer, buffer.length);
				
				//Receive response
				aSocket.receive(response);
				
				// Print response
				String resp = new String(response.getData());
				System.out.println("Client received: " + resp);
			} catch (IOException e) {
				System.err.println("Erro ao lidar com a conexão do cliente: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				aSocket.close();
			}
		} 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
			
	
	

	

	
}