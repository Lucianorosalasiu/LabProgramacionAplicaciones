/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataTypes;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexis
 */
@Getter
@Setter
@AllArgsConstructor
public class DTProveedor extends DTUsuario {
    private String description;
    private String websiteURL;

    public DTProveedor() {
        super();
    }

    public DTProveedor(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate, 
            String description,
            String websiteURL
    ) {
        super(nickname, name, lastName, email, birthDate);
        this.description = description;
        this.websiteURL = websiteURL;
    }
    
    public DTProveedor(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate,
            String password, 
            String imagePath,
            byte [] photo,
            String description, 
            String websiteURL
    ) {
        super(nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.description = description;
        this.websiteURL = websiteURL;
    }

    public DTProveedor(
            Long id, 
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate, 
            String password, 
            String imagePath,
            byte [] photo,
            String description, 
            String websiteURL
    ) {
        super(id, nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.description = description;
        this.websiteURL = websiteURL;
    }

    public DTProveedor(Long id, String nickname, String email, String description) {
        super(id, nickname, email);
        this.description = description;
    }
}
