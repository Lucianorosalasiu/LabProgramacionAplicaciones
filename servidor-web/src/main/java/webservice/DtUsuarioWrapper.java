
package webservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtUsuarioWrapper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtUsuarioWrapper"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dtt" type="{http://webService/}dtTurista" minOccurs="0"/&gt;
 *         &lt;element name="dtp" type="{http://webService/}dtProveedor" minOccurs="0"/&gt;
 *         &lt;element name="photo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="turista" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="imagePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="isTurista" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuarioWrapper", propOrder = {
    "dtt",
    "dtp",
    "photo",
    "turista",
    "imagePath",
    "email",
    "id",
    "isTurista",
    "nickname",
    "password"
})
public class DtUsuarioWrapper {

    protected DtTurista dtt;
    protected DtProveedor dtp;
    protected byte[] photo;
    protected boolean turista;
    protected String imagePath;
    protected String email;
    protected Long id;
    protected Boolean isTurista;
    protected String nickname;
    protected String password;

    /**
     * Gets the value of the dtt property.
     * 
     * @return
     *     possible object is
     *     {@link DtTurista }
     *     
     */
    public DtTurista getDtt() {
        return dtt;
    }

    /**
     * Sets the value of the dtt property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTurista }
     *     
     */
    public void setDtt(DtTurista value) {
        this.dtt = value;
    }

    /**
     * Gets the value of the dtp property.
     * 
     * @return
     *     possible object is
     *     {@link DtProveedor }
     *     
     */
    public DtProveedor getDtp() {
        return dtp;
    }

    /**
     * Sets the value of the dtp property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtProveedor }
     *     
     */
    public void setDtp(DtProveedor value) {
        this.dtp = value;
    }

    /**
     * Gets the value of the photo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Sets the value of the photo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPhoto(byte[] value) {
        this.photo = value;
    }

    /**
     * Gets the value of the turista property.
     * 
     */
    public boolean isTurista() {
        return turista;
    }

    /**
     * Sets the value of the turista property.
     * 
     */
    public void setTurista(boolean value) {
        this.turista = value;
    }

    /**
     * Gets the value of the imagePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the value of the imagePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagePath(String value) {
        this.imagePath = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the isTurista property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsTurista() {
        return isTurista;
    }

    /**
     * Sets the value of the isTurista property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsTurista(Boolean value) {
        this.isTurista = value;
    }

    /**
     * Gets the value of the nickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the value of the nickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
