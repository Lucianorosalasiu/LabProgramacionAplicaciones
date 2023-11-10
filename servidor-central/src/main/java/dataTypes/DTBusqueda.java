/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ignfer
 */
@Getter
@Setter
@AllArgsConstructor
public class DTBusqueda {
    private Long id;
    private String nombre;
    private String fechaAlta;
    private String categorias;
    private String tipoResultado;
    private String descripcion;

    public DTBusqueda(){}
    
}
