/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexis
 */

@Setter
@Getter
@Entity
@Table(name = "usuario")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class EUsuario extends EBase {
    @Column(unique = true)
    protected String nickname;
    protected String name;
    protected String lastName;
    @Column(unique = true)
    protected String email;
    @Temporal(TemporalType.DATE)
    protected Date birthDate;
    protected String password;
    protected String imagePath;
    protected byte[] photo;

    public EUsuario() { }

    public EUsuario(String nickname, String name, String lastName, String email, Date birthDate) {
        super();
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public EUsuario(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate, 
            String password, 
            String imagePath,
            byte[] photo
    ) {
        super();
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.imagePath = imagePath;
        this.photo = photo;
    }  
}
