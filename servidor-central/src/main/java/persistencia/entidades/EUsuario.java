package persistencia.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
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

    @ManyToMany
    @JoinTable(
            name = "SEGUIDORES",
            joinColumns = @JoinColumn(name = "seguidor_id"),
            inverseJoinColumns = @JoinColumn(name = "seguido_id")
    )
    protected List<EUsuario> seguidores;

    private void inicializarLista() {
        this.seguidores = new ArrayList<>();
    }

    public EUsuario() {
        super();
        this.inicializarLista();
    }

    public EUsuario(String nickname, String name, String lastName, String email, Date birthDate) {
        super();
        this.nickname = nickname;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.inicializarLista();
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
        this.inicializarLista();
    }

    public void agregarSeguidor(EUsuario seguidor) {
        seguidores.add(seguidor);
    }

    public void removerSeguidor(EUsuario seguidor) {
        seguidores.remove(seguidor);
    }
}
