/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService.dataTypesWS;

import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lucho
 */
@Getter
@Setter

public class DTCompraWS {
    private DTTurista COMPRADOR;
    private DTPaqueteActividadTuristica PAQUETE;
    private int CANTTURISTAS;
    private Date VENCIMIENTO;
    private Date FECHACOMPRA;
    private float COSTO ;

    public DTCompraWS(){}
    public DTCompraWS(DTTurista comprador, DTPaqueteActividadTuristica paquete, int cantTuristas,Date vencimiento,Date fechaCompra,float costo){
        this.COMPRADOR = comprador;
        this.PAQUETE = paquete;
        this.CANTTURISTAS = cantTuristas;
        this.VENCIMIENTO = vencimiento;
        this.FECHACOMPRA = fechaCompra;
        this.COSTO = costo;

    } 
}
