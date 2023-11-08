
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtSalidaTuristicaWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad nombre.
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
     * Define el valor de la propiedad nombre.
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
     * Obtiene el valor de la propiedad cantidadMaxTuristas.
     * 
     */
    public int getCantidadMaxTuristas() {
        return cantidadMaxTuristas;
    }

    /**
     * Define el valor de la propiedad cantidadMaxTuristas.
     * 
     */
    public void setCantidadMaxTuristas(int value) {
        this.cantidadMaxTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSalida.
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
     * Define el valor de la propiedad fechaSalida.
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
     * Obtiene el valor de la propiedad lugar.
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
     * Define el valor de la propiedad lugar.
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
     * Obtiene el valor de la propiedad fechaAlta.
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
     * Define el valor de la propiedad fechaAlta.
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
     * Obtiene el valor de la propiedad dtActividadTuristica.
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
     * Define el valor de la propiedad dtActividadTuristica.
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
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagen(byte[] value) {
        this.imagen = value;
    }

}
