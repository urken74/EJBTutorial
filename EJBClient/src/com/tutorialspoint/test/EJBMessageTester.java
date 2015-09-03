/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.test;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;
 
public class EJBMessageTester {
 
   BufferedReader brConsoleReader = null; 
//   InitialContext ctx;
   static QueueConnectionFactory connectionFactory;
   static Queue queue;
   {
    
      try {
          setEjbSelector();
          lookupJMS();       
      } catch (NamingException ex) {
         ex.printStackTrace();
      }
      brConsoleReader = 
      new BufferedReader(new InputStreamReader(System.in));
   }
   
   public static void main(String[] args) {
 
      EJBMessageTester ejbTester = new EJBMessageTester();
 
      ejbTester.testMessageBeanEjb();
   }
   
   private void showGUI(){
      System.out.println("**********************");
      System.out.println("Welcome to Book Store");
      System.out.println("**********************");
      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
   }
   
   private void testMessageBeanEjb(){
 
      try {
         int choice = 1; 
         QueueConnection connection = connectionFactory.createQueueConnection("ejbUser2","ejbPassword");
         QueueSession session = 
         connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
         QueueSender sender = session.createSender(queue);
 
         while (choice != 2) {
            String bookName;
            showGUI();
            String strChoice = brConsoleReader.readLine();
            choice = Integer.parseInt(strChoice);
            if (choice == 1) {
               System.out.print("Enter book name: ");
               bookName = brConsoleReader.readLine();
               Book book = new Book();
               book.setName(bookName);
               ObjectMessage objectMessage = 
                  session.createObjectMessage(book);
               sender.send(objectMessage); 
            } else if (choice == 2) {
               break;
            }
         }
 
         LibraryPersistentBeanRemote libraryBean = lookupLibraryPersistentBean();
 
         List<Book> booksList = libraryBean.getBooks();
 
         System.out.println("Book(s) entered so far: " + booksList.size());
         int i = 0;
         for (Book book:booksList) {
            System.out.println((i+1)+". " + book.getName());
            i++;
         }           
      } catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }finally {
         try {
            if(brConsoleReader !=null){
               brConsoleReader.close();
            }
         } catch (IOException ex) {
            System.out.println(ex.getMessage());
         }
      }
   }   
   
   private static Destination lookupJMS() throws NamingException {
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "remote://localhost:4449");
        env.put(Context.SECURITY_PRINCIPAL, "ejbUser");
        env.put(Context.SECURITY_CREDENTIALS, "ejbPassword");
        Context context = new InitialContext(env);
        
        connectionFactory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        queue = (Queue) context.lookup("jms/queue/bookqueue");
        context.close();
        
        return queue;
   }
   
    private  static void setEjbSelector() throws NamingException  {
        Properties clientProp = new Properties();
        clientProp.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        clientProp.put("remote.connections", "default");
        clientProp.put("remote.connection.default.port", "4449");
        clientProp.put("remote.connection.default.host", "localhost");
        clientProp.put("remote.connection.default.username", "ejbUser");
        clientProp.put("remote.connection.default.password", "ejbPassword");
        clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
 
        EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(clientProp);
        ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
        EJBClientContext.setSelector(selector);  
    }
    
   private  static LibraryPersistentBeanRemote lookupLibraryPersistentBean() throws NamingException  {
    
        Properties props = new Properties();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        InitialContext initialContext = new InitialContext(props);
      
        final String appName = "";
        final String moduleName = "EJBComponent";
        final String distinctName = "";
        final String beanName = "LibraryPersistentBean";
        final String viewClassName = LibraryPersistentBeanRemote.class.getName();
        return (LibraryPersistentBeanRemote) initialContext.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }
}