/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dataTypes.DTDepartamento;
import java.util.List;
import persistencia.entidades.EDepartamento;

/**
 *
 * @author all
 */
public interface IDataPersistencia {
    void persistirDepartamento(DTDepartamento departamento);
    boolean existeDepartamento(String nombre);
    List<EDepartamento> obtenerDepartamentos();
}
