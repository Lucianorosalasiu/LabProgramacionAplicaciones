package dataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ignfer
 * 
 * @importante Este DT "deberia" estar en la carpeta de webService.dataTypesWS 
 * pero si lo muevo de donde esta ahora no puede acceder a metodos de DTTurista ni de 
 * DTProveedor por lo que lo deje aca, saludos :D
 */
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioWrapper {
    private DTTurista dtt;
    private DTProveedor dtp;
    private byte [] photo;
    private boolean turista = false;
    private String imagePath;

    public DTUsuarioWrapper(){}
    
    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public boolean verifyPassword(String inputPassword, String hashedPassword){
       return BCrypt.checkpw(inputPassword, hashedPassword);
    }
    
    @XmlElement
    public Boolean getIsTurista(){
        return this.turista;
    }
    
    @XmlElement
    public Long getId(){
        if(turista){
            return dtt.getId();
        }else{
            return dtp.getId();
        }
    }
    
    @XmlElement
    public String getNickname(){
        if(turista){
            return dtt.getNickname();
        }else{
            return dtp.getNickname();
        }
    }
    
    @XmlElement
    public String getEmail(){
        if(turista){
            return dtt.getEmail();
        }else{
            return dtp.getEmail();
        }
    }
    
    public String getProfileImageUrl() {
        if (this.photo != null && !this.photo.equals("")) {
            // Si hay una imagen de perfil en formato blob, se convierte a Base64
            String encodedImage = Base64.getEncoder().encodeToString(photo);
            return "data:image/jpeg;base64," + encodedImage;
        } else if (this.imagePath != null && !this.imagePath.isBlank()) {
            // Si hay una ruta de imagen
            return "https://" + this.imagePath;
        } else {
            // Si no se encuentra una imagen, usa la imagen por defecto
            return "assets/img/defecto.jpg";
        }
    }
    
    @XmlElement
    public String getPassword(){
        if(turista){
            return dtt.getPassword();
        }else{
            return dtp.getPassword();
        }
    }
    
}
