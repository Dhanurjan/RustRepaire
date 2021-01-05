/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rustrepaire;

/**
 *
 * @author Dhanurjan
 */
import java.long.system.Logger;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificationSending {
     public static void SendMail(String Recipient) throws Exception{
         System.out.println("Message sent successfully");
         Properties properties = new Properties();  
        
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable","true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
         properties.put("mail.smtp.port", "587");
         
         String myAccountEmail = "smchrist98@gmail.com";
         String password = "xxxxxxxxx";
         
        Session session = session = Session.getDefaultInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(PasswordAuthentication,password );
            }
        });
         
        Message message = prepareMessage(Session session, String myAccountEmail);
        
        Transport.send(message);
        System.out.println("Message sent successfully");
        
    } 
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
       try { 
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("My first email from Java App");
        message.setText("Hey There, \n Look at my email");
        return message;
        } catch (Exception ex) {
            
        }
       return null;
    }
}
}

public class MailSending {
    
}