package webService.dataTypesWS;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;


/**
 *
 * @author alexis
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTProveedorWS extends DTUsuarioWS {
    private String description;
    private String websiteURL;
    
    public DTProveedorWS(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            String birthDate, 
            String description,
            String websiteURL
    ) {
        super(nickname, name, lastName, email, birthDate);
        this.description = description;
        this.websiteURL = websiteURL;
    }
    
    public DTProveedorWS(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            String birthDate,
            String password, 
            String imagePath,
            byte [] photo,
            String description, 
            String websiteURL
    ) {
        super(nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.description = description;
        this.websiteURL = websiteURL;
    }

    public DTProveedorWS(
            Long id, 
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            String birthDate, 
            String password, 
            String imagePath,
            byte [] photo,
            String description, 
            String websiteURL
    ) {
        super(id, nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.description = description;
        this.websiteURL = websiteURL;
    }

    public DTProveedorWS(Long id, String nickname, String email, String description) {
        super(id, nickname, email);
        this.description = description;
    }
    
    public DTProveedorWS(
            Long id,
            String email,
            String nickname,
            String password,
            String imagePath,
            byte[] photo
    ) {
        super(id, nickname, email, password, imagePath, photo);
    }  
}
