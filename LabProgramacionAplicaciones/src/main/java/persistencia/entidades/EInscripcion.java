/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
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
@Entity
@AllArgsConstructor
@Table(name = "inscripcion")
public class EInscripcion extends EBase {
    //@ManyToOne
    //ETurista 
    
    @ManyToOne
    ESalidaTuristica eSalidaTuristica;
    
    Date fecha;
    int cantidadTuristas;
    float costoTotal;
    
    public EInscripcion(){};
}
