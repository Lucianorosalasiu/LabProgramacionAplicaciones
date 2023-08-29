/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author all
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    public EBase() {}

    //    public EBase(Long id) {
    //        this.id = id;
    //    }
}