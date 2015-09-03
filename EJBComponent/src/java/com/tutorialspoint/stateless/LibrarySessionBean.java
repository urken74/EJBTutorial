/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.stateless;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author gorerk
 */
@Stateless
@Remote(LibrarySessionBeanRemote.class)
public class LibrarySessionBean implements LibrarySessionBeanRemote {

    List<String> bookShelf;    
    
    public LibrarySessionBean(){
       bookShelf = new ArrayList<String>();
    }
    
    public void addBook(String bookName) {
        bookShelf.add(bookName);
    }    
 
    public List<String> getBooks() {
        return bookShelf;
    }
}
