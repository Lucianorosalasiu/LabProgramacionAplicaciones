/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
    
    @OneToMany
    @JoinTable(name = "PROVEEDOR_ACTIVIDAD",
            joinColumns = @JoinColumn(name ="PROVEEDOR_ID"),
            inverseJoinColumns = @JoinColumn(name ="ACTIVIDAD_ID"))
    private List<EActividadTuristica> actividades;
    
    public EProveedor() {
        super();
    }

    public EProveedor(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            Date birthDate,
            String password,
            String imagePath,
            String description, 
            String websiteURL
    ) {
        super(nickname, name, lastName, email, birthDate, password, imagePath);
        this.description = description;
        this.websiteURL = websiteURL;
    }
    
    public void addActividad(EActividadTuristica nuevaActividad){
        this.actividades.add(nuevaActividad);
    }
    
}
