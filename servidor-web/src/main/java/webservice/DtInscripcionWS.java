
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtInscripcionWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtInscripcionWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="turista" type="{http://webService/}dtTuristaWS" minOccurs="0"/&gt;
 *         &lt;element name="salidaTuristica" type="{http://webService/}dtSalidaTuristicaWS" minOccurs="0"/&gt;
 *         &lt;element name="fecha" type="{http://webService/}localDate" minOccurs="0"/&gt;
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
    protected LocalDate fecha;
    protected int cantidadTuristas;
    protected float costoTotal;
    @XmlSchemaType(name = "string")
    protected TipoInscripcion tipo;

    /**
     * Obtiene el valor de la propiedad turista.
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
     * Define el valor de la propiedad turista.
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
     * Obtiene el valor de la propiedad salidaTuristica.
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
     * Define el valor de la propiedad salidaTuristica.
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
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadTuristas.
     * 
     */
    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    /**
     * Define el valor de la propiedad cantidadTuristas.
     * 
     */
    public void setCantidadTuristas(int value) {
        this.cantidadTuristas = value;
    }

    /**
     * Obtiene el valor de la propiedad costoTotal.
     * 
     */
    public float getCostoTotal() {
        return costoTotal;
    }

    /**
     * Define el valor de la propiedad costoTotal.
     * 
     */
    public void setCostoTotal(float value) {
        this.costoTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
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
     * Define el valor de la propiedad tipo.
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
