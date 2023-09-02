/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import logica.clases.ActividadTuristica;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucho
 */
@Getter
@Setter
@Entity
@Table(name = "paquetes")
public class EPaqueteActividadTuristica extends EBase{
    @Column(unique = true)
    private String nombre;
    private String descripcion;
    private int validez;
    private float descuento;
    private Date fechaAlta;
   // @OneToMany
   // private LinkedList<ActividadTuristica> actividades;

    public EPaqueteActividadTuristica() {
    }
    
    public EPaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
  //      actividades = new LinkedList<>();
    }
}
