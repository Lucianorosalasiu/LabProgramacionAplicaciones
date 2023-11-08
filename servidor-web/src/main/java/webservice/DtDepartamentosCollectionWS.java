
package webservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtDepartamentosCollectionWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtDepartamentosCollectionWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="departamentos" type="{http://webService/}dtDepartamentoWS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDepartamentosCollectionWS", propOrder = {
    "departamentos"
})
public class DtDepartamentosCollectionWS {

    @XmlElement(nillable = true)
    protected List<DtDepartamentoWS> departamentos;

    /**
     * Gets the value of the departamentos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the departamentos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartamentos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtDepartamentoWS }
     * 
     * 
     */
    public List<DtDepartamentoWS> getDepartamentos() {
        if (departamentos == null) {
            departamentos = new ArrayList<DtDepartamentoWS>();
        }
        return this.departamentos;
    }

}
