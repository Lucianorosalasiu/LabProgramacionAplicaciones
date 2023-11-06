package webService.dataTypesWS;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import java.time.LocalDate;

/**
 *
 * @author alexis
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTTuristaWS extends DTUsuarioWS {
    private String nacionality;
    
    public DTTuristaWS(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            LocalDate birthDate, 
            String nacionality
    ) {
        super(nickname, name, lastName, email, birthDate);
        this.nacionality = nacionality;
    }
    
    public DTTuristaWS(
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            LocalDate birthDate,
            String password, 
            String imagePath,
            byte [] photo,
            String nacionality
    ) {
        super(nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.nacionality = nacionality;
    }
    
    public DTTuristaWS(
            Long id, 
            String nickname, 
            String name, 
            String lastName, 
            String email, 
            LocalDate birthDate, 
            String password,
            String imagePath,
            byte [] photo,
            String nacionality
    ) {
        super(id, nickname, name, lastName, email, birthDate, password, imagePath, photo);
        this.nacionality = nacionality;
    }

    public DTTuristaWS(Long id, String nickname, String email, String nacionality) {
        super(id, nickname, email);
        this.nacionality = nacionality;
    }
}
