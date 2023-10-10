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

@Setter
@Getter
@AllArgsConstructor
public abstract class DTUsuario {
    protected Long id;
    protected String nickname;
    protected String name;
    protected String lastName;
    protected String email;
    protected Date birthDate;
    protected String password;
    protected String imagePath;

    public DTUsuario() {}

    public DTUsuario(String nickname, String name, String lastName, String email, Date birthDate) {
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
}