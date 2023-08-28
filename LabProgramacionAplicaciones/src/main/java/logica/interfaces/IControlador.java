/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.interfaces;

import DataTypes.DTDepartamento;

/**
 *
 * @author lucho
 */
public interface  IControlador {
    
    void test();
    boolean existeDepartamento(String nombre);
    void altaDepartamento(DTDepartamento departamento);
}
