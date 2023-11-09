/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

/**
 *
 * @author ignfer
 */
@lombok.Getter
@lombok.Setter
public class DTTop {
    private int pos;
    private Long id;
    private String nombre;
    private String tipoTop;
    private int cantidadVistas;
    
    public DTTop(){}
    
    public DTTop(int pos, Long id, String nombre, String tipoTop, int cantidadVistas){
        this.pos = pos;
        this.id = id;
        this.nombre = nombre;
        this.tipoTop = tipoTop;
        this.cantidadVistas = cantidadVistas;
    }
}
