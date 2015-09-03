/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.stateless;

import com.tutorialspoint.entity.Book;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gorerk
 */
@Stateless
@WebService(serviceName="LibraryService")
public class LibraryPersistentBean implements LibraryPersistentBeanRemote {

   public LibraryPersistentBean(){
   }

   @PersistenceContext(unitName="EjbComponentPU")
   private EntityManager entityManager;         

   public void addBook(Book book) {
      entityManager.persist(book);
   }    

   @WebMethod(operationName="getBooks")
   public List<Book> getBooks() {
      return entityManager.createQuery("From Book").getResultList();
   }
   
    @PostConstruct
   public void postConstruct(){
      System.out.println("postConstruct:: LibraryPersistentBean session bean"
         + " created with entity Manager object: ");
   }

   @PreDestroy
   public void preDestroy(){
      System.out.println("preDestroy: LibraryPersistentBean session"
      + " bean is removed ");
   }
   

}
