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

    @WebMethod(exclude = true)
    public void publish() {
        endpoint = Endpoint.publish("http://localhost:8889/ws/Actividad", this);
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
    public float obtenerCostoActividad(String nombreActividad) {
        return controlador.obtenerCostoActividad(nombreActividad);
    }
}
