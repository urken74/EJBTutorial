
package com.tutorialspoint.stateless;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tutorialspoint.stateless package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PostConstruct_QNAME = new QName("http://stateless.tutorialspoint.com/", "postConstruct");
    private final static QName _AddBookResponse_QNAME = new QName("http://stateless.tutorialspoint.com/", "addBookResponse");
    private final static QName _GetBooksResponse_QNAME = new QName("http://stateless.tutorialspoint.com/", "getBooksResponse");
    private final static QName _PreDestroy_QNAME = new QName("http://stateless.tutorialspoint.com/", "preDestroy");
    private final static QName _GetBooks_QNAME = new QName("http://stateless.tutorialspoint.com/", "getBooks");
    private final static QName _PreDestroyResponse_QNAME = new QName("http://stateless.tutorialspoint.com/", "preDestroyResponse");
    private final static QName _AddBook_QNAME = new QName("http://stateless.tutorialspoint.com/", "addBook");
    private final static QName _PostConstructResponse_QNAME = new QName("http://stateless.tutorialspoint.com/", "postConstructResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tutorialspoint.stateless
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PostConstruct }
     * 
     */
    public PostConstruct createPostConstruct() {
        return new PostConstruct();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link PreDestroy }
     * 
     */
    public PreDestroy createPreDestroy() {
        return new PreDestroy();
    }

    /**
     * Create an instance of {@link GetBooksResponse }
     * 
     */
    public GetBooksResponse createGetBooksResponse() {
        return new GetBooksResponse();
    }

    /**
     * Create an instance of {@link PostConstructResponse }
     * 
     */
    public PostConstructResponse createPostConstructResponse() {
        return new PostConstructResponse();
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link PreDestroyResponse }
     * 
     */
    public PreDestroyResponse createPreDestroyResponse() {
        return new PreDestroyResponse();
    }

    /**
     * Create an instance of {@link GetBooks }
     * 
     */
    public GetBooks createGetBooks() {
        return new GetBooks();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostConstruct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "postConstruct")
    public JAXBElement<PostConstruct> createPostConstruct(PostConstruct value) {
        return new JAXBElement<PostConstruct>(_PostConstruct_QNAME, PostConstruct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "getBooksResponse")
    public JAXBElement<GetBooksResponse> createGetBooksResponse(GetBooksResponse value) {
        return new JAXBElement<GetBooksResponse>(_GetBooksResponse_QNAME, GetBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreDestroy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "preDestroy")
    public JAXBElement<PreDestroy> createPreDestroy(PreDestroy value) {
        return new JAXBElement<PreDestroy>(_PreDestroy_QNAME, PreDestroy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "getBooks")
    public JAXBElement<GetBooks> createGetBooks(GetBooks value) {
        return new JAXBElement<GetBooks>(_GetBooks_QNAME, GetBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PreDestroyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "preDestroyResponse")
    public JAXBElement<PreDestroyResponse> createPreDestroyResponse(PreDestroyResponse value) {
        return new JAXBElement<PreDestroyResponse>(_PreDestroyResponse_QNAME, PreDestroyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostConstructResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stateless.tutorialspoint.com/", name = "postConstructResponse")
    public JAXBElement<PostConstructResponse> createPostConstructResponse(PostConstructResponse value) {
        return new JAXBElement<PostConstructResponse>(_PostConstructResponse_QNAME, PostConstructResponse.class, null, value);
    }

}
