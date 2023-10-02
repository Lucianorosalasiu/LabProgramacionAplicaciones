package persistencia.entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.entidades.EActividadTuristica;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-02T02:16:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EProveedor.class)
public class EProveedor_ extends EUsuario_ {

    public static volatile SingularAttribute<EProveedor, String> websiteURL;
    public static volatile SingularAttribute<EProveedor, String> description;
    public static volatile ListAttribute<EProveedor, EActividadTuristica> actividades;

}