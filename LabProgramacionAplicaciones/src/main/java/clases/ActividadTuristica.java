/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author ignfer
 * 
 */
@Getter
@Setter
public class ActividadTuristica {
    private String nombre;
    private String descripcion;
    private String duracion;
    private float costo;
    private String ciudad;
    private Date fechaAlta;
    private Departamento departamento;
    
    public ActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, String nombreDepartamento){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        /*encontrar el 'Departamento' dado su 'nombreDepartamento'*/
        //this.departamento = departamento;
    }
}
