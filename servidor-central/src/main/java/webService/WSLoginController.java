package webService;

import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import webService.dataTypesWS.DTProveedorWS;
import webService.dataTypesWS.DTTuristaWS;
import webService.dataTypesWS.DTUsuarioWrapper;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import lombok.NoArgsConstructor;

/**
 *
 * @author ignfer
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSLoginController {

    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    private Endpoint endpoint = null;
    private String host = null;
    private String port = null;

    @WebMethod(exclude = true)
    public void publish(String wsHost, String wsPort) {
        this.host = wsHost;
        this.port = wsPort;
        endpoint = Endpoint.publish("http://" + this.host + ":" + this.port + "/ws/Login", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://" + this.host + ":" + this.port + "/ws/Login?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }

    @WebMethod
    public String ping() {
        return "pong";
    }

    @WebMethod
    public DTUsuarioWrapper obtenerUsuarioAlternativo(String nickname) {
        DTUsuario u = controlador.obtenerUsuarioAlternativo(nickname);
        DTUsuarioWrapper dtuw = new DTUsuarioWrapper();

        if (u instanceof DTProveedor) {
            DTProveedor proveedor = (DTProveedor) u;
            
            DTProveedorWS proveedorWS = new DTProveedorWS(
                    proveedor.getId(),
                    proveedor.getEmail(),
                    proveedor.getNickname(),
                    proveedor.getPassword(),
                    proveedor.getImagePath(),
                    proveedor.getPhoto()
            );
                    
            dtuw.setPhoto(proveedorWS.getPhoto());
            dtuw.setImagePath(proveedorWS.getImagePath());
            dtuw.setDtp(proveedorWS);
            dtuw.getDtp().setImagePath(proveedorWS.getImagePath());
            dtuw.setTurista(false);
            
            /* no me pregunten porque pero si no le pongo un DTTurista vacio revienta,
             mi suposicion es que al serializarlo si no encuentra nada da error */
            DTTuristaWS turistaVacio = new DTTuristaWS();
            dtuw.setDtt(turistaVacio);
        } else if (u instanceof DTTurista) {
            DTTurista turista = (DTTurista) u;
            
            DTTuristaWS turistaWS = new DTTuristaWS(
                    turista.getId(),
                    turista.getEmail(), 
                    turista.getNickname(),
                    turista.getPassword(),
                    turista.getImagePath(),
                    turista.getPhoto()
            );
            
            /* en cambio si cuando es un turista no le pongo el DTProveedor no pasa nada */
            dtuw.setPhoto(turistaWS.getPhoto());
            dtuw.setImagePath(turistaWS.getImagePath());
            
            dtuw.setDtt(turistaWS);
            dtuw.getDtt().setImagePath(turistaWS.getImagePath());
            dtuw.setTurista(true);
        }

        return dtuw;
    }
    
    @WebMethod
    public boolean verifyPassword(String loginPassword, DTUsuarioWrapper usuario) {
        if (usuario.isTurista()) {
            return usuario.getDtt().verifyPassword(
                    loginPassword, 
                    usuario.getDtt().getPassword()
            );
        }
   
        return usuario.getDtp().verifyPassword(
                loginPassword, 
                usuario.getDtp().getPassword()
        );
    }
    
    @WebMethod
    public String getProfileImageUrl(DTUsuarioWrapper usuario) {   
        return usuario.getProfileImageUrl();
    }
}
