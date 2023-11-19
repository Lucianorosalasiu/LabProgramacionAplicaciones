/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService.dataTypesWS;

import java.util.Date;
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
public class DTBusquedaWS {
    private Long id;
    private String nombre;
    private Date fechaAlta;
    private String fechaAltaComoString;
    private String categorias;
    private String tipoResultado;
    private String descripcion;
    private String departamento;

    public DTBusquedaWS(){}
    
    public void setCategorias(String categorias){
        this.categorias = categorias;
    }
}
