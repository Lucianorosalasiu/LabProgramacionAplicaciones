/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import logica.clases.Departamento;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ignfer
 */
@Getter
@Setter
@Entity
public class EActividadTuristica extends EBase {
    private String nombre;
    private String descripcion;
    private String duracion;
    private float costo;
    private String ciudad;
    private Date fechaAlta;
    @ManyToOne
    private Departamento departamento;
    
    private EActividadTuristica(){}
    
    public EActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, Departamento departamento){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.departamento = departamento;
    }
}
