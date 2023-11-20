
package webService.dataTypesWS;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Alexis
 */

@Setter
@Getter
@NoArgsConstructor
public class DTUsuarioCollectionWS {
    ArrayList<DTUsuarioWrapper> usuariosW = new ArrayList();
    ArrayList<DTUsuarioWS> usuariosWS = new ArrayList();
}
