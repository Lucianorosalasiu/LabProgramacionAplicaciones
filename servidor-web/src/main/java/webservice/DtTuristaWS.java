
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtTuristaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the nacionality property.
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
     * Sets the value of the nacionality property.
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
