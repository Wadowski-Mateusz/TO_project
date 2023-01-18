package org.shop.app;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ClientApp {
    final static int PORT = 7777;
    final static String HOSTNAME = "localhost";
    String t;

    public static void main(String[] args) throws IOException {
        try {
            boolean logged = false;
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
                String t;
                if (!logged) {
                    switch (readerInput) {
                        //logowanie
                        case "login":
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            t = serverInput.readLine();
                            System.out.println(t);
                            if (t.compareTo("Pomyslne logowanie!") == 0) {
                                logged = true;
                                t = serverInput.readLine();
                                if (t.compareTo("Jestes adminem.") == 0) {
                                    System.out.println(t);
                                }
                            }
                            break;
                        case "register":
                            //email
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //haslo
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //powtorz haslo
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //imie
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //nazwisko
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //numer telefonu
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //ulica
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //nr domu
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //kod pocztowy
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //miasto
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            //wojewodztwo
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            break;
                        case "login debug":
                            logged = true;
                            break;
                    }
                } else if (logged) {
                    switch (readerInput) {
                        // wyswietlenie kategorii produktow
                        case "show categories":
                        case "show cpu":
                        case "show fridges":
                        case "show laptops":
                        case "show microwaves":
                            t = serverInput.readLine();
                            Integer x = Integer.parseInt(t);
                            for (int i = 0; i < x; ++i) {
                                buf = serverInput.readLine();
                                System.out.println(i + 1 + ". " + buf);
                            }
                            break;

                        case "logout":
                            t = serverInput.readLine();
                            System.out.println(t);
                            break;

                        //dodanie produktu do koszyka
                        case "show product":
                            t = serverInput.readLine();
                            System.out.println(t);
                            readerInput = clientInput.readLine();
                            printWriter.println(readerInput);
                            break;

                        //wyswietlenie koszyka
                        case "show cart":
                            break;
                    }
                }
            }

            // Closing the connection
            socket.close();

        } catch (SocketException e) {
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
