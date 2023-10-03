/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.clases;

import Enums.EstadoActividad;
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
    private EstadoActividad estadoActividad;
    
    public ActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, Departamento departamento){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        /*el usuario va a seleccionar el nombre de un departamento y en el controlador
        se va a buscar el objeto departamento al que le corresponda ese nombre
        y se le va a pasar a este constructor*/
        this.departamento = departamento;
    }
}
