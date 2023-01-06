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
    private Socket socket;
    private DatabaseConnector databaseConnector;

    public ServerAppThread(Socket socket){
        this.socket = socket;
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    /**
     * Thread main() */
    public void run(){

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String message = null;

            while ((message  = bufferedReader.readLine()) != null){
                if(message.isEmpty()) {
                    System.out.println("Thread end.");
                    break;
                }
                System.out.println("Recived: " + message);
                printStream.println("[Thread] recevied");
            }
            socket.close();
        } catch (SocketException e){
            System.out.println("Client disconnected");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
