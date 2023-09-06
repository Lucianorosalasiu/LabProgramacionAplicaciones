/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "proveedor")
public class EProveedor extends EUsuario {
    private String description;
    private String websiteURL;

    public EProveedor() {
        super();
    }

    public EProveedor(
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
