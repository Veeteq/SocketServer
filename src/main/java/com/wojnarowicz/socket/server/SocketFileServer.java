package com.wojnarowicz.socket.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.wojnarowicz.socket.data.FileData;
import com.wojnarowicz.socket.data.Response;


public class SocketFileServer {

	private final static int port = 9876;
    private static ServerSocket server;

    public void startServer() throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        while(true) {
        	System.out.println("Waiting for the client request");

        	Socket socket = server.accept();
        	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        	FileData fileData = (FileData) ois.readObject();
        	
        	if(fileData.getFileSize() > 0) {
        		System.out.println("Message Received: " + fileData.toString());
        		Path savedFile = saveFile(fileData);

        		Response response = new Response();
        		response.setMessage("Data received " + savedFile.toString());
        		response.setSuccess(true);
        		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        		oos.writeObject(response);

        		ois.close();
        		oos.close();
        		socket.close();
        	} else if(fileData.getFileSize() == -1) {
        		Response response = new Response();
        		response.setMessage("Closing transmission.");
        		response.setSuccess(true);
        		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        		oos.writeObject(response);
        		
        		break;
        	}
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }

    private Path saveFile(FileData fileData) {
        if(fileData.getFileSize() <= 0) {
            return null;
        }

        if (!new File(fileData.getDestinationDirectory()).exists()) {
        	new File(fileData.getDestinationDirectory()).mkdirs();
        }
        
        String outputFile = fileData.getDestinationDirectory() + fileData.getFileName();
        
        Path savedPath = null;
        try {
            Path path = Paths.get(outputFile);
            savedPath = Files.write(path, fileData.getFileData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savedPath;
    }
    
    public static void main(String[] args) {
    	SocketFileServer server = new SocketFileServer();
    	
    	try {
			server.startServer();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
