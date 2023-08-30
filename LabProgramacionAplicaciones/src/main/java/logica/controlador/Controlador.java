/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controlador;
import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import logica.interfaces.IControlador;
import logica.clases.Departamento;
import logica.clases.MyException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import persistencia.DataPersistencia;
import persistencia.FDataPersistencia;
import persistencia.IDataPersistencia;
import persistencia.entidades.EDepartamento;
/**
 *
 * @author lucho
 */
public class Controlador implements IControlador{
    private static Controlador instance = null;
    private static ArrayList<Departamento> departamentos;
    private FDataPersistencia fabPersistencia = new FDataPersistencia();
    private IDataPersistencia dataPersistencia = fabPersistencia.getInterface();
    
    private Controlador(){
        departamentos = new ArrayList<Departamento>();
    }
    
    public static Controlador getInstance(){
        if(instance == null){
            instance = new Controlador();
        }
        return instance;
    }
    
    @Override
    public void test(){
        System.out.println("Hola estoy fabricando!");
    }
    
    /**
     * 
     * @param nombre nombre del departamento a verificar su unicidad
     * @throws MyException en caso de encontrar un departamento con ese nombre en el sistema
     */
    @Override
    public void existeDepartamento(String nombre)throws MyException{
        if(dataPersistencia.existeDepartamento(nombre)){
            throw new MyException("ERROR! Ya existe un departamento con ese nombre en el sistema. ");
        }
    }
    
    @Override
    public void altaDepartamento(DTDepartamento departamento){  
        dataPersistencia.altaDepartamento(departamento);
    }
    
    /**
     * @DTDepartamentos una linked list que se carga en persistencia con DTDepartamentos parseados desde un EDepartamento
     * puede deolver la lista vacia en caso de que no se haya encontrado nada en persistencia
     */
    @Override
    public List<DTDepartamento> obtenerDepartamentos(){
        List<DTDepartamento> DTDepartamentos = dataPersistencia.obtenerDepartamentos();        
        return DTDepartamentos;       
    }
    
    @Override
    public void existeActividadTuristica(String nombre)throws MyException{
        dataPersistencia.existeActividadTuristica(nombre);
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, DTDepartamento dTDepartamento){
        dataPersistencia.altaActividadTuristica(dtActividadTuristica, dTDepartamento);
    }

}
