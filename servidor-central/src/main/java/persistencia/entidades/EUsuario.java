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
//@MappedSuperclass
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
            name = "SEGUIMIENTO_USUARIO",
            joinColumns = @JoinColumn(name = "seguidor_id"),
            inverseJoinColumns = @JoinColumn(name = "seguido_id")
    )
    protected List<EUsuario> seguidores = new ArrayList<>();

    @ManyToMany(mappedBy = "seguidores")
    protected List<EUsuario> seguidos = new ArrayList<>();

    private void inicializarListas() {
        this.seguidores = new ArrayList<>();
        this.seguidos = new ArrayList<>();
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

    public void agregarSeguidor(EUsuario seguidor) {
        seguidores.add(seguidor);
        seguidor.getSeguidos().add(this);
    }

    public void removerSeguidor(EUsuario seguidor) {
        seguidores.remove(seguidor);
        seguidor.getSeguidos().remove(this);
    }

    public void agregarSeguido(EUsuario seguido) {
        seguidos.add(seguido);
        seguido.getSeguidores().add(this);
    }

    public void removerSeguido(EUsuario seguido) {
        seguidos.remove(seguido);
        seguido.getSeguidores().remove(this);
    }
}
