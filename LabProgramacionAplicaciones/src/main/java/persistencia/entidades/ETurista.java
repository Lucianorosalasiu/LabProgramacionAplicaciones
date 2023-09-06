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
@Table(name = "turista")
public class ETurista extends EUsuario {
    private String nacionality;

    public ETurista() {
        super();
    }
    
    public ETurista(
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

}
