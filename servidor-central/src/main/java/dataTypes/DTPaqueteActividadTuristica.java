/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import java.util.Date;
import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucho
 */
@Getter
@Setter
public class DTPaqueteActividadTuristica {
    private String nombre;
    private String descripcion;
    private int validez;
    private float descuento;
    private Date fechaAlta;
    private float costo;
    private LinkedList<DTActividadTuristica> actividades ;
    private byte[] imagen;
    public DTPaqueteActividadTuristica(){}
    public DTPaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        costo = 0;
        actividades = new LinkedList<DTActividadTuristica>();
        imagen = null;
    }
    public DTPaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta,float costo){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.costo = costo;
        actividades = new LinkedList<DTActividadTuristica>();
        imagen = null;
    }
    
    public DTPaqueteActividadTuristica(String nombre){
        this.nombre = nombre;
    }
  
    
}
