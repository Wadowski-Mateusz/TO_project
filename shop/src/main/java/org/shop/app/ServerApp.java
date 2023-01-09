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

//        try {
//            // Creation of a server socket on port 7070
//            ServerSocket serverSocket = new ServerSocket(9999);
//            System.out.println("host: "  + InetAddress.getLocalHost());
//            System.out.println("port: "  + serverSocket.getLocalPort());
//
//            // Connection
//            Socket socket = serverSocket.accept();
//
//            // Reading and writing to the connection
//            BufferedReader myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintStream myOutput = new PrintStream(socket.getOutputStream());
//
//            // Read from input stream
//            String buf = myInput.readLine();
//            if (buf != null){
//                System.out.println("buf: " + buf);
//                myOutput.print("received");
//            }
//
//            // Closing the connection
//            socket.close();
//            System.out.println("Server close");
//
//        } catch (IOException e){
//            e.printStackTrace();
//            System.out.println("Error");
//        }


    }
}
