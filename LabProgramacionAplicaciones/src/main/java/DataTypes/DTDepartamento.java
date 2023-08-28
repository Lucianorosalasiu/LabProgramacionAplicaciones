/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author ignfer
 */
@Getter
@Setter
public class DTDepartamento {
    private String nombre;
    private String descripcion;
    private String URL;
    
    public DTDepartamento(){}
    public DTDepartamento(String nombre, String descripcion, String URL){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URL = URL;
    }
}
