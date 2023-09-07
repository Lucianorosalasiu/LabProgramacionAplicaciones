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
public class DTProveedor {
    private Long id;
    private String nickname;
    private String name;
    private String lastName;
    private String email;
    private Date birthDate;
    private String description;
    private String websiteURL;

    public DTProveedor(String nickname, String name, String lastName,String email, Date birthDate, String description, String websiteURL){
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = this.birthDate;
        this.description = description;
        this.websiteURL = websiteURL;
    }
    
    public DTProveedor(Long id, String nickname, String email, String description){
        this.id = id;
        this.nickname = nickname;      
        this.email = email; 
        this.description = description;
    }

}
