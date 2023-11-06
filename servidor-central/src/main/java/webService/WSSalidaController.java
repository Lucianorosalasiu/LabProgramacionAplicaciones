/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import dataTypes.DTInscripcion;
import dataTypes.DTSalidaTuristica;
import exceptions.MyException;
import java.util.ArrayList;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import lombok.NoArgsConstructor;

/**
 *
 * @author diego
 */

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
@NoArgsConstructor
public class WSSalidaController {
    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    
    @WebMethod
    public void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException {
        controlador.altaSalidaTuristica(dtSalidaTuristica, nombreActividad);
    }
    
    @WebMethod
    public ArrayList<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad){
        return controlador.obtenerSalidasTuristicas(nombreActividad);
    }
    
    @WebMethod
    public DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida) {
        return controlador.obtenerSalidaTuristica(nombreSalida);
    }
    
    @WebMethod
    public void altaInscripcion(DTInscripcion dtInscripcion, String nombreActividad, String nombreSalida, 
            String nicknameTurista) throws MyException {
        controlador.altaInscripcion(dtInscripcion, nombreActividad, nombreSalida, nicknameTurista);
    }
}
