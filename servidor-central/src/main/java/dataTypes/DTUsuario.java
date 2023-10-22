/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import java.util.Base64;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author alexis
 */

@Setter
@Getter
public abstract class DTUsuario {
    protected Long id;
    protected String nickname;
    protected String name;
    protected String lastName;
    protected String email;
    protected Date birthDate;
    protected String password;
    protected String imagePath;
    protected byte[] photo;

    public DTUsuario() {}

    public DTUsuario(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate, 
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
    
    public DTUsuario(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate
    ) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }
    
    public DTUsuario(Long id, String nickname, String name, String lastName, String email, Date birthDate) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }    
        
    public DTUsuario(Long id, String nickname, String name, String lastName, String email, Date birthDate, String password, String imagePath, byte [] photo) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.imagePath = imagePath;
        this.photo = photo;
    }
        
    public DTUsuario(Long id, String nickname, String email) {
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
