package webService.dataTypesWS;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

/**
 *
 * @author alexis
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTDepartamentoWS {
    private Long id;
    private String nombre;
    private String descripcion;
    private String URL;
    
    public DTDepartamentoWS(String nombre, String descripcion, String URL){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URL = URL;
    }
}
