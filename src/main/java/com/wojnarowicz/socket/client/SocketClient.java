package com.wojnarowicz.socket.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;

import com.wojnarowicz.socket.data.FileData;
import com.wojnarowicz.socket.data.Response;

public class SocketClient {

    private final InetAddress host;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketClient() throws UnknownHostException, IOException {
        host = InetAddress.getByName("192.168.10.45");
    }

    public void sendFile(Path path) {
        FileData fileData = new FileData(path);
        fileData.setDestinationDirectory("c:\\apps\\");
        sendDataPackage(fileData);
    }

    public void sendDataPackage(FileData fileData) {
        try {
            // establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);

            // write to socket using ObjectOutputStream
            System.out.println("Sending request to Socket Server");
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(fileData);
            oos.flush();
            
            // read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            Response response = (Response) ois.readObject();
            System.out.println("Message: " + response.getMessage());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            // close resources
            socket.close();
            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        // get the localhost IP address, if server is running on some other IP, you need
        // to use that
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
        }
    }
}
