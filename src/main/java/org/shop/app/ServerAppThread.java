package org.shop.app;

import org.shop.classes.DatabaseConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Single thread for user. Every user has their own thread with their own socket to use.*/
public class ServerAppThread extends Thread {
    private final Socket socket;

    public ServerAppThread(Socket socket){
        this.socket = socket;
    }

    /**
     * Thread main() */
    public void run(){
        final DatabaseConnector dbc = DatabaseConnector.getInstance();
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String message;

            while ((message  = bufferedReader.readLine()) != null){
                if(message.isEmpty()) {
                    System.out.println("Thread end.");
                    break;
                }
                System.out.println("Received: " + message);
                printStream.println("[Thread] received");
            }
            socket.close();
        } catch (SocketException e){
            System.out.println("Client disconnected");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
