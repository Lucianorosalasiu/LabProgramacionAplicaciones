/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author ignfer
 */
@Getter
@Setter
public class DTDepartamento {
    private Long id;
    private String nombre;
    private String descripcion;
    private String URL;
    
    public DTDepartamento(){}
    public DTDepartamento(String nombre, String descripcion, String URL){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URL = URL;
    }
    /*constructor con ID*/
    public DTDepartamento(Long id, String nombre, String descripcion, String URL){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URL = URL;
    }
    
}
