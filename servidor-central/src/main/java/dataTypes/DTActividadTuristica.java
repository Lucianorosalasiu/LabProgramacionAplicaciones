/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ignfer
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class DTActividadTuristica {
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
            float costo, String ciudad, Date fechaAlta, String categoriasString){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.categoriasString = categoriasString;
    }
    
    public DTActividadTuristica(String nombre){
        this.nombre = nombre;
    }
    
    public DTActividadTuristica(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getCostoToString(){
        Float costoString = this.costo;
        return costoString.toString();
    }
    
}
