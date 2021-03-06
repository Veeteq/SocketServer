package com.wojnarowicz.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GreetingServer extends Thread {
	private ServerSocket serverSocket;

	public GreetingServer(int portNumber) throws IOException {
		this.serverSocket = new ServerSocket(portNumber);
		this.serverSocket.setSoTimeout(100000);
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				
				System.out.println("Just connected to " + server.getRemoteSocketAddress());
	            DataInputStream in = new DataInputStream(server.getInputStream());
	            
	            System.out.println(in.readUTF());
	            DataOutputStream out = new DataOutputStream(server.getOutputStream());
	            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
	            server.close();	 

			} catch (SocketTimeoutException e) {
				System.out.println("Socket timed out!");
	            break;
			} catch(IOException e) {
	            e.printStackTrace();
	            break;	            
			}
		}
	}

	public static void main(String[] args) {
		int portNumber = Integer.parseInt(args[0]);
		try {
			Thread t = new GreetingServer(portNumber);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
