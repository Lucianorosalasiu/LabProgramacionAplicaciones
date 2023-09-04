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
public class DTTurista {
    private String nickname;
    private String name;
    private String lastName;
    private String email;
    private Date birthDate;
    private String nacionality;
}
