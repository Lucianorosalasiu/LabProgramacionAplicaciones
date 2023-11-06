package webService.dataTypesWS;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import java.time.LocalDate;

/**
 *
 * @author alexis
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTSalidaTuristicaWS {
    private String nombre;
    private int cantidadMaxTuristas;
    private LocalDate fechaSalida;
    private String lugar;
    private LocalDate fechaAlta;
    private DTActividadTuristicaWS dtActividadTuristica;
    private byte[] imagen;
    
    public DTSalidaTuristicaWS(String nombre, int cantidadMaxTuristas, LocalDate fechaSalida, String lugar, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.cantidadMaxTuristas = cantidadMaxTuristas;
        this.fechaSalida = fechaSalida;
        this.lugar = lugar;
        this.fechaAlta = fechaAlta;
    }
    
    public DTSalidaTuristicaWS(String nombre, int cantidadMaxTuristas, LocalDate fechaSalida, String lugar, LocalDate fechaAlta, byte[] imagen) {
        this.nombre = nombre;
        this.cantidadMaxTuristas = cantidadMaxTuristas;
        this.fechaSalida = fechaSalida;
        this.lugar = lugar;
        this.fechaAlta = fechaAlta;
        this.imagen = imagen;
    }
}
