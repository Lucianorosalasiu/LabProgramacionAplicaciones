/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import java.util.Date;
import java.util.LinkedList;
import logica.clases.ActividadTuristica;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucho
 */
@Getter
@Setter

public class DTCompraPaquete {
    private DTUsuario COMPRADOR;
    private DTPaqueteActividadTuristica PAQUETE;
    private int CANTTURISTAS;
    private Date VENCIMIENTO;
    private Date FECHACOMPRA;
    private float COSTO ;
    
    public DTCompraPaquete(){}
    public DTCompraPaquete(DTUsuario comprador, DTPaqueteActividadTuristica paquete, int cantTuristas,Date vencimiento,Date fechaCompra,float costo){
        this.COMPRADOR = comprador;
        this.PAQUETE = paquete;
        this.CANTTURISTAS = cantTuristas;
        this.VENCIMIENTO = vencimiento;
        this.FECHACOMPRA = fechaCompra;
        this.COSTO = costo;
        
    } 
}
