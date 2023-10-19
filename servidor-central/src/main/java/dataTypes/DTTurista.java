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
public class DTTurista extends DTUsuario{
    private String nacionality;

    public DTTurista() {
    }
  
    public DTTurista(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate, 
            String nacionality
    ) {
        super(nickname, name, lastName, email, birthDate);
        this.nacionality = nacionality;
    }
    
    public DTTurista(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate,
            String password, 
            String imagePath,
            byte [] photo,
            String nacionality
    ) {
        super(nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.nacionality = nacionality;
    }
    
    public DTTurista(
            Long id, 
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate, 
            String password,
            String imagePath,
            byte [] photo,
            String nacionality
    ) {
        super(id, nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.nacionality = nacionality;
    }

    public DTTurista(Long id, String nickname, String email, String nacionality) {
        super(id, nickname, email);
        this.nacionality = nacionality;
    }
}
