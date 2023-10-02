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
    private Long id;
    private String nickname;
    private String name;
    private String lastName;
    private String email;
    private Date birthDate;

    public DTUsuario() {}

    public DTUsuario(String nickname, String name, String lastName, String email, Date birthDate) {
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }
        
    public DTUsuario(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }  
}