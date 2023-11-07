package webService.dataTypesWS;

import Enums.TipoInscripcion;

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
public class DTInscripcionWS {
    DTTuristaWS turista;
    DTSalidaTuristicaWS salidaTuristica;
    String fecha;
    int cantidadTuristas;
    float costoTotal;
    
    TipoInscripcion tipo;
        
    public DTInscripcionWS(String fecha, int cantidadTuristas) {
        this.fecha = fecha;
        this.cantidadTuristas = cantidadTuristas;
    }
}