/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Getter
@Setter
@AllArgsConstructor
@Table(name = "salidaTuristica")
@Entity
public class ESalidaTuristica extends EBase {
    @Column(unique = true)
    private String nombre;
    
    private int cantidadMaxTuristas;
    private Date fechaSalida;
    private String lugar;
    private Date fechaAlta;
    
    @ManyToOne
    private EActividadTuristica eActividadTuristica;
    
    
    public ESalidaTuristica(){}
}
