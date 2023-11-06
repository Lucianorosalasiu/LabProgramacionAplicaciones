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
 * @author diego
 */

@Getter
@Setter
@NoArgsConstructor
public class DTDepartamentosCollectionWS {
    ArrayList<DTDepartamentoWS> departamentos = new ArrayList<>();
}
