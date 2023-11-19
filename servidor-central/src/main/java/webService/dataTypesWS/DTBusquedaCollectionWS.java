/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService.dataTypesWS;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ignfer
 */

@Getter
@Setter
@NoArgsConstructor
public class DTBusquedaCollectionWS {
    ArrayList<DTBusquedaWS> resultadosBusqueda = new ArrayList<>();
}
