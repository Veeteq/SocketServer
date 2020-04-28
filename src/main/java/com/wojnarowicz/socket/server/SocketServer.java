package com.wojnarowicz.socket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.wojnarowicz.socket.data.DataPackage;

public class SocketServer {

    private static ServerSocket server;
    private static int port = 9876;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        while(true) {
            System.out.println("Waiting for the client request");

            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            DataPackage dataPackage = (DataPackage) ois.readObject();
            saveFile(dataPackage.getFile(), dataPackage.getName());
            System.out.println("Message Received: " + dataPackage.toString());

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Data received " + dataPackage.toString());

            ois.close();
            oos.close();
            socket.close();
            if(dataPackage.getLength() == -1) {
                break;
            }
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }

    private static void saveFile(byte[] file, String fileName) {
        if(file.length == 0) {
            return;
        }
        
        try {
            Path path = Paths.get(fileName);
            Files.write(path, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
