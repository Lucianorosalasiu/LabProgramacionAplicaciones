
package webservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 4.0.0-M4
 * Generated source version: 3.0
 * 
 */
@WebService(name = "WSUsuarioController", targetNamespace = "http://webService/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSUsuarioController {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSUsuarioController/pingRequest", output = "http://webService/WSUsuarioController/pingResponse")
    public String ping();

    /**
     * 
     * @param arg0
     * @return
     *     returns webservice.DtTurista
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSUsuarioController/obtenerTuristaRequest", output = "http://webService/WSUsuarioController/obtenerTuristaResponse")
    public DtTurista obtenerTurista(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0);

}
