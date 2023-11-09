/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
//import persistencia.entidades.EActividadTuristica_;

/**
 *
 * @author ignfer
 */
@Getter
@Setter
public class DTActividadTuristica{
    private Long id;
    private String nombre;
    private String descripcion;
    private String duracion;
    private float costo;
    private String ciudad;
    private Date fechaAlta;
    private DTDepartamento departamento;
    private List<Long> categorias;
    private String categoriasString;
    private int cantidadVistas;
    private String url;
            
    public DTActividadTuristica(){}
    
    public DTActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, DTDepartamento departamento){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.departamento = departamento;
    }
    
    /*constructor sin el departamento*/
    public DTActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
    }
    
    public DTActividadTuristica(Long id, String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
    }
    
    public DTActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, String categoriasString){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.categoriasString = categoriasString;
    }
    
    public DTActividadTuristica(Long id,String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, String categoriasString, int cantidadVistas, String url){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.categoriasString = categoriasString;
        this.cantidadVistas = cantidadVistas;
        this.url = url;
    }
    
    public DTActividadTuristica(String nombre){
        this.nombre = nombre;
    }
    
    public DTActividadTuristica(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public DTActividadTuristica(Long id, String nombre, int cantidadVistas){
        this.id = id;
        this.nombre = nombre;
        this.cantidadVistas = cantidadVistas;
    }
    
    public String getCostoToString(){
        Float costoString = this.costo;
        return costoString.toString();
    }
    
}
