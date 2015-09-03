/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.messagebean;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
 
@MessageDriven(
   name = "BookMessageHandler",
   activationConfig = {
      @ActivationConfigProperty( propertyName = "destinationType", 
                                 propertyValue = "javax.jms.Queue"),
      @ActivationConfigProperty( propertyName = "destination", 
                                 propertyValue ="/queue/BookQueue")
   }
)
public class LibraryMessageBean implements MessageListener {
 
   @Resource
   private MessageDrivenContext mdctx;  
 
   @EJB
   LibraryPersistentBeanRemote libraryBean;
 
   public LibraryMessageBean(){        
        System.out.println("LibraryMessageBean() constructor");
    }
 
   public void onMessage(Message message) {
      ObjectMessage objectMessage = null;
      try {
         System.out.println("Got message: "+ message.getJMSDestination().toString());
         objectMessage = (ObjectMessage) message;
         Book book = (Book) objectMessage.getObject(); 
         libraryBean.addBook(book);
 
      } catch (JMSException ex) {
         mdctx.setRollbackOnly();
      }       
   }   
}