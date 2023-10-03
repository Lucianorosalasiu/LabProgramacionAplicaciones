/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import Enums.EstadoActividad;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import logica.clases.Departamento;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ignfer
 */
@Getter
@Setter
@Table(name = "actividadTuristica")
@Entity
public class EActividadTuristica extends EBase {
    @Column(unique = true)
    private String nombre;
    private String descripcion;
    private String duracion;
    private float costo;
    private String ciudad;
    private Date fechaAlta;
   
    @ManyToOne
    private EDepartamento eDepartamento;
    
    @ManyToMany(mappedBy = "actividades")
    private List<EPaqueteActividadTuristica> paquetes;
    
    @OneToMany(mappedBy = "eActividadTuristica")
    private List<ESalidaTuristica> ESalidasTuristicas;
    
    @Column(name = "ESTADOACTIVIDAD")
    @Enumerated(EnumType.STRING)
    private EstadoActividad estadoActividad;
    
    private EActividadTuristica(){}
    
    public EActividadTuristica(String nombre, String descripcion, String duracion,
            float costo, String ciudad, Date fechaAlta, EDepartamento eDepartamento){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.eDepartamento = eDepartamento;
        this.estadoActividad = EstadoActividad.AGREGADA;
    }
}
