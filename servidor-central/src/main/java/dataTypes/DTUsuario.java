/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

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
        
    public DTUsuario(Long id, String nickname, String name, String lastName, String email, Date birthDate, String password, String imagePath) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.imagePath = imagePath;
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
}