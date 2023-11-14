
package webservice;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
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

    private final static QName _DtUsuarioWrapper_QNAME = new QName("http://webService/", "dtUsuarioWrapper");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtUsuarioWrapper }
     * 
     */
    public DtUsuarioWrapper createDtUsuarioWrapper() {
        return new DtUsuarioWrapper();
     * Create an instance of {@link DtCompraWS }
     * 
     */
    public DtCompraWS createDtCompraWS() {
        return new DtCompraWS();
    }

    /**
     * Create an instance of {@link DtTurista }
     * 
     */
    public DtTurista createDtTurista() {
        return new DtTurista();
    }

    /**
     * Create an instance of {@link DtTurista }
     * 
     */
    public DtTurista createDtTurista() {
        return new DtTurista();
    }

    /**
     * Create an instance of {@link DtProveedor }
     * 
     */
    public DtProveedor createDtProveedor() {
        return new DtProveedor();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtUsuarioWrapper }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtUsuarioWrapper }{@code >}
     */
    @XmlElementDecl(namespace = "http://webService/", name = "dtUsuarioWrapper")
    public JAXBElement<DtUsuarioWrapper> createDtUsuarioWrapper(DtUsuarioWrapper value) {
        return new JAXBElement<DtUsuarioWrapper>(_DtUsuarioWrapper_QNAME, DtUsuarioWrapper.class, null, value);
    }

    /**
     * Create an instance of {@link DtStringCollectionWS }
     * 
     */
    public DtStringCollectionWS createDtStringCollectionWS() {
        return new DtStringCollectionWS();
    }

}
