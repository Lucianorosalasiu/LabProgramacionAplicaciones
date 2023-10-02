/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
/**
 *
 * @author all
 */

@Getter
@Setter
@Entity
@Table(name = "departamento")
public class EDepartamento extends EBase {
    @Column(unique = true)
    private String nombre;

    private String descripcion;

    private String url; 

   // @OneToMany(mappedBy = "department")
   // private List<TouristicActivity> touristicActivities;

    //Constructor
    public EDepartamento() {
    }

    public EDepartamento(String nombre, String desc, String url) {
        super();
        this.nombre = nombre;
        this.descripcion = desc;
        this.url = url;
        // this.InitLists();
    }

}