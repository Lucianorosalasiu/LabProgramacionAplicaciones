
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtInscripcionWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtInscripcionWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="turista" type="{http://webService/}dtTuristaWS" minOccurs="0"/&gt;
 *         &lt;element name="salidaTuristica" type="{http://webService/}dtSalidaTuristicaWS" minOccurs="0"/&gt;
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cantidadTuristas" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="costoTotal" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="tipo" type="{http://webService/}tipoInscripcion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtInscripcionWS", propOrder = {
    "turista",
    "salidaTuristica",
    "fecha",
    "cantidadTuristas",
    "costoTotal",
    "tipo"
})
public class DtInscripcionWS {

    protected DtTuristaWS turista;
    protected DtSalidaTuristicaWS salidaTuristica;
    protected String fecha;
    protected int cantidadTuristas;
    protected float costoTotal;
    @XmlSchemaType(name = "string")
    protected TipoInscripcion tipo;

    /**
     * Gets the value of the turista property.
     * 
     * @return
     *     possible object is
     *     {@link DtTuristaWS }
     *     
     */
    public DtTuristaWS getTurista() {
        return turista;
    }

    /**
     * Sets the value of the turista property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTuristaWS }
     *     
     */
    public void setTurista(DtTuristaWS value) {
        this.turista = value;
    }

    /**
     * Gets the value of the salidaTuristica property.
     * 
     * @return
     *     possible object is
     *     {@link DtSalidaTuristicaWS }
     *     
     */
    public DtSalidaTuristicaWS getSalidaTuristica() {
        return salidaTuristica;
    }

    /**
     * Sets the value of the salidaTuristica property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtSalidaTuristicaWS }
     *     
     */
    public void setSalidaTuristica(DtSalidaTuristicaWS value) {
        this.salidaTuristica = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the cantidadTuristas property.
     * 
     */
    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    /**
     * Sets the value of the cantidadTuristas property.
     * 
     */
    public void setCantidadTuristas(int value) {
        this.cantidadTuristas = value;
    }

    /**
     * Gets the value of the costoTotal property.
     * 
     */
    public float getCostoTotal() {
        return costoTotal;
    }

    /**
     * Sets the value of the costoTotal property.
     * 
     */
    public void setCostoTotal(float value) {
        this.costoTotal = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoInscripcion }
     *     
     */
    public TipoInscripcion getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoInscripcion }
     *     
     */
    public void setTipo(TipoInscripcion value) {
        this.tipo = value;
    }

}
