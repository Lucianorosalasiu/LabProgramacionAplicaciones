/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */

@Getter
@Setter
@Table(name = "categoria")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ECategoria extends EBase {
    
    String nombre;
    
    @ManyToMany
    private List<EActividadTuristica> actividades;
    
    public ECategoria (String nombre){
        this.nombre = nombre;
        this.actividades = new LinkedList<>();
    }
    
    public void addActividad(EActividadTuristica actividad){
        actividades.add(actividad);
    }
}
