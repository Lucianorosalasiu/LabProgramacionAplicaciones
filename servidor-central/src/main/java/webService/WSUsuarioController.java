package webService;

import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import exceptions.MyException;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import webService.dataTypesWS.DTProveedorWS;
import webService.dataTypesWS.DTTuristaWS;
import webService.dataTypesWS.DTUsuarioCollectionWS;
import webService.dataTypesWS.DTUsuarioWrapper;
import webService.dataTypesWS.DTLongCollectionWS;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static utils.DateConverter.userDateToStringFront;
import static utils.DateConverter.userDateToStringForm;
import static utils.DateConverter.userStringToDate;

/**
 *
 * @author diego
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSUsuarioController {

    private final Fabrica fabrica = new Fabrica();
    private final IControlador controlador = fabrica.getInterface();
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

    @WebMethod
    public DTUsuarioWrapper obtenerUsuarioAlternativo(String nickname) {
        DTUsuario u = controlador.obtenerUsuarioAlternativo(nickname);
        DTUsuarioWrapper dtuw = new DTUsuarioWrapper();

        if (u instanceof DTProveedor) {
            DTProveedor proveedor = (DTProveedor) u;

            DTProveedorWS proveedorWS = new DTProveedorWS(
                    proveedor.getId(),
                    proveedor.getNickname(),
                    proveedor.getName(),
                    proveedor.getLastName(),
                    proveedor.getEmail(),
                    userDateToStringForm(proveedor.getBirthDate()),
                    proveedor.getPassword(),
                    proveedor.getImagePath(),
                    proveedor.getPhoto(),
                    proveedor.getDescription(),
                    proveedor.getWebsiteURL()
            );

            dtuw.setPhoto(proveedorWS.getPhoto());
            dtuw.setImagePath(proveedorWS.getImagePath());
            dtuw.setDtp(proveedorWS);
            dtuw.getDtp().setImagePath(proveedorWS.getImagePath());
            dtuw.setTurista(false);

            /* Nacho dice que no le pregunten porque pero si no le pone un 
            DTTurista vacio revienta, su suposicion es que al serializarlo, 
            si no encuentra nada da error 
            
            PD: Entonces no le pregunto
            */
            DTTuristaWS turistaVacio = new DTTuristaWS();
            dtuw.setDtt(turistaVacio);
        } else if (u instanceof DTTurista) {
            DTTurista turista = (DTTurista) u;

            DTTuristaWS turistaWS = new DTTuristaWS(
                    turista.getId(),
                    turista.getNickname(),
                    turista.getName(),
                    turista.getLastName(),
                    turista.getEmail(),
                    userDateToStringForm(turista.getBirthDate()),
                    turista.getPassword(),
                    turista.getImagePath(),
                    turista.getPhoto(),
                    turista.getNacionality()
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
    public DTUsuarioCollectionWS obtenerUsuarios() {
        ArrayList<DTUsuarioWrapper> listUsuarioWrapper = new ArrayList<>();
        List<DTUsuario> users = controlador.obtenerUsuarios();

        for (DTUsuario usr : users) {
            DTUsuarioWrapper dtuw = new DTUsuarioWrapper();

            if (usr instanceof DTProveedor) {
                DTProveedor proveedor = (DTProveedor) usr;

                DTProveedorWS proveedorWS = new DTProveedorWS(
                        proveedor.getId(),
                        proveedor.getNickname(),
                        proveedor.getName(),
                        proveedor.getLastName(),
                        proveedor.getEmail(),
                        userDateToStringFront(proveedor.getBirthDate()),
                        proveedor.getPassword(),
                        proveedor.getImagePath(),
                        proveedor.getPhoto(),
                        proveedor.getDescription(),
                        proveedor.getWebsiteURL()
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
            } else if (usr instanceof DTTurista) {
                DTTurista turista = (DTTurista) usr;

                DTTuristaWS turistaWS = new DTTuristaWS(
                        turista.getId(),
                        turista.getNickname(),
                        turista.getName(),
                        turista.getLastName(),
                        turista.getEmail(),
                        userDateToStringFront(turista.getBirthDate()),
                        turista.getPassword(),
                        turista.getImagePath(),
                        turista.getPhoto(),
                        turista.getNacionality()
                );

                /* en cambio si cuando es un turista no le pongo el DTProveedor no pasa nada */
                dtuw.setPhoto(turistaWS.getPhoto());
                dtuw.setImagePath(turistaWS.getImagePath());

                dtuw.setDtt(turistaWS);
                dtuw.getDtt().setImagePath(turistaWS.getImagePath());
                dtuw.setTurista(true);
            }

            listUsuarioWrapper.add(dtuw);
        }

        DTUsuarioCollectionWS collection = new DTUsuarioCollectionWS();
        collection.setUsuariosW(listUsuarioWrapper);

        return collection;
    }

    @WebMethod
    public void altaProveedor(DTProveedorWS proveedor) throws MyException, ParseException {
        DTProveedor nuevoProveedor = new DTProveedor(
                proveedor.getNickname(),
                proveedor.getName(),
                proveedor.getLastName(),
                proveedor.getEmail(),
                userStringToDate(proveedor.getBirthDate()),
                proveedor.getPassword(),
                proveedor.getImagePath(),
                proveedor.getPhoto(),
                proveedor.getDescription(),
                proveedor.getWebsiteURL()
        );
        controlador.altaProveedor(nuevoProveedor);
    }

    @WebMethod
    public void altaTurista(DTTuristaWS turista) throws MyException, ParseException {
        DTTurista nuevoTurista = new DTTurista(
                turista.getNickname(),
                turista.getName(),
                turista.getLastName(),
                turista.getEmail(),
                userStringToDate(turista.getBirthDate()),
                turista.getPassword(),
                turista.getImagePath(),
                turista.getPhoto(),
                turista.getNacionality()
        );
        controlador.altaTurista(nuevoTurista);
    }

    @WebMethod
    public void modificarProveedor(DTProveedorWS proveedor) throws MyException, ParseException { 
        DTProveedor proveedorAActualizar = new DTProveedor(
                proveedor.getNickname(),
                proveedor.getName(),
                proveedor.getLastName(),
                proveedor.getEmail(),
                userStringToDate(proveedor.getBirthDate()),
                proveedor.getPassword(),
                proveedor.getImagePath(),
                proveedor.getPhoto(),
                proveedor.getDescription(),
                proveedor.getWebsiteURL()
        );
        controlador.actualizarUsuario(proveedorAActualizar);   
    }
    
    @WebMethod
    public void modificarTurista(DTTuristaWS turista) throws MyException, ParseException { 
        DTTurista turistaAActualizar = new DTTurista(
                turista.getNickname(),
                turista.getName(),
                turista.getLastName(),
                turista.getEmail(),
                userStringToDate(turista.getBirthDate()),
                turista.getPassword(),
                turista.getImagePath(),
                turista.getPhoto(),
                turista.getNacionality()
        );
        controlador.actualizarUsuario(turistaAActualizar);   
    }
        
    @WebMethod
    public void seguirUsuario(Long idSeguidor, Long idSeguido) throws MyException {
        controlador.seguirUsuario(idSeguidor, idSeguido);
    }

    @WebMethod
    public void dejarDeSeguirUsuario(Long idSeguidor, Long idSeguido) throws MyException {
        controlador.dejarDeSeguirUsuario(idSeguidor, idSeguido);
    }

    @WebMethod
    public DTLongCollectionWS obtenerSeguidos(Long idSeguidor) {
        DTLongCollectionWS dtLongCollection = new DTLongCollectionWS();

        dtLongCollection.setLista(controlador.obtenerSeguidos(idSeguidor));

        return dtLongCollection;
    }

    @WebMethod
    public DTLongCollectionWS obtenerSeguidores(Long idSeguido) {
        DTLongCollectionWS dtLongCollection = new DTLongCollectionWS();

        dtLongCollection.setLista(controlador.obtenerSeguidores(idSeguido));

        return dtLongCollection;
    }

    @WebMethod
    public DTTurista obtenerTurista(long idTurista) {
        return controlador.obtenerTurista(idTurista);
    }
}
