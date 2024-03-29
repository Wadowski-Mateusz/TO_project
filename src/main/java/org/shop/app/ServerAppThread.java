package org.shop.app;

import org.shop.classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Single thread for user. Every user has their own thread with their own socket to use.
 */
public class ServerAppThread extends Thread {
    private final Socket socket;

    public ServerAppThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Thread main()
     */
    public void run() {
        final DatabaseConnector dbc = DatabaseConnector.getInstance();
        try {
            boolean logged = false;
            boolean isAdmin = false;
            String email, password, password2, name, surname, phoneNumber, street, house, zip, city, voivodeship, id, buf;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String message;

            while ((message = bufferedReader.readLine()) != null) {
                if (message.isEmpty()) {
                    System.out.println("Thread end.");
                    break;
                }
                System.out.println("Received: " + message);
                printStream.println("[Thread] received");
                if (!logged) {
                    switch (message) {
                        case "login":
                            printStream.println("Podaj email");
                            email = bufferedReader.readLine();
                            printStream.println("Podaj haslo");
                            password = bufferedReader.readLine();
                            User user = new User(email, password);
                            UserChecker checker = new RegisteredUserChecker(null);
                            if (checker.check(user)) {
                                printStream.println("Pomyslne logowanie!");
                                logged = true;
                                CartBuilder cartBuilder = Cart.getBuilder();

                                UserChecker adminChecker = new RoleChecker(null);
                                if (adminChecker.check(user)) {
                                    printStream.println("Jestes adminem.");
                                    isAdmin = true;
                                }
                            } else {
                                printStream.println("Bledny email lub haslo!");
                            }
                            break;

                        //rejestracja konta
                        case "register":
                            printStream.println("Podaj email");
                            email = bufferedReader.readLine();
                            printStream.println("Podaj haslo");
                            password = bufferedReader.readLine();
                            printStream.println("Powtorz haslo");
                            password2 = bufferedReader.readLine();
                            printStream.println("Podaj imie");
                            name = bufferedReader.readLine();
                            printStream.println("Podaj nazwisko");
                            surname = bufferedReader.readLine();
                            printStream.println("Podaj numer telefonu");
                            phoneNumber = bufferedReader.readLine();
                            printStream.println("Podaj Ulice/miejscowosc");
                            street = bufferedReader.readLine();
                            printStream.println("Podaj nr numer domu");
                            house = bufferedReader.readLine();
                            printStream.println("Podaj kod pocztowy");
                            zip = bufferedReader.readLine();
                            printStream.println("Podaj miasto");
                            city = bufferedReader.readLine();
                            printStream.println("Podaj wojewodztwo");
                            voivodeship = bufferedReader.readLine();
                            Address address = new Address(street, house, zip, city, voivodeship);
                            UserBuilder builder = User.getBuilder();
                            builder.setEmail(email).setPassword(password).setName(name).setSurname(surname).setPhoneNumber(phoneNumber).setAddress(address);
                            //-----
                            User u = builder.build();
                            break;

                        case "login debug":
                            logged = true;

                        default:
                            break;

                    }
                } else if (logged) {
                    switch (message) {
                        // wyswietlenie kategorii produktow
                        case "show categories":
                            List<String> resultCategories = new ArrayList<>();
                            resultCategories.addAll(dbc.listFiles("data/products/agd"));
                            resultCategories.addAll(dbc.listFiles("data/products/computer/computer"));
                            resultCategories.addAll(dbc.listFiles("data/products/computer/computer_parts"));
                            System.out.println(resultCategories);
                            printStream.println(resultCategories.size());
                            for (int i = 0; i < resultCategories.size(); ++i) {
                                printStream.println(resultCategories.get(i).substring(0, resultCategories.get(i).length() - 4));
                            }
                            break;

                        //wyswietlenie produktu z podanej kategorii
                        case "show cpu":
                        case "show fridges":
                        case "show laptops":
                        case "show microwaves":
                            String category = "";
                            switch (message) {
                                case "show cpu":
                                    category = "cpu";
                                    break;
                                case "show fridges":
                                    category = "fridge";
                                    break;
                                case "show laptops":
                                    category = "laptop";
                                    break;
                                case "show microwaves":
                                    category = "microwave";
                                    break;
                            }
                            TreeMap<Integer, String> result = new TreeMap<>();
                            result = dbc.listProductsFromCategory(category);
                            String[] tab = result.entrySet().stream().map(entry -> entry.getValue() + " (id:" + entry.getKey() + ")").toArray(String[]::new);
                            printStream.println(tab.length);
                            for (String x : tab) {
                                printStream.println(x);
                            }
                            break;

                        case "logout":
                            logged = false;
                            printStream.println("Pomyslnie wylogowano");

                            //dodanie produktu do koszyka

                        case "show product":
                            printStream.println("Podaj id");
                            id = bufferedReader.readLine();
                            Product p = (Product) Product.convertFromRecord(Integer.parseInt(id));
                            Map<String, String> characteristics = p.getCharacteristics();
                            String mapAsString = characteristics.keySet().stream().map(key -> key + ": " + characteristics.get(key)).collect(Collectors.joining(", ", "{", "}"));
                            printStream.println(p.getName() + " (" + p.getCategory() + "); Znak: " + p.getMark() + "; Cena: " + p.getPrice() + "; Cechy: " + mapAsString);
                            printStream.println("Dodac do koszyka?");
                            buf = bufferedReader.readLine();
                            if(buf == "y"){
                                break;
                            }
                            break;

                        //wyswietlenie koszyka
                        case "show cart":
                            break;
                    }
                }
            }
            socket.close();
        } catch (SocketException e) {
            System.out.println("Client disconnected");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
