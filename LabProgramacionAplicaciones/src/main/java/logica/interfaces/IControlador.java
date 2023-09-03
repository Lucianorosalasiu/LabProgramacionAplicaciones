/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.interfaces;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTInscripcion;
import java.util.List;
import logica.clases.MyException;

/**
 *
 * @author lucho
 */
public interface  IControlador {
    
    void test();
    
    /*CU4*/
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica,Long idDepartamento);
    
    /*CU5*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento);
    DTActividadTuristica obtenerActividadTuristica(String nombreActividad);

    /*CU6*/
    void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException;
    
    /*CU7*/
    List<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad);
    DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida);
    
    /*CU8*/
    void altaInscripcion(DTInscripcion dtInscripcion, String nombreSalida, String nicknameTurista) throws MyException;
    
    /*CU12*/
    void existeDepartamento(String nombre)throws MyException;
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete);
}
