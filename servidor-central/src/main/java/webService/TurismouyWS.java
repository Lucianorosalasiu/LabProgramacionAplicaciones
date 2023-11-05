/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import dataTypes.DTSalidaTuristica;
import exceptions.MyException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
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
public class TurismouyWS {
    private Fabrica fabrica = new Fabrica();
    private IControlador controlador = fabrica.getInterface();
    
    @WebMethod
    public void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException {
        controlador.altaSalidaTuristica(dtSalidaTuristica, nombreActividad);
    }
    
    
}
