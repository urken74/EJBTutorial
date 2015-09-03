/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.helper;

import com.tutorialspoint.timer.TimerSessionBeanRemote;
import java.util.Properties;
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
public class ConnectHelper {
    public static void initEjbClientContext(){
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
   
   public  static Object lookupStatelessSessionBean(String beanName, String viewBeanName) throws NamingException  {
        Properties props = new Properties();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        InitialContext initialContext = new InitialContext(props);
        
        System.out.println("ejb:/EJBComponent//"+beanName+"!" +  viewBeanName);
        return  initialContext.lookup("ejb:/EJBComponent//"+beanName+"!" +  viewBeanName);
    } 
}
