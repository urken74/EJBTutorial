/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbwebserviceclient;

/**
 *
 * @author gorerk
 */
public class EJBWebServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (com.tutorialspoint.stateless.Book book : getBooks()) {
            System.out.println(book.getName());
        }
    }

    private static java.util.List<com.tutorialspoint.stateless.Book> getBooks() {
        com.tutorialspoint.stateless.LibraryService service = new com.tutorialspoint.stateless.LibraryService();
        com.tutorialspoint.stateless.LibraryPersistentBean port = service.getLibraryPersistentBeanPort();
        return port.getBooks();
    }

}
