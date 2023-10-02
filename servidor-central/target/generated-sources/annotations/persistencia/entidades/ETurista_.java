package persistencia.entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.entidades.EInscripcion;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-10-02T02:16:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ETurista.class)
public class ETurista_ extends EUsuario_ {

    public static volatile ListAttribute<ETurista, EInscripcion> EInscripciones;
    public static volatile SingularAttribute<ETurista, String> nacionality;

}