package webService;

import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;

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
    private String host = null;
    private String port = null;
    
    @WebMethod(exclude = true)
    public void publish(String ipHost, String port) {
        this.host = ipHost;
        this.port = port;
        endpoint = Endpoint.publish("http://"+ ipHost + ":" + port + "/ws/Usuario", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://"+this.host+":"+port+"/ws/Usuario?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }
    
    @WebMethod
    public String ping() {
    	return "pong";
    }    
}
