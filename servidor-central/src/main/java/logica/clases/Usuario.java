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
public abstract class Usuario {
    protected String nickname;
    protected String name;
    protected String lastName;
    protected String email;
    protected Date birthDate;
    protected String password;
    protected String imagePath;

    public Usuario(String nickname, String name, String lastName, String email, Date birthDate, String password, String imagePath) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.imagePath = imagePath;
    }

    public Usuario(String nickname, String name, String lastName, String email, Date birthDate) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }
}

