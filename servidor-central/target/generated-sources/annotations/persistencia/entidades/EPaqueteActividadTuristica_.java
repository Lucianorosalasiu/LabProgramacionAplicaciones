package persistencia.entidades;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.entidades.EActividadTuristica;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-02T02:16:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EPaqueteActividadTuristica.class)
public class EPaqueteActividadTuristica_ extends EBase_ {

    public static volatile SingularAttribute<EPaqueteActividadTuristica, String> descripcion;
    public static volatile SingularAttribute<EPaqueteActividadTuristica, Date> fechaAlta;
    public static volatile SingularAttribute<EPaqueteActividadTuristica, Float> descuento;
    public static volatile SingularAttribute<EPaqueteActividadTuristica, Integer> validez;
    public static volatile ListAttribute<EPaqueteActividadTuristica, EActividadTuristica> actividades;
    public static volatile SingularAttribute<EPaqueteActividadTuristica, String> nombre;

}