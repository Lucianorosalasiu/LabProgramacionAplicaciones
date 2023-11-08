
package webservice;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoInscripcion.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="tipoInscripcion"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PAQUETE"/&gt;
 *     &lt;enumeration value="GENERAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoInscripcion")
@XmlEnum
public enum TipoInscripcion {

    PAQUETE,
    GENERAL;

    public String value() {
        return name();
    }

    public static TipoInscripcion fromValue(String v) {
        return valueOf(v);
    }

}
