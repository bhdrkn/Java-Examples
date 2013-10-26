
package com.bahadirakin.helloworld;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bahadirakin.helloworld package. 
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

    private final static QName _SayHelloResponse_QNAME = new QName("http://helloworld.bahadirakin.com/", "sayHelloResponse");
    private final static QName _HelloRequest_QNAME = new QName("http://helloworld.bahadirakin.com/", "helloRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bahadirakin.helloworld
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloRequest }
     * 
     */
    public HelloRequest createHelloRequest() {
        return new HelloRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.bahadirakin.com/", name = "sayHelloResponse")
    public JAXBElement<String> createSayHelloResponse(String value) {
        return new JAXBElement<String>(_SayHelloResponse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.bahadirakin.com/", name = "helloRequest")
    public JAXBElement<HelloRequest> createHelloRequest(HelloRequest value) {
        return new JAXBElement<HelloRequest>(_HelloRequest_QNAME, HelloRequest.class, null, value);
    }

}
