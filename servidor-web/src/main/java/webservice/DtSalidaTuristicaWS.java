
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtSalidaTuristicaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtSalidaTuristicaWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cantidadMaxTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fechaSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dtActividadTuristica" type="{http://webService/}dtActividadTuristicaWS" minOccurs="0"/&gt;
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSalidaTuristicaWS", propOrder = {
    "nombre",
    "cantidadMaxTuristas",
    "fechaSalida",
    "lugar",
    "fechaAlta",
    "dtActividadTuristica",
    "imagen"
})
public class DtSalidaTuristicaWS {

    protected String nombre;
    protected int cantidadMaxTuristas;
    protected String fechaSalida;
    protected String lugar;
    protected String fechaAlta;
    protected DtActividadTuristicaWS dtActividadTuristica;
    protected byte[] imagen;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the cantidadMaxTuristas property.
     * 
     */
    public int getCantidadMaxTuristas() {
        return cantidadMaxTuristas;
    }

    /**
     * Sets the value of the cantidadMaxTuristas property.
     * 
     */
    public void setCantidadMaxTuristas(int value) {
        this.cantidadMaxTuristas = value;
    }

    /**
     * Gets the value of the fechaSalida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSalida() {
        return fechaSalida;
    }

    /**
     * Sets the value of the fechaSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSalida(String value) {
        this.fechaSalida = value;
    }

    /**
     * Gets the value of the lugar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Sets the value of the lugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugar(String value) {
        this.lugar = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the dtActividadTuristica property.
     * 
     * @return
     *     possible object is
     *     {@link DtActividadTuristicaWS }
     *     
     */
    public DtActividadTuristicaWS getDtActividadTuristica() {
        return dtActividadTuristica;
    }

    /**
     * Sets the value of the dtActividadTuristica property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtActividadTuristicaWS }
     *     
     */
    public void setDtActividadTuristica(DtActividadTuristicaWS value) {
        this.dtActividadTuristica = value;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagen(byte[] value) {
        this.imagen = value;
    }

}
