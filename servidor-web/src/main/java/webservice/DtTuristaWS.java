
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtTuristaWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtTuristaWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService/}dtUsuarioWS"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nacionality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTuristaWS", propOrder = {
    "nacionality"
})
public class DtTuristaWS
    extends DtUsuarioWS
{

    protected String nacionality;

    /**
     * Obtiene el valor de la propiedad nacionality.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionality() {
        return nacionality;
    }

    /**
     * Define el valor de la propiedad nacionality.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionality(String value) {
        this.nacionality = value;
    }

}
