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
public class Proveedor extends Usuario{
    private String description;
    private String websiteURL;

    public Proveedor(
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
}
