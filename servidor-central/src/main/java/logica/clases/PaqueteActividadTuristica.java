/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.clases;
import java.util.Date;
import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;
import logica.clases.ActividadTuristica;
/**
 *
 * @author lucho
 */
@Getter
@Setter
public class PaqueteActividadTuristica {
    private String nombre;
    private String descripcion;
    private int validez;
    private float descuento;
    private Date fechaAlta;
    private LinkedList<ActividadTuristica> actividades ;
    
    public PaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        actividades = new LinkedList<ActividadTuristica>();
    }
}
