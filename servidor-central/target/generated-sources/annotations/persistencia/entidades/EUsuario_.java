package persistencia.entidades;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-01T22:12:09", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EUsuario.class)
public abstract class EUsuario_ extends EBase_ {

    public static volatile SingularAttribute<EUsuario, String> lastName;
    public static volatile SingularAttribute<EUsuario, String> nickname;
    public static volatile SingularAttribute<EUsuario, String> name;
    public static volatile SingularAttribute<EUsuario, Date> birthDate;
    public static volatile SingularAttribute<EUsuario, String> email;

}