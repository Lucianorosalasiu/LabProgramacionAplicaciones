/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.clases;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author ignfer
 */
@Getter
@Setter
@Entity
public class Departamento{
    @Id
    private String nombre;
    private String descripcion;
    private String url;
    
    public Departamento(){}
    
    public Departamento(String nombre, String descripcion, String url){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }
}
