//package org.shop.classes;
//
//import java.util.Properties;
//
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//
//public class EmailObserver implements Observer {
//    private String email;
//    public EmailObserver(String email) {
//        this.email = email;
//    }
//    @Override
//    public void update(String productName, float productPrice) {
//        String message = "Product " + productName + " changed its price to " + productPrice + "!";
//        sendEmailTo(email, message);
//    }
//
//    private void sendEmailTo(String email, String message) {
//        sendEmail(email, "Special Offer!", message);
//    }
//
//    public void sendEmail(String to, String subject, String text) {
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.example.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("nie.wiem.3@interia.pl", "sklepzelektronika");
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("no-reply@example.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            message.setSubject(subject);
//            message.setText(text);
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}
