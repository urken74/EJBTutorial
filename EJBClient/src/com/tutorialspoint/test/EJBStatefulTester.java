/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.test;

import com.tutorialspoint.stateful.LibraryStatefulSessionBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

/**
 *
 * @author gorerk
 */
public class EJBStatefulTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;
    LibraryStatefulSessionBeanRemote libraryBean;
         
    {
        try {
            libraryBean = lookupLibrarySessionBean();
             System.out.println("libraryBean = "+libraryBean );
           
        } catch (NamingException ex) {
            Logger.getLogger(EJBStatefulTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {

        EJBStatefulTester ejbTester = new EJBStatefulTester();

        ejbTester.testStatefulEjb();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testStatefulEjb() {
        try {
            int choice = 1;
            while (choice != 2) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    libraryBean.addBook(bookName);
                } else if (choice == 2) {
                    break;
                }
            }
            List<String> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            for (int i = 0; i < booksList.size(); ++i) {
                System.out.println((i + 1) + ". " + booksList.get(i));
            }
            LibraryStatefulSessionBeanRemote libraryBean1 = lookupLibrarySessionBean();
            List<String> booksList1 = libraryBean1.getBooks();
            System.out.println(
                    "***Using second lookup to get library stateless object***");
            System.out.println(
                    "Book(s) entered so far: " + booksList1.size());
            for (int i = 0; i < booksList1.size(); ++i) {
                System.out.println((i + 1) + ". " + booksList1.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

  
      private  static LibraryStatefulSessionBeanRemote lookupLibrarySessionBean() throws NamingException  {
         
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
 
        Properties props = new Properties();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        InitialContext initialContext = new InitialContext(props);
      
        final String appName = "";
        final String moduleName = "EJBComponent";
        final String distinctName = "";
        final String beanName = "LibraryStatefulSessionBean";
        final String viewClassName = LibraryStatefulSessionBeanRemote.class.getName();
        return (LibraryStatefulSessionBeanRemote) initialContext.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
    }
 
}
