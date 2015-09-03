package com.tutorialspoint.test;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.helper.ConnectHelper;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class EJBClobBlobTester {

   BufferedReader brConsoleReader = null; 
   {
       ConnectHelper.initEjbClientContext();
      brConsoleReader = 
      new BufferedReader(new InputStreamReader(System.in));
   }
   
   public static void main(String[] args) {

      EJBClobBlobTester ejbTester = new EJBClobBlobTester();

      ejbTester.testBlobClob();
   }
   
   private void showGUI(){
      System.out.println("**********************");
      System.out.println("Welcome to Book Store");
      System.out.println("**********************");
      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
   }
   
   private void testBlobClob(){

      try {
         int choice = 1; 

         LibraryPersistentBeanRemote libraryBean = 
        (LibraryPersistentBeanRemote)
         ConnectHelper.lookupStatelessSessionBean("LibraryPersistentBean", LibraryPersistentBeanRemote.class.getName());

         while (choice != 2) {
            String bookName;
            String publisherName;
            String publisherAddress;
            showGUI();
            String strChoice = brConsoleReader.readLine();
            choice = Integer.parseInt(strChoice);
            if (choice == 1) {
               System.out.print("Enter book name: ");
               bookName = brConsoleReader.readLine();
               String xml = "<book><name>"+bookName+"</name></book>";
               Book book = new Book();
               book.setName(bookName);                                        
               byte[] imageBytes = {0x32, 0x32,0x32, 0x32,0x32,
               0x32,0x32, 0x32,
               0x32, 0x32,0x32, 0x32,0x32, 0x32,0x32, 0x32,
               0x32, 0x32,0x32, 0x32,0x32, 0x32,0x32, 0x32
               };
               book.setImage(imageBytes);
               book.setXml(xml);

               libraryBean.addBook(book);          
            } else if (choice == 2) {
               break;
            }
         }

         List<Book> booksList = libraryBean.getBooks();

         System.out.println("Book(s) entered so far: " + booksList.size());
         int i = 0;
         for (Book book:booksList) {
            System.out.println((i+1)+". " + book.getName());
            byte[] imageByts = book.getImage();
            if(imageByts != null){
               System.out.print("image bytes: [");
               for(int j = 0; j < imageByts.length ; j++){
                  System.out.print("0x" 
                  + String.format("%x", imageByts[j]) +" ");
               }            
               System.out.println("]");
            }        
            System.out.println(book.getXml());
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
}