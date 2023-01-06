package org.shop.app;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class ClientApp {
    final static int PORT = 7777;
    final static String HOSTNAME = "localhost";
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket(HOSTNAME, PORT);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String readerInput = clientInput.readLine();
                printWriter.println(readerInput);
                if (readerInput.isEmpty())
                    break;
                // Checking server respond
                String buf = serverInput.readLine();
                if (buf != null)
                    System.out.println("Server respond: " + buf);
            }

            // Closing the connection
            socket.close();

        }catch(ConnectException e){
            System.out.println("Server is unavailable.");
            System.exit(1);
        } catch(SocketException e){
            System.out.println("Server is unavailable.");
            System.exit(1);
        }
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
