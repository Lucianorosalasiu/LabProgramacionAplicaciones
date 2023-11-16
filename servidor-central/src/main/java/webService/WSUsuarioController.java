package webService;

import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import exceptions.MyException;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
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
    public void publish(String wsHost, String wsPort) {
        this.host = wsHost;
        this.port = wsPort;
        endpoint = Endpoint.publish("http://" + this.host + ":" + this.port + "/ws/Usuario", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://" + this.host + ":" + this.port + "/ws/Usuario?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }

    @WebMethod
    public String ping() {
        return "pong";
    }

    // controlador.altaProveedor(nuevoProveedor);
    // controlador.altaTurista(nuevoTurista);
    // List<DTUsuario> usrs = controlador.obtenerUsuarios();
    // DTUsuario usr = controlador.obtenerUsuarioAlternativo(emailUsuario);
    // controlador.actualizarUsuario(nuevoProveedor);   
    // controlador.actualizarUsuario(nuevoTurista);
    // En WSSalida:
    // List<DTSalidaTuristica> salidaList = controlador.obtenerSalidasDeTurista(turista.getId());
    // List<DTSalidaTuristica> salidaList = controlador.obtenerSalidasDeProveedorCompleto(proveedor.getId());
    // List<DTSalidaTuristica> salidaList = controlador.obtenerSalidasDeProveedor(proveedor.getId());
    // En WSActividad:
    // List<DTActividadTuristica> actividadList = controlador.obtenerActividadesDeProveedorCompleto(proveedor.getId());
    // List<DTActividadTuristica> actividadList = controlador.obtenerActividadesDeProveedor(proveedor.getId());
    // ArrayList<DTActividadTuristica> actividadesFinalizables = controlador.obtenerActividadesFinalizables(sessionID);
    
    @WebMethod
    public void seguirUsuario(Long idSeguidor, Long idSeguido) throws MyException {
        controlador.seguirUsuario(idSeguidor, idSeguido);
    }

    @WebMethod
    public void dejarDeSeguirUsuario(Long idSeguidor, Long idSeguido) throws MyException  {
        controlador.dejarDeSeguirUsuario(idSeguidor, idSeguido);
    }

    @WebMethod
    public DTTurista obtenerTurista(long idTurista) {
        return controlador.obtenerTurista(idTurista);
    }
}
