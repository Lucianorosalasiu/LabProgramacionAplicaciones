
package webservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 4.0.0-M4
 * Generated source version: 3.0
 * 
 */
@WebService(name = "WSSalidaController", targetNamespace = "http://webService/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSSalidaController {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSSalidaController/pingRequest", output = "http://webService/WSSalidaController/pingResponse")
    public String ping();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns webservice.DtpdfWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSSalidaController/obtenerPdfRequest", output = "http://webService/WSSalidaController/obtenerPdfResponse")
    public DtpdfWrapper obtenerPdf(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws MyException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @Action(input = "http://webService/WSSalidaController/altaSalidaTuristicaRequest", output = "http://webService/WSSalidaController/altaSalidaTuristicaResponse", fault = {
        @FaultAction(className = MyException_Exception.class, value = "http://webService/WSSalidaController/altaSalidaTuristica/Fault/MyException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://webService/WSSalidaController/altaSalidaTuristica/Fault/ParseException")
    })
    public void altaSalidaTuristica(
        @WebParam(name = "arg0", partName = "arg0")
        DtSalidaTuristicaWS arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws MyException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns webservice.DtSalidasCollectionWS
     * @throws DatatypeConfigurationException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSSalidaController/obtenerSalidasTuristicasRequest", output = "http://webService/WSSalidaController/obtenerSalidasTuristicasResponse", fault = {
        @FaultAction(className = DatatypeConfigurationException_Exception.class, value = "http://webService/WSSalidaController/obtenerSalidasTuristicas/Fault/DatatypeConfigurationException")
    })
    public DtSalidasCollectionWS obtenerSalidasTuristicas(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws DatatypeConfigurationException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns webservice.DtSalidaTuristicaWS
     * @throws DatatypeConfigurationException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webService/WSSalidaController/obtenerSalidaTuristicaRequest", output = "http://webService/WSSalidaController/obtenerSalidaTuristicaResponse", fault = {
        @FaultAction(className = DatatypeConfigurationException_Exception.class, value = "http://webService/WSSalidaController/obtenerSalidaTuristica/Fault/DatatypeConfigurationException")
    })
    public DtSalidaTuristicaWS obtenerSalidaTuristica(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws DatatypeConfigurationException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws MyException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @Action(input = "http://webService/WSSalidaController/altaInscripcionRequest", output = "http://webService/WSSalidaController/altaInscripcionResponse", fault = {
        @FaultAction(className = MyException_Exception.class, value = "http://webService/WSSalidaController/altaInscripcion/Fault/MyException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://webService/WSSalidaController/altaInscripcion/Fault/ParseException")
    })
    public void altaInscripcion(
        @WebParam(name = "arg0", partName = "arg0")
        DtInscripcionWS arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3)
        throws MyException_Exception, ParseException_Exception
    ;

}
