package persistencia.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    protected List<ESeguidores> seguidores;

    private void inicializarListas() {
        this.seguidores = new ArrayList<>();
    }

    public EUsuario() {
        super();
        this.inicializarListas();
    }

    public EUsuario(String nickname, String name, String lastName, String email, Date birthDate) {
        super();
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.inicializarListas();
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
        this.inicializarListas();
    }

    public void agregarSeguidor(long seguidorId) {
        ESeguidores relacion = new ESeguidores();
        relacion.setSeguidorId(seguidorId);
        relacion.setSeguidoId(this.getId());

        this.seguidores.add(relacion);
    }

    public void removerSeguidor(long seguidorId) {
        this.seguidores.removeIf(relacion -> relacion.getSeguidorId() == seguidorId);
    }
}
