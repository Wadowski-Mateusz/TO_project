package org.shop.classes;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class ClientApp {
    final static int PORT = 7777;
    final static String HOSTNAME = "localhost";
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(HOSTNAME, PORT);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new java.io.BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String readerInput = bufferedReader.readLine();
            printWriter.println(readerInput);
            if(readerInput.isEmpty())
                break;
        }

        // Closing the connection
        socket.close();
        System.out.println("Client end");

//        try {
//            // Connecting to server
//            System.out.println("Connecting to server...");
//            Socket socket = new Socket("localhost", 9999);
//
//            // Reading and writing to the connection
//            BufferedReader myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintStream myOutput = new PrintStream(socket.getOutputStream());
//
//            // Sending message to the server
//            myOutput.println("Client message");
//
//            // Checking server respond
//            String buf = myInput.readLine();
//            if(buf != null)
//                System.out.println("Server respond: " + buf);
//
//            // Closing the connection
//            myOutput.close();
//            socket.close();
//            System.out.println("Client end");
//
//        } catch (ConnectException e){
//            System.out.println("Server is offline");
//            System.exit(404);
//        } catch (IOException e){
//            e.printStackTrace();
//            System.out.println("Error");
//        }
    }
}
