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
 * @author diego
 */

@Getter
@Setter
@AllArgsConstructor
public class DTCategoria {
    Long Id;
    String nombre;
    
    /*constructor caso sin id*/
    public DTCategoria (String nombre){
        this.nombre = nombre;
    }
    
}
