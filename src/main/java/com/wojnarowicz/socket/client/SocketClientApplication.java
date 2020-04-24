package com.wojnarowicz.socket.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.wojnarowicz.socket.data.FileData;

public class SocketClientApplication {

    private static final String MY_DIR = "C:\\Users\\la289dm\\Documents\\Private\\";
    private static final int MAX_DEPTH = 1;

    public static void main(String[] args) {
        
        try {
            SocketClient client = new SocketClient();
            Files.walk(Paths.get(MY_DIR),MAX_DEPTH)
            .filter(Files::isRegularFile)
            .filter(path -> path.toFile().length() < 10000000)
            .filter(path -> path.toString().endsWith(".pdf"))
            .forEach(path -> {
                System.out.println("Sending file: " + path.getFileName());
                client.sendFile(path);   
            });
            
            FileData exitPackage = new FileData();
            exitPackage.setFileSize(-1);
            client.sendDataPackage(exitPackage);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
}
