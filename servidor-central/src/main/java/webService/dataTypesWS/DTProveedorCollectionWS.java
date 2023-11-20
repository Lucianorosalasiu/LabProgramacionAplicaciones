
package webService.dataTypesWS;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author alexis
 */

@Getter
@Setter
@NoArgsConstructor
public class DTProveedorCollectionWS {
    ArrayList<DTProveedorWS> proveedores = new ArrayList<>();
}
