/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.test;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;
import com.tutorialspoint.timer.TimerSessionBeanRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

public class EJBTimerTester {
   BufferedReader brConsoleReader = null; 
   
   {   
   initEjbClientContext();
      brConsoleReader = 
      new BufferedReader(new InputStreamReader(System.in));
   }
   
   public static void main(String[] args) {

      EJBTimerTester ejbTester = new EJBTimerTester();

      ejbTester.testTimerService();
   }
   
  
   private void testTimerService(){
      try {
         TimerSessionBeanRemote timerServiceBean = lookupTimerSessionBean();

         System.out.println("["+(new Date()).toString()+ "]" + "timer created.");
         timerServiceBean.createTimer(10*1000);            

      } catch (NamingException ex) {
         ex.printStackTrace();
      }
   }
   
   private static void initEjbClientContext(){
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
   
   private  static TimerSessionBeanRemote lookupTimerSessionBean() throws NamingException  {
        Properties props = new Properties();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        InitialContext initialContext = new InitialContext(props);
      
        return (TimerSessionBeanRemote) initialContext.lookup("ejb:/EJBComponent//TimerSessionBean!" +  TimerSessionBeanRemote.class.getName());
    }
}