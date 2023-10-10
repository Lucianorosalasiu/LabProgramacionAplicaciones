/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.clases;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexis
 */

@Setter
@Getter
public class Turista extends Usuario{
    private String nacionality;

    public Turista(
            String nickname,
            String name,
            String lastName, 
            String email, 
            Date birthDate,
            String password,
            String imagePath,
            String nacionality
    ) {
        super(nickname, name, lastName, email, birthDate, password, imagePath);
        this.nacionality = nacionality; 
    }
}
