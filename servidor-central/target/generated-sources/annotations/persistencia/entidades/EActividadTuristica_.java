package persistencia.entidades;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.entidades.EDepartamento;
import persistencia.entidades.EPaqueteActividadTuristica;
import persistencia.entidades.ESalidaTuristica;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-02T02:16:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EActividadTuristica.class)
public class EActividadTuristica_ extends EBase_ {

    public static volatile SingularAttribute<EActividadTuristica, String> descripcion;
    public static volatile SingularAttribute<EActividadTuristica, EDepartamento> eDepartamento;
    public static volatile SingularAttribute<EActividadTuristica, Date> fechaAlta;
    public static volatile SingularAttribute<EActividadTuristica, Float> costo;
    public static volatile SingularAttribute<EActividadTuristica, String> ciudad;
    public static volatile ListAttribute<EActividadTuristica, EPaqueteActividadTuristica> paquetes;
    public static volatile SingularAttribute<EActividadTuristica, String> duracion;
    public static volatile ListAttribute<EActividadTuristica, ESalidaTuristica> ESalidasTuristicas;
    public static volatile SingularAttribute<EActividadTuristica, String> nombre;

}