package webService.dataTypesWS;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import java.util.Base64;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author alexis
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DTUsuarioWS {
    protected Long id;
    protected String nickname;
    protected String name;
    protected String lastName;
    protected String email;
    protected String birthDate;
    protected String password;
    protected String imagePath;
    protected byte[] photo;

    public DTUsuarioWS(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            String birthDate, 
            String password, 
            String imagePath,
            byte [] photo
    ) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.imagePath = imagePath;
        this.photo = photo;
    }
    
    public DTUsuarioWS(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            String birthDate
    ) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }
    
    public DTUsuarioWS(Long id, String nickname, String name, String lastName, String email, String birthDate) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    } 
    
    public DTUsuarioWS(Long id, String email, String nickname,  String password, String imagePath, byte[] photo) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.imagePath = imagePath;
        this.photo = photo;
    } 
        
    public DTUsuarioWS(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }  
    
    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public boolean verifyPassword(String inputPassword, String hashedPassword){
       return BCrypt.checkpw(inputPassword, hashedPassword);
    }
    
    // Método válido para visualizar las imagenes desde la web, no desde Swing
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
}
