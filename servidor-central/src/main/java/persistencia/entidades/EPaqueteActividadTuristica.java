/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @Column(name = "IMAGEN")
    @Lob
    private byte [] foto;
    @Column(name = "ESTADOPAQUETE")
    private boolean ESTADOPAQUETE;/*true = confirmado || false = no confirmado */
    @OneToMany
    @JoinTable(name = "PAQUETE_ACTIVIDAD",
            joinColumns = @JoinColumn(name = "PAQUETE_ID"),
            inverseJoinColumns=@JoinColumn(name = "ACTIVIDAD_ID"))
    private List<EActividadTuristica> actividades = new ArrayList<>();
    
    
    
    public EPaqueteActividadTuristica() {
    }
    
    
    public EPaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta,byte [] foto){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.ESTADOPAQUETE = false;
        actividades = null;
        this.foto = foto;
    }
    public EPaqueteActividadTuristica(String nombre, String descripcion, int validez,float descuento,Date fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.ESTADOPAQUETE = false;
        actividades = null;
    }
}
