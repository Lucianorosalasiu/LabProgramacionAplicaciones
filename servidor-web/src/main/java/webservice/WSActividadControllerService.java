
package webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 4.0.0-M4
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "WSActividadControllerService", targetNamespace = "http://webService/", wsdlLocation = "http://localhost:8889/ws/Actividad?wsdl")
public class WSActividadControllerService
    extends Service
{

    private final static URL WSACTIVIDADCONTROLLERSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSACTIVIDADCONTROLLERSERVICE_EXCEPTION;
    private final static QName WSACTIVIDADCONTROLLERSERVICE_QNAME = new QName("http://webService/", "WSActividadControllerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8889/ws/Actividad?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSACTIVIDADCONTROLLERSERVICE_WSDL_LOCATION = url;
        WSACTIVIDADCONTROLLERSERVICE_EXCEPTION = e;
    }

    public WSActividadControllerService() {
        super(__getWsdlLocation(), WSACTIVIDADCONTROLLERSERVICE_QNAME);
    }

    public WSActividadControllerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSACTIVIDADCONTROLLERSERVICE_QNAME, features);
    }

    public WSActividadControllerService(URL wsdlLocation) {
        super(wsdlLocation, WSACTIVIDADCONTROLLERSERVICE_QNAME);
    }

    public WSActividadControllerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSACTIVIDADCONTROLLERSERVICE_QNAME, features);
    }

    public WSActividadControllerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSActividadControllerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSActividadController
     */
    @WebEndpoint(name = "WSActividadControllerPort")
    public WSActividadController getWSActividadControllerPort() {
        return super.getPort(new QName("http://webService/", "WSActividadControllerPort"), WSActividadController.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSActividadController
     */
    @WebEndpoint(name = "WSActividadControllerPort")
    public WSActividadController getWSActividadControllerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webService/", "WSActividadControllerPort"), WSActividadController.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSACTIVIDADCONTROLLERSERVICE_EXCEPTION!= null) {
            throw WSACTIVIDADCONTROLLERSERVICE_EXCEPTION;
        }
        return WSACTIVIDADCONTROLLERSERVICE_WSDL_LOCATION;
    }

}
