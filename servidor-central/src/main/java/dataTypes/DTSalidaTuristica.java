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
public class DTSalidaTuristica {
    private String nombre;
    private int cantidadMaxTuristas;
    private Date fechaSalida;
    private String lugar;
    private Date fechaAlta;
    private DTActividadTuristica dtActividadTuristica;
    private byte[] imagen;
    
    public DTSalidaTuristica(){};
    
    public DTSalidaTuristica(String nombre, int cantidadMaxTuristas, Date fechaSalida, String lugar, Date fechaAlta) {
        this.nombre = nombre;
        this.cantidadMaxTuristas = cantidadMaxTuristas;
        this.fechaSalida = fechaSalida;
        this.lugar = lugar;
        this.fechaAlta = fechaAlta;
    }
    
    public DTSalidaTuristica(String nombre, int cantidadMaxTuristas, Date fechaSalida, String lugar, Date fechaAlta, byte[] imagen) {
        this.nombre = nombre;
        this.cantidadMaxTuristas = cantidadMaxTuristas;
        this.fechaSalida = fechaSalida;
        this.lugar = lugar;
        this.fechaAlta = fechaAlta;
        this.imagen = imagen;
    }
}
