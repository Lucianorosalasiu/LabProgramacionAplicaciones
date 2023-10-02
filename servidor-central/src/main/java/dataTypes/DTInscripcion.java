/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import java.util.Date;
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
public class DTInscripcion {
    DTTurista turista;
    DTSalidaTuristica salidaTuristica;
    Date fecha;
    int cantidadTuristas;
    float costoTotal;
       
    public DTInscripcion(){}
    
    public DTInscripcion(Date fecha, int cantidadTuristas) {
        this.fecha = fecha;
        this.cantidadTuristas = cantidadTuristas;
    }
}
