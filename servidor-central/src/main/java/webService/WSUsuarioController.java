package webService;

import dataTypes.DTTurista;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import webService.dataTypesWS.DTTuristaWS;

/**
 *
 * @author diego
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSUsuarioController {
    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    private Endpoint endpoint = null;

    @WebMethod(exclude = true)
    public void publish() {
        endpoint = Endpoint.publish("http://0.0.0.0:8889/ws/Usuario", this);
    }
    @WebMethod(exclude = true)
    public DTTurista obtenerTurista(Long idTurista){
        return controlador.obtenerTurista(idTurista);
    }
    
    @WebMethod
    public DTTurista obtenerTurista(long idTurista){
        return controlador.obtenerTurista(idTurista);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public String ping() {
    	return "pong";
    }    
}
