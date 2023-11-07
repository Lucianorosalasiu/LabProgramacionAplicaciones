
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
@WebService(name = "WSPaqueteController", targetNamespace = "http://webService/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSPaqueteController {

    byte[] obtenerFotoPaqueteActividadTuristica(String paquete);
    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSPaqueteController/pingRequest", output = "http://webService/WSPaqueteController/pingResponse")
    public String ping();

    /**
     * 
     * @return
     *     returns webservice.DtStringCollectionWS
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSPaqueteController/obtenerPaqueteNombresRequest", output = "http://webService/WSPaqueteController/obtenerPaqueteNombresResponse")
    public DtStringCollectionWS obtenerPaqueteNombres();

    /**
     * 
     * @param arg0
     * @return
     *     returns webservice.DtPaqueteActividadTuristica
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSPaqueteController/obtenerPaqueteCostoRequest", output = "http://webService/WSPaqueteController/obtenerPaqueteCostoResponse")
    public DtPaqueteActividadTuristica obtenerPaqueteCosto(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns webservice.DtPaquetesCollectionWS
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSPaqueteController/obtenerPaquetesCompradosRequest", output = "http://webService/WSPaqueteController/obtenerPaquetesCompradosResponse")
    public DtPaquetesCollectionWS obtenerPaquetesComprados(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2);

}
