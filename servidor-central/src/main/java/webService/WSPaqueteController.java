package webService;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import dataTypes.DTCompraPaquete;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
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
import webService.dataTypesWS.DTCompraWS;
import webService.dataTypesWS.DTPaqueteWS;
import webService.dataTypesWS.DTPaquetesCollectionWS;
import webService.dataTypesWS.DTSalidaTuristicaWS;
import webService.dataTypesWS.DTSalidasCollectionWS;
import webService.dataTypesWS.DTStringCollectionWS;

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

    @WebMethod(exclude = true)
    public void publish() {
        endpoint = Endpoint.publish("http://0.0.0.0:8889/ws/Paquete", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public String ping() {
    	return "pong";
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
    public boolean compraExiste(DTCompraWS compra){
        DTCompraPaquete Compra = new DTCompraPaquete(compra.getCOMPRADOR(), compra.getPAQUETE(), compra.getCANTTURISTAS(),
                compra.getVENCIMIENTO(), compra.getFECHACOMPRA(), compra.getCOSTO());
        return controlador.compraExiste(Compra);
      
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
    public byte[] obtenerFotoPaqueteActividadTuristica(String paquete){
        return controlador.obtenerFotoPaqueteActividadTuristica(paquete);
    }
    @WebMethod
    public boolean compraExiste(DTCompraPaquete compra){
        return controlador.compraExiste(compra);
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
    public void agregarCompraPaquete(DTCompraWS compra){
        DTCompraPaquete Compra = new DTCompraPaquete(compra.getCOMPRADOR(), compra.getPAQUETE(), compra.getCANTTURISTAS(),
                compra.getVENCIMIENTO(), compra.getFECHACOMPRA(), compra.getCOSTO());
        
        controlador.agregarCompraPaquete(Compra);
    }
    
}

