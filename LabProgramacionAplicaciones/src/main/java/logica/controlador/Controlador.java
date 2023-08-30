/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controlador;
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
            throw new MyException("ERROR! Nombre del departamento ya existente en el sistema. ");
        }
    }
    
    @Override
    public void altaDepartamento(DTDepartamento departamento){  
        dataPersistencia.persistirDepartamento(departamento);
    }
    
    @Override
    public List<DTDepartamento> obtenerDepartamentos(){
        List<EDepartamento> EDepartamentos = dataPersistencia.obtenerDepartamentos();
        List<DTDepartamento> DTDepartamentos = new LinkedList<>();        
        
        for(EDepartamento d: EDepartamentos){
            DTDepartamento dtDepartamento = new DTDepartamento(d.getNombre(),d.getDescripcion(),d.getUrl());
            DTDepartamentos.add(dtDepartamento);
            System.out.println(d.getNombre());
        }
        
        
        return DTDepartamentos;       
    }

}
