package persistencia.entidades;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.entidades.EActividadTuristica;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-01T22:12:09", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ESalidaTuristica.class)
public class ESalidaTuristica_ extends EBase_ {

    public static volatile SingularAttribute<ESalidaTuristica, Integer> cantidadMaxTuristas;
    public static volatile SingularAttribute<ESalidaTuristica, Date> fechaAlta;
    public static volatile SingularAttribute<ESalidaTuristica, Date> fechaSalida;
    public static volatile SingularAttribute<ESalidaTuristica, String> lugar;
    public static volatile SingularAttribute<ESalidaTuristica, EActividadTuristica> eActividadTuristica;
    public static volatile SingularAttribute<ESalidaTuristica, String> nombre;

}