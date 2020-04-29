package com.wojnarowicz.socket.server;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketLargeFileServer {
	private final static int port = 9876;

	public static void main(String[] args) {
		SocketLargeFileServer server = new SocketLargeFileServer();
		SocketChannel socketChannel = server.createServerSocketChannel();
		server.startServer(socketChannel);
	}

	private SocketChannel createServerSocketChannel() {
		ServerSocketChannel serverSocketChannel = null;
		SocketChannel socketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			socketChannel = serverSocketChannel.accept();
			System.out.println("Connection established...." + socketChannel.getRemoteAddress());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return socketChannel;
	}
	
	private void startServer(SocketChannel socketChannel) {
		RandomAccessFile file = null;
	}
}
