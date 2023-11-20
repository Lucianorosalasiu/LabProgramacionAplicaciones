package persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexis
 */
@Setter
@Getter
@Entity
@Table(name = "SEGUIDORES")
public class ESeguidores extends EBase {
    @Column(name = "seguidor_id")
    private long seguidorId;

    @Column(name = "seguido_id")
    private long seguidoId;

    public ESeguidores() {
    }

    public ESeguidores(long seguidorId, long seguidoId) {
        this.seguidorId = seguidorId;
        this.seguidoId = seguidoId;
    }
}
