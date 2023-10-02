/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import persistencia.IDataPersistencia;
import persistencia.DataPersistencia;


/**
 *
 * @author all
 */
public class FDataPersistencia {
    public FDataPersistencia(){};
    public IDataPersistencia getInterface(){
        return DataPersistencia.getInstance();
    }
}
