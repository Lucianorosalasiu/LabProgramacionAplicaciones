package webService;

import Enums.TipoInscripcion;
import webService.dataTypesWS.DTInscripcionWS;
import webService.dataTypesWS.DTSalidaTuristicaWS;
import dataTypes.DTInscripcion;
import dataTypes.DTSalidaTuristica;
import exceptions.MyException;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

import java.util.ArrayList;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.text.ParseException;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import utils.DateConverter;
import webService.dataTypesWS.DTPDFWrapper;
import webService.dataTypesWS.DTSalidasCollectionWS;

/**
 *
 * @author diego
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSSalidaController {

    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    private Endpoint endpoint = null;
    private String host = null;
    private String port = null;
    
    @WebMethod(exclude = true)
    public void publish(String wsHost, String wsPort) {
        this.host = wsHost;
        this.port = wsPort;
        endpoint = Endpoint.publish("http://"+ this.host + ":" + this.port + "/ws/Salida", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://"+this.host+":"+this.port+"/ws/Salida?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }
    
    @WebMethod
    public String ping() {
    	return "pong";
    }

    @WebMethod
    public DTPDFWrapper obtenerPdf(String nickname, String nombreSalida){
        DTPDFWrapper pdf = new DTPDFWrapper();
        pdf.setData(controlador.obtenerPdfInscripcion(nickname, nombreSalida));
        
        return pdf;
    }

    
    @WebMethod
    public void altaSalidaTuristica(DTSalidaTuristicaWS dTSalidaTuristicaWS, String nombreActividad) throws MyException, ParseException {

        /*Se construye el DTSalidaTuristica a partir del DTSalidaTuristicaWS*/
        DTSalidaTuristica dtSalidaTuristica = new DTSalidaTuristica(
                dTSalidaTuristicaWS.getNombre(),
                dTSalidaTuristicaWS.getCantidadMaxTuristas(),
                DateConverter.stringToDate(dTSalidaTuristicaWS.getFechaSalida()),
                dTSalidaTuristicaWS.getLugar(),
                DateConverter.stringToDate(dTSalidaTuristicaWS.getFechaAlta()),
                dTSalidaTuristicaWS.getImagen()
        );

        controlador.altaSalidaTuristica(dtSalidaTuristica, nombreActividad);
    }

    @WebMethod
    public DTSalidasCollectionWS obtenerSalidasTuristicas(String nombreActividad) throws DatatypeConfigurationException {
        ArrayList<DTSalidaTuristicaWS> listDTSalidasWS = new ArrayList<>();

        /* Se obtiene la lista de DTsalidas y se parsea a DTsalidasWS*/
        ArrayList<DTSalidaTuristica> listDTSalidas = controlador.obtenerSalidasTuristicas(nombreActividad);
        for (DTSalidaTuristica st : listDTSalidas) {
            listDTSalidasWS.add(
                    new DTSalidaTuristicaWS(
                            st.getNombre(),
                            st.getCantidadMaxTuristas(),
                            DateConverter.dateToString(st.getFechaSalida()),
                            st.getLugar(),
                            DateConverter.dateToString(st.getFechaAlta())
                    )
            );
        }
            
        DTSalidasCollectionWS collection = new DTSalidasCollectionWS();
        collection.setSalidas(listDTSalidasWS);
        
        return collection;
    }

    @WebMethod
    public DTSalidaTuristicaWS obtenerSalidaTuristica(String nombreSalida) throws DatatypeConfigurationException {

        DTSalidaTuristica salida = controlador.obtenerSalidaTuristica(nombreSalida);

        return new DTSalidaTuristicaWS(
                salida.getNombre(),
                salida.getCantidadMaxTuristas(),
                DateConverter.dateToString(salida.getFechaSalida()),
                salida.getLugar(),
                DateConverter.dateToString(salida.getFechaAlta()),
                salida.getImagen()
        );
    }

    @WebMethod
    public void altaInscripcion(DTInscripcionWS dTInscripcionWS, String nombreActividad, String nombreSalida,
            String nicknameTurista) throws MyException, ParseException {

        /*Se construye el DTInscripcion a partir del DTInscripcionWS*/
        DTInscripcion dtInscripcion = new DTInscripcion(
                DateConverter.stringToDate(dTInscripcionWS.getFecha()),
                dTInscripcionWS.getCantidadTuristas()
        );

        if (dTInscripcionWS.getTipo() == TipoInscripcion.PAQUETE) {
            dtInscripcion.setCostoTotal(dTInscripcionWS.getCostoTotal());
            dtInscripcion.setTipo(TipoInscripcion.PAQUETE);
        } else if (dTInscripcionWS.getTipo() == TipoInscripcion.GENERAL) {
            dtInscripcion.setTipo(TipoInscripcion.GENERAL);
        }

        controlador.altaInscripcion(dtInscripcion, nombreActividad, nombreSalida, nicknameTurista);
    }
    
    @WebMethod
    public DTSalidasCollectionWS obtenerSalidasDeTurista(Long idTurista) {
        ArrayList<DTSalidaTuristicaWS> resultadosParseados = new ArrayList<>();

        /* Se obtiene la lista de DTsalidas y se parsea a DTsalidasWS*/
        List<DTSalidaTuristica> resultadosSinParsear = controlador.obtenerSalidasDeTurista(idTurista);

        for (DTSalidaTuristica st : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTSalidaTuristicaWS(
                            st.getNombre(),
                            st.getCantidadMaxTuristas(),
                            DateConverter.dateToString(st.getFechaSalida()),
                            st.getLugar(),
                            DateConverter.dateToString(st.getFechaAlta())
                    )
            );
        }
            
        DTSalidasCollectionWS collection = new DTSalidasCollectionWS();
        collection.setSalidas(resultadosParseados);
        
        return collection;
    }
    
    @WebMethod
    public DTSalidasCollectionWS obtenerSalidasDeProveedorCompleto(Long idProveedor) {
        ArrayList<DTSalidaTuristicaWS> resultadosParseados = new ArrayList<>();

        /* Se obtiene la lista de DTsalidas y se parsea a DTsalidasWS*/
        List<DTSalidaTuristica> resultadosSinParsear = controlador.obtenerSalidasDeProveedorCompleto(idProveedor);

        for (DTSalidaTuristica st : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTSalidaTuristicaWS(
                            st.getNombre(),
                            st.getCantidadMaxTuristas(),
                            DateConverter.dateToString(st.getFechaSalida()),
                            st.getLugar(),
                            DateConverter.dateToString(st.getFechaAlta())
                    )
            );
        }
            
        DTSalidasCollectionWS collection = new DTSalidasCollectionWS();
        collection.setSalidas(resultadosParseados);
        
        return collection;
    }
    
    @WebMethod
    public DTSalidasCollectionWS obtenerSalidasDeProveedor(Long idProveedor) {
        ArrayList<DTSalidaTuristicaWS> resultadosParseados = new ArrayList<>();

        /* Se obtiene la lista de DTsalidas y se parsea a DTsalidasWS*/
        List<DTSalidaTuristica> resultadosSinParsear = controlador.obtenerSalidasDeProveedor(idProveedor);
        
        for (DTSalidaTuristica st : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTSalidaTuristicaWS(
                            st.getNombre(),
                            st.getCantidadMaxTuristas(),
                            DateConverter.dateToString(st.getFechaSalida()),
                            st.getLugar(),
                            DateConverter.dateToString(st.getFechaAlta())
                    )
            );
        }
            
        DTSalidasCollectionWS collection = new DTSalidasCollectionWS();
        collection.setSalidas(resultadosParseados);
        
        return collection;
    }
}
