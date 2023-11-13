/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import java.util.ArrayList;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.util.List;
import utils.DateConverter;
import webService.dataTypesWS.DTActividadTuristicaWS;
import webService.dataTypesWS.DTActividadesCollectionWS;
import webService.dataTypesWS.DTDepartamentoWS;
import webService.dataTypesWS.DTDepartamentosCollectionWS;
import webService.dataTypesWS.DTStringCollectionWS;

/**
 *
 * @author diego
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSActividadController {

    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    private Endpoint endpoint = null;
    private String host = null;
    private String port = null;
    
    @WebMethod(exclude = true)
    public void publish(String ipHost, String port) {
        this.host = ipHost;
        this.port = port;
        endpoint = Endpoint.publish("http://"+ ipHost + ":" + port + "/ws/Actividad", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://"+this.host+":"+port+"/ws/Actividad?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }

    @WebMethod
    public String ping() {
        return "pong";
    }
    
    @WebMethod
    public DTActividadTuristica obtenerActividadTuristicaNull(Long id){
        return controlador.obtenerActividadTuristicaNull(id);
    }
    @WebMethod
    public DTActividadTuristica obtenerActividadTuristica(String actividad){
        return controlador.obtenerActividadTuristica(actividad);
    }
    @WebMethod
    public DTDepartamentosCollectionWS obtenerDepartamentos() {
        ArrayList<DTDepartamentoWS> listDTDepartamentoWS = new ArrayList<>();
        
        /* Se obtiene la lista de DTDepartamento y se parsea a DTDepartamentoWS*/
        List<DTDepartamento> listDTDepartamento = controlador.obtenerDepartamentos();
        
        for (DTDepartamento dtDepartamento : listDTDepartamento) {
            listDTDepartamentoWS.add(
                    new DTDepartamentoWS(
                            dtDepartamento.getId(),
                            dtDepartamento.getNombre(),
                            dtDepartamento.getDescripcion(),
                            dtDepartamento.getURL()
                    )
            );
        }
        
        DTDepartamentosCollectionWS collection = new DTDepartamentosCollectionWS();
        collection.setDepartamentos(listDTDepartamentoWS);
        
        return collection;
    }
    
    @WebMethod
    public DTActividadesCollectionWS obtenerActividadesTuristicasPorDepartamento(String nombreDepartamento){
        ArrayList<DTActividadTuristicaWS> listDTActividadWS = new ArrayList<>();

        /* Se obtiene la lista de DTActividad y se parsea a DTActividadWS*/
        List<DTActividadTuristica> listDTActividad = controlador.obtenerActividadesTuristicas(
                nombreDepartamento
        );

        for (DTActividadTuristica at : listDTActividad) {
            listDTActividadWS.add(
                    new DTActividadTuristicaWS(
                            at.getNombre(),
                            at.getDescripcion(),
                            at.getDuracion(),
                            at.getCosto(),
                            at.getCiudad(),
                            DateConverter.convertToLocalDate(at.getFechaAlta())
                    )
            );
        }
        
        DTActividadesCollectionWS collection = new DTActividadesCollectionWS();
        collection.setActividades(listDTActividadWS);
        
        return collection;
    }
    
    @WebMethod
    public DTActividadesCollectionWS obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor) {
        ArrayList<DTActividadTuristicaWS> listDTActividadWS = new ArrayList<>();

        /* Se obtiene la lista de DTActividad y se parsea a DTActividadWS*/
        ArrayList<DTActividadTuristica> listDTActividad = controlador.obtenerActividadesTuristicas(
                nombreDepartamento,
                idProveedor
        );

        for (DTActividadTuristica at : listDTActividad) {
            listDTActividadWS.add(
                    new DTActividadTuristicaWS(
                            at.getNombre(),
                            at.getDescripcion(),
                            at.getDuracion(),
                            at.getCosto(),
                            at.getCiudad(),
                            DateConverter.convertToLocalDate(at.getFechaAlta())
                    )
            );
        }
        
        DTActividadesCollectionWS collection = new DTActividadesCollectionWS();
        collection.setActividades(listDTActividadWS);
        
        return collection;
    }
   @WebMethod
   public DTActividadTuristica obtenerFotoActividadTuristicaID(String selectedActividad){
        return controlador.obtenerFotoActividadTuristicaID(selectedActividad);
   }
   @WebMethod
   public byte[] obtenerFotoActividadTuristica(Long id){
       return controlador.obtenerFotoActividadTuristica(id);
   }
    
    @WebMethod
    public DTActividadesCollectionWS obtenerActividadesRelacionadas(String nombrePaquete) {
        ArrayList<DTActividadTuristicaWS> listDTActividadWS = new ArrayList<>();

        /* Se obtiene la lista de DTActividad y se parsea a DTActividadWS*/
        List<DTActividadTuristica> listDTActividad = controlador.obtenerActividadesRelacionadas(nombrePaquete);

        for (DTActividadTuristica at : listDTActividad) {
            listDTActividadWS.add(
                    new DTActividadTuristicaWS(
                            at.getId(),
                            at.getNombre(),
                            at.getDescripcion(),
                            at.getDuracion(),
                            at.getCosto(),
                            at.getCiudad(),
                            DateConverter.convertToLocalDate(at.getFechaAlta())
                    )
            );
        }
        
        DTActividadesCollectionWS collection = new DTActividadesCollectionWS();
        collection.setActividades(listDTActividadWS);
        
        return collection;
    }
    
    @WebMethod
    public float obtenerCostoActividad(String nombreActividad) {
        return controlador.obtenerCostoActividad(nombreActividad);
    }
    
    
    @WebMethod
    public int obtenerCantidadFavoritos(String nombreActividad) {
        return controlador.obtenerCantidadFavoritos(nombreActividad);
    }
    
    @WebMethod
    public DTStringCollectionWS obtenerActividadesFavoritas(String nickname){
        DTStringCollectionWS dtStringCollection = new DTStringCollectionWS();

        dtStringCollection.setLista(controlador.obtenerActividadesFavoritas(nickname));
        
        return dtStringCollection;
    }
    
    @WebMethod
    public void agregarAFavoritos(Long idActividad, String nickname){
        controlador.agregarAFavoritos(idActividad, nickname);
    }
        
    @WebMethod
    public void eliminarDeFavoritos(Long idActividad, String nickname){
        controlador.eliminarDeFavoritos(idActividad, nickname);
    }
}
