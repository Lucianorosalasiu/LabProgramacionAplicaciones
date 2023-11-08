package webService.dataTypesWS;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author alexis
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCategoriaWS {

    Long Id;
    String nombre;

    /*constructor caso sin id*/
    public DTCategoriaWS(String nombre) {
        this.nombre = nombre;
    }
}
