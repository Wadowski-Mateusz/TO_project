package org.shop.classes;

import org.shop.interfaces.Observer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
public class Newsletter implements Observer   {

    private volatile boolean isSending = false;
    private volatile List<Integer> usersId;
    private volatile Map<String, Float> products;
    private static volatile Newsletter instance;
    private static String DIR = "mails/";

    private Newsletter() {
        usersId = new ArrayList<>();
        products = new TreeMap<>();
    }

    public static void setDIR(String dir){
        DIR = dir;
    }

    public static Newsletter getInstance() {

        Newsletter result = instance;
        if (result != null)
            return result;

        synchronized (Newsletter.class) {
            if (instance == null)
                instance = new Newsletter();
            return instance;
        }

    }

    @Override
    public void update(String productName, float priceDiff) {
        products.remove(productName);

        if(priceDiff < 0)
            products.put(productName, priceDiff);

        if(products.size() > 3)
            sendMails();

    }

    public void addUser(User user){
        if(!usersId.contains(user.getId()))
            usersId.add(user.getId());
    }

    public void deleteUser(User user){
        if(usersId.contains(user.getId()))
            for(int i = 0; i < usersId.size(); i++)
                if(user.getId() == usersId.get(i)) {
                    usersId.remove(i);
                    break;
                }
    }

    private void sendMails(){
        if(isSending)
            return;

        isSending = true;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        StringBuilder msg = new StringBuilder(dtf.format(now) + "\nPonizsze produkty sa na promocji: \n");

        for (Map.Entry<String, Float> entry : products.entrySet())
            msg.append(entry.getKey()).append(" o ").append(entry.getValue() * (-1.0F)).append(" zlotych\n");
        products.clear();

        for(Integer id : usersId){
            User u = (User) User.convertFromRecord(id);
            assert u != null;
            String mail = u.getEmail();
            String path = DIR + mail;
            try {
                Writer output = new BufferedWriter(new FileWriter(path, true));
                output.append(System.lineSeparator()).append(msg);
                output.close();
            } catch (IOException e) {
                System.out.println("Observer error\t" + DIR + u.getEmail());
            }
        }

        isSending = false;
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public Map<String, Float> getProducts() {
        return products;
    }
}
