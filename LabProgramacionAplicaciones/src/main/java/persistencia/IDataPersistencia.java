/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import java.util.List;
import logica.clases.MyException;
import persistencia.entidades.EDepartamento;

/**
 *
 * @author all
 */
public interface IDataPersistencia {
    
    boolean existeDepartamento(String nombre);
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, DTDepartamento dTDepartamento);
    
}
