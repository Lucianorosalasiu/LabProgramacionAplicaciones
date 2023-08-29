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

/**
 * se define una query personalizada que se puede reutilizar, en este caso esta es llamada desde DataPersistencia.existeDepartamento
 * 
 * :atributo significa que ese atributo va a ser remplazado por un parametro posteriormente cuando se llame a la consulta
 * en este caso cuando llamo a esta consulta utilizo .setParameter("nombreDepartamento", nombre) siendo nombre el parametro y la cadena de texto
 * el nombre del parametro a remplazar
 * 
 * la reutilizacion de 'd' es una convencion en JPQL
 */
@NamedQuery(name = "EDepartamento.existeNombreDepartamento", query = "select d from EDepartamento d where d.nombre = :nombreDepartamento")
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