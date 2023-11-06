/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import dataTypes.DTActividadTuristica;
import java.util.ArrayList;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import lombok.NoArgsConstructor;

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
    
    @WebMethod
    public ArrayList<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor) {
        return controlador.obtenerActividadesTuristicas(nombreDepartamento, idProveedor);
    }
}
