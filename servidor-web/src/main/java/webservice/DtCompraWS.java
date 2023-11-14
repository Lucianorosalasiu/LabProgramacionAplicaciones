
package webservice;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtCompraWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtCompraWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CANTTURISTAS" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="COMPRADOR" type="{http://webService/}dtTurista" minOccurs="0"/&gt;
 *         &lt;element name="COSTO" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="FECHACOMPRA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="PAQUETE" type="{http://webService/}dtPaqueteActividadTuristica" minOccurs="0"/&gt;
 *         &lt;element name="VENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompraWS", propOrder = {
    "cantturistas",
    "comprador",
    "costo",
    "fechacompra",
    "paquete",
    "vencimiento"
})
public class DtCompraWS {

    @XmlElement(name = "CANTTURISTAS")
    protected int cantturistas;
    @XmlElement(name = "COMPRADOR")
    protected DtTurista comprador;
    @XmlElement(name = "COSTO")
    protected float costo;
    @XmlElement(name = "FECHACOMPRA")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechacompra;
    @XmlElement(name = "PAQUETE")
    protected DtPaqueteActividadTuristica paquete;
    @XmlElement(name = "VENCIMIENTO")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar vencimiento;

    /**
     * Gets the value of the cantturistas property.
     * 
     */
    public int getCANTTURISTAS() {
        return cantturistas;
    }

    /**
     * Sets the value of the cantturistas property.
     * 
     */
    public void setCANTTURISTAS(int value) {
        this.cantturistas = value;
    }

    /**
     * Gets the value of the comprador property.
     * 
     * @return
     *     possible object is
     *     {@link DtTurista }
     *     
     */
    public DtTurista getCOMPRADOR() {
        return comprador;
    }

    /**
     * Sets the value of the comprador property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTurista }
     *     
     */
    public void setCOMPRADOR(DtTurista value) {
        this.comprador = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCOSTO() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCOSTO(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the fechacompra property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFECHACOMPRA() {
        return fechacompra;
    }

    /**
     * Sets the value of the fechacompra property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFECHACOMPRA(XMLGregorianCalendar value) {
        this.fechacompra = value;
    }

    /**
     * Gets the value of the paquete property.
     * 
     * @return
     *     possible object is
     *     {@link DtPaqueteActividadTuristica }
     *     
     */
    public DtPaqueteActividadTuristica getPAQUETE() {
        return paquete;
    }

    /**
     * Sets the value of the paquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaqueteActividadTuristica }
     *     
     */
    public void setPAQUETE(DtPaqueteActividadTuristica value) {
        this.paquete = value;
    }

    /**
     * Gets the value of the vencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVENCIMIENTO() {
        return vencimiento;
    }

    /**
     * Sets the value of the vencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVENCIMIENTO(XMLGregorianCalendar value) {
        this.vencimiento = value;
    }

}
