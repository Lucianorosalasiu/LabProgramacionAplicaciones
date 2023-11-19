/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTBusqueda;
import dataTypes.DTCategoria;
import dataTypes.DTDepartamento;
import exceptions.MyException;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import java.util.ArrayList;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import utils.DateConverter;
import webService.dataTypesWS.DTActividadTuristicaWS;
import webService.dataTypesWS.DTActividadesCollectionWS;
import webService.dataTypesWS.DTDepartamentoWS;
import webService.dataTypesWS.DTDepartamentosCollectionWS;
import webService.dataTypesWS.DTStringCollectionWS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import webService.dataTypesWS.DTBusquedaCollectionWS;
import webService.dataTypesWS.DTBusquedaWS;

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
    public void publish(String wsHost, String wsPort) {
        this.host = wsHost;
        this.port = wsPort;
        endpoint = Endpoint.publish("http://"+ this.host + ":" + this.port + "/ws/Actividad", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://"+this.host+":"+this.port+"/ws/Actividad?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }

    @WebMethod
    public String ping() {
        return "pong";
    }
    
    @WebMethod
    public void altaActividadTuristica (
        DTActividadTuristicaWS nuevaActividadTuristica,
        Long idDepartamento,
        Long idProveedor, 
        String categoriasIds,
        byte[] newImage,
        String url,
        String strFecha) throws ParseException {
            String[] categoriasIdsSplit = categoriasIds.split(",");
            List<Long> categoriasIdsLong = new LinkedList<>();

            for (String categoriaIdsIndividual : categoriasIdsSplit) {
                try {
                    categoriasIdsLong.add(Long.parseLong(categoriaIdsIndividual));
                } catch (Exception e) {
                    
                }
            }
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date fecha = new Date();
            try {
                fecha = dateFormat.parse(strFecha);
            } catch (Exception e) {
                throw new ParseException("Algo salio mal construyendo la fecha", 0);
            } 
            
            
            DTActividadTuristica nuevoDTActividadTuristica = new DTActividadTuristica(
                    nuevaActividadTuristica.getNombre(),
                    nuevaActividadTuristica.getDescripcion(),
                    nuevaActividadTuristica.getDuracion(),
                    nuevaActividadTuristica.getCosto(),
                    nuevaActividadTuristica.getCiudad(),
                    fecha);         
            
            controlador.altaActividadTuristica(nuevoDTActividadTuristica, idDepartamento, idProveedor, categoriasIdsLong, newImage, url);
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
    public String obtenerCategorias(){
        String PlainStringCategorias = "";
        
        List<DTCategoria> categorias = controlador.obtenerCategorias();
        for(DTCategoria c : categorias){
            PlainStringCategorias += c.getNombre() + ",";
        }
        
        return PlainStringCategorias;
    }
    
    @WebMethod
    public String obtenerIdsCategorias(){
        String PlainStringIdsCategorias = "";
        
        List<DTCategoria> categorias = controlador.obtenerCategorias();
        for(DTCategoria c : categorias){
            PlainStringIdsCategorias += String.valueOf(c.getId()) + ",";
        }
        
        return PlainStringIdsCategorias;
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
    public void existeActividadTuristica(String nombreActividad)throws MyException{
        controlador.existeActividadTuristica(nombreActividad);
    }
    
    @WebMethod
    public DTActividadesCollectionWS obtenerActividadesPorCategoria(String nombreCategoria){
        ArrayList<DTActividadTuristicaWS> listDTActividadWS = new ArrayList<>();
        
        List<DTActividadTuristica> listDTActividad = controlador.obtenerActividadesTuristicasPorCategoria(
                nombreCategoria);
        
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
    
    @WebMethod
    public DTActividadesCollectionWS obtenerActividadesFinalizables(Long idProveedor){
        ArrayList<DTActividadTuristicaWS> resultadosParseados = new ArrayList<>();
        
        List<DTActividadTuristica> resultadosSinParsear = controlador.obtenerActividadesFinalizables(idProveedor);
        
        for (DTActividadTuristica at : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTActividadTuristicaWS(
                            at.getId(),
                            at.getNombre(),
                            at.getDescripcion(),
                            at.getCiudad(),
                            at.getCosto()
                    )
            );
        }
        
        DTActividadesCollectionWS collection = new DTActividadesCollectionWS();
        collection.setActividades(resultadosParseados);
        
        return collection;
    }
    
    @WebMethod
    public void finalizarActividad(Long idActividad){
        controlador.validarActividad(idActividad, EstadoActividad.FINALIZADA);
    }
    
    @WebMethod
    public DTBusquedaCollectionWS obtenerBusqueda(String peticionBusqueda) {
        ArrayList<DTBusquedaWS> resultadosParseados = new ArrayList<>();
        
        List<DTBusqueda> resultadosSinParsear = controlador.obtenerBusqueda(peticionBusqueda);

        for (DTBusqueda dtb : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTBusquedaWS(
                        dtb.getId(),
                        dtb.getNombre(),
                        dtb.getFechaAlta(),
                        dtb.getFechaAltaComoString(),
                        dtb.getCategorias(),
                        dtb.getTipoResultado(),
                        dtb.getDescripcion(),
                        dtb.getDepartamento()
                    )
            );
        }
        
        DTBusquedaCollectionWS collection = new DTBusquedaCollectionWS();
        collection.setResultadosBusqueda(resultadosParseados);
        
        return collection;
    }
    
    @WebMethod
    public DTBusquedaCollectionWS ordenarBusquedaDepartamento(String peticionBusqueda,String nombreDepartamento){
        ArrayList<DTBusquedaWS> resultadosParseados = new ArrayList<>();
        
        List<DTBusqueda> resultadosSinParsear = controlador.ordenarBusquedaDepartamento(peticionBusqueda, nombreDepartamento);
        
         for (DTBusqueda dtb : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTBusquedaWS(
                        dtb.getId(),
                        dtb.getNombre(),
                        dtb.getFechaAlta(),
                        dtb.getFechaAltaComoString(),
                        dtb.getCategorias(),
                        dtb.getTipoResultado(),
                        dtb.getDescripcion(),
                        dtb.getDepartamento()
                    )
            );
        }
        
        DTBusquedaCollectionWS collection = new DTBusquedaCollectionWS();
        collection.setResultadosBusqueda(resultadosParseados);
        
        return collection;
    }
    
    @WebMethod
    public DTBusquedaCollectionWS ordenarBusquedaCategoria(String peticionBusqueda,String nombreCategoria) {
    ArrayList<DTBusquedaWS> resultadosParseados = new ArrayList<>();
        
        List<DTBusqueda> resultadosSinParsear = controlador.ordenarBusquedaCategoria(peticionBusqueda, nombreCategoria);
        
         for (DTBusqueda dtb : resultadosSinParsear) {
            resultadosParseados.add(
                    new DTBusquedaWS(
                        dtb.getId(),
                        dtb.getNombre(),
                        dtb.getFechaAlta(),
                        dtb.getFechaAltaComoString(),
                        dtb.getCategorias(),
                        dtb.getTipoResultado(),
                        dtb.getDescripcion(),
                        dtb.getDepartamento()
                    )
            );
        }
        
        DTBusquedaCollectionWS collection = new DTBusquedaCollectionWS();
        collection.setResultadosBusqueda(resultadosParseados);
        
        return collection;
    }
    
    @WebMethod
    public DTBusquedaCollectionWS ordenarBusquedaFecha(String peticionBusqueda) {
    ArrayList<DTBusquedaWS> resultadosParseados = new ArrayList<>();
        
        List<DTBusqueda> resultadosSinParsear = controlador.obtenerBusqueda(peticionBusqueda);
        Collections.sort(resultadosSinParsear, Comparator.comparing(DTBusqueda::getFechaAlta).reversed());
        
        for (DTBusqueda dtb : resultadosSinParsear) {
            resultadosParseados.add(new DTBusquedaWS(
                dtb.getId(),
                dtb.getNombre(),
                dtb.getFechaAlta(),
                dtb.getFechaAltaComoString(),
                dtb.getCategorias(),
                dtb.getTipoResultado(),
                dtb.getDescripcion(),
                dtb.getDepartamento()
            ));
        }
        
        DTBusquedaCollectionWS collection = new DTBusquedaCollectionWS();
        collection.setResultadosBusqueda(resultadosParseados);
        
        return collection;
    }
}
