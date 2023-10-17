/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import dataTypes.DTUsuario;
import java.util.Date;
import java.util.Date;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import logica.clases.ActividadTuristica;
import logica.clases.PaqueteActividadTuristica;
import logica.clases.Usuario;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author lucho
 */
@Getter
@Setter
@Entity
@Table(name = "COMPRAS_PAQUETE")
public class ECompraPaquete extends EBase{
    
    private ETurista COMPRADOR;
    private EPaqueteActividadTuristica PAQUETE;
    private int CANTTURISTAS;
    private Date VENCIMIENTO;
    private Date FECHACOMPRA;
    private float COSTO ;
    
    public ECompraPaquete(){}
    public ECompraPaquete(ETurista comprador, EPaqueteActividadTuristica paquete, int cantTuristas,Date vencimiento,Date fechaCompra,float costo){
        this.COMPRADOR = comprador;
        this.PAQUETE = paquete;
        this.CANTTURISTAS = cantTuristas;
        this.VENCIMIENTO = vencimiento;
        this.FECHACOMPRA = fechaCompra;
        this.COSTO = costo;
        
    } 
}
