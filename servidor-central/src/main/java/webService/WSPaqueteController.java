package webService;

import dataTypes.DTCompraPaquete;
import dataTypes.DTPaqueteActividadTuristica;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

import lombok.NoArgsConstructor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import utils.DateConverter;
import webService.dataTypesWS.DTPaqueteWS;
import webService.dataTypesWS.DTPaquetesCollectionWS;
import webService.dataTypesWS.DTStringCollectionWS;
import webService.dataTypesWS.DTCompraWS;
/**
 *
 * @author diego
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSPaqueteController {
    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    private Endpoint endpoint = null;
    private String host = null;
    private String port = null;
    
    @WebMethod(exclude = true)
    public void publish(String wsHost, String wsPort) {
        this.host = wsHost;
        this.port = wsPort;
        endpoint = Endpoint.publish("http://"+ this.host + ":" + this.port + "/ws/Paquete", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
           
    @WebMethod(exclude = true)
    public String getAddress() {
        if (endpoint != null) {
            return "http://"+this.host+":"+this.port+"/ws/Paquete?wsdl";
        } else {
            return "Endpoint no publicado aún";
        }
    }
    
    @WebMethod
    public String ping() {
    	return "pong";
    }  
        
    @WebMethod
    public DTPaquetesCollectionWS obtenerPaquetesComprados(Long idTurista, String nombreSalida, int cantTuristas) {
        ArrayList<DTPaqueteWS> listDTPaqueteWS = new ArrayList<>();
        List<DTPaqueteActividadTuristica> paquetes = controlador.obtenerPaquetesComprados(idTurista, nombreSalida, cantTuristas);
        
        /* Se obtiene la lista de DTPaqueteActividadTuristica y se parsea a DTPaqueteWS*/
        for (DTPaqueteActividadTuristica paquete : paquetes) {
            listDTPaqueteWS.add(
                    new DTPaqueteWS(
                            paquete.getNombre(),
                            paquete.getDescripcion(),
                            paquete.getValidez(),
                            paquete.getDescuento(),
                            DateConverter.dateToString(paquete.getFechaAlta()),
                            paquete.getCosto(),
                            paquete.getActividades(),
                            paquete.getImagen()
                    )
            );
        }
            
        DTPaquetesCollectionWS collection = new DTPaquetesCollectionWS();
        collection.setPaquetes(listDTPaqueteWS);
        
        return collection;
    
    }
    public byte[] obtenerFotoPaqueteActividadTuristica(String paquete){
        return controlador.obtenerFotoPaqueteActividadTuristica(paquete);
    }
    
    @WebMethod
    public DTStringCollectionWS obtenerPaqueteNombres() {
        ArrayList<String> listDTPaqueteWS = new ArrayList<>();
        List<String> paquetes = controlador.obtenerPaqueteNombres();
        
        /* Se obtiene la lista de DTPaqueteActividadTuristica y se parsea a DTPaqueteWS*/
        for (String paquete : paquetes) {
            listDTPaqueteWS.add(paquete);
            System.out.println(paquete);
        }
            
        DTStringCollectionWS collection = new DTStringCollectionWS();
        collection.setLista(listDTPaqueteWS);
        
        return collection;
    
    }
    @WebMethod
    public DTPaqueteActividadTuristica obtenerPaqueteCosto(String paquete){
        return controlador.obtenerPaqueteCosto(paquete);
    }
    
    
    
     @WebMethod
    public DTPaquetesCollectionWS obtenerPaquetes(){
        ArrayList<DTPaqueteWS> listDTPaqueteWS = new ArrayList<>();
        List<DTPaqueteActividadTuristica> paquetes = controlador.obtenerPaquetes();

        /* Se obtiene la lista de DTPaqueteActividadTuristica y se parsea a DTPaqueteWS*/
        for (DTPaqueteActividadTuristica paquete : paquetes) {
            listDTPaqueteWS.add(
                    new DTPaqueteWS(
                            paquete.getNombre(),
                            paquete.getDescripcion(),
                            paquete.getValidez(),
                            paquete.getDescuento(),
                            DateConverter.dateToString(paquete.getFechaAlta()),
                            paquete.getCosto(),
                            paquete.getActividades(),
                            paquete.getImagen()
                    )
            );
        }

        DTPaquetesCollectionWS collection = new DTPaquetesCollectionWS();
        collection.setPaquetes(listDTPaqueteWS);

        return collection;

    }
    
    @WebMethod
    public DTPaquetesCollectionWS obtenerPaquetesRelacionados(Long idActividad){
        ArrayList<DTPaqueteWS> listDTPaqueteWS = new ArrayList<>();
        List<DTPaqueteActividadTuristica> paquetes = controlador.obtenerPaquetesRelacionadosCompletos(idActividad);
        
        for (DTPaqueteActividadTuristica paquete : paquetes) {
            listDTPaqueteWS.add(
                    new DTPaqueteWS(
                            paquete.getNombre(),
                            paquete.getDescripcion(),
                            paquete.getValidez(),
                            paquete.getDescuento(),
                            DateConverter.dateToString(paquete.getFechaAlta()),
                            paquete.getCosto(),
                            paquete.getActividades(),
                            paquete.getImagen()
                    )
            );
        }

        DTPaquetesCollectionWS collection = new DTPaquetesCollectionWS();
        collection.setPaquetes(listDTPaqueteWS);

        return collection;
    }
    
    @WebMethod
    public boolean compraExiste(DTCompraWS compra){
        DTCompraPaquete Compra = new DTCompraPaquete(compra.getCOMPRADOR(), compra.getPAQUETE(), compra.getCANTTURISTAS(),
                compra.getVENCIMIENTO(), compra.getFECHACOMPRA(), compra.getCOSTO());
        return controlador.compraExiste(Compra);

    }
    @WebMethod
    public void agregarCompraPaquete(DTCompraWS compra){
        DTCompraPaquete Compra = new DTCompraPaquete(compra.getCOMPRADOR(), compra.getPAQUETE(), compra.getCANTTURISTAS(),
                compra.getVENCIMIENTO(), compra.getFECHACOMPRA(), compra.getCOSTO());

        controlador.agregarCompraPaquete(Compra);
    }
}

