/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import dataTypes.DTUsuarioWrapper;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            dtuw.setPhoto(u.getPhoto());
            dtuw.setImagePath(u.getImagePath());
            
            dtuw.setDtp((DTProveedor) u);
            
            dtuw.getDtp().setImagePath(u.getImagePath());
            
            dtuw.setTurista(false);
            /* no me pregunten porque pero si no le pongo un DTTurista vacio revienta,
             mi suposicion es que al serializarlo si no encuentra nada da error */
            DTTurista turistaVacio = new DTTurista();
            dtuw.setDtt(turistaVacio);
        } else if (u instanceof DTTurista) {
            /* en cambio si cuando es un turista no le pongo el DTProveedor no pasa nada */
            dtuw.setPhoto(u.getPhoto());
            dtuw.setImagePath(u.getImagePath());
            
            dtuw.setDtt((DTTurista) u);
            dtuw.getDtt().setImagePath(u.getImagePath());
            dtuw.setTurista(true);
        }

        return dtuw;
    }
}
