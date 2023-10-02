package persistencia.entidades;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.entidades.ESalidaTuristica;
import persistencia.entidades.ETurista;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-02T02:16:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EInscripcion.class)
public class EInscripcion_ extends EBase_ {

    public static volatile SingularAttribute<EInscripcion, Date> fecha;
    public static volatile SingularAttribute<EInscripcion, ETurista> eTurista;
    public static volatile SingularAttribute<EInscripcion, ESalidaTuristica> eSalidaTuristica;
    public static volatile SingularAttribute<EInscripcion, Integer> cantidadTuristas;
    public static volatile SingularAttribute<EInscripcion, Float> costoTotal;

}