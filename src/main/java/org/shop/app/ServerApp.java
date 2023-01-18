package org.shop.app;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerApp {
    final static int PORT = 7777;
    final static String HOSTNAME = "localhost";
    public static void main(String[] args) throws IOException {

        System.out.println("Server is booting...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Port: " + PORT + "\nHostname: " + HOSTNAME);

        while(true){
            Socket socket = serverSocket.accept();
            new ServerAppThread(socket).start();
        }
    }
}
