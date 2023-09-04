/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import java.util.List;
import exceptions.MyException;
import persistencia.entidades.EDepartamento;

/**
 *
 * @author all
 */
public interface IDataPersistencia {
    
    /*CU4*/
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento);
    

    /*CU5*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(Long idDepartamento);
    List<DTActividadTuristica> obtenerActividadesTuristicas();
    DTActividadTuristica obtenerActividadTuristica(Long idActividad);

    /*CU6*/
    void existeSalidaTuristica(String nombre) throws MyException;
    void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException;
    
    /*CU12*/
    void existeDepartamento(String nombre)throws MyException;
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete);
}
