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
public class DTPaqueteActividadTuristica {
    private String nombre;
    private String descripcion;
    private int validez;
    private float descuento;
    private Date fechaAlta;
    private LinkedList<ActividadTuristica> actividades ;
    
    public DTPaqueteActividadTuristica(){}
    public DTPaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        actividades = new LinkedList<ActividadTuristica>();
    }
    public String getNombre(){
        return nombre;
    }
    public String getDesc(){
        return descripcion;
    }
    public int getValidez(){
        return validez;
    }
    public float getDescuento(){
        return descuento;
    }
    public Date getFechaAlta(){
        return fechaAlta;
    }
    
}
