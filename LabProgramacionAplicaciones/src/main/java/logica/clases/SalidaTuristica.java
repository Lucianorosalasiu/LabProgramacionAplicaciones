/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.clases;

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
public class SalidaTuristica {
    private String nombre;
    private int cantidadMaxTuristas;
    private Date fechaSalida;
    private String lugar;
    private Date fechaAlta;
}
