/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controlador;
import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import logica.interfaces.IControlador;
import logica.clases.Departamento;
import exceptions.MyException;
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
    public void existeActividadTuristica(String nombre)throws MyException{
        dataPersistencia.existeActividadTuristica(nombre);
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento){
        dataPersistencia.altaActividadTuristica(dtActividadTuristica, idDepartamento);
    }
    
    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento){
        Long idDepartamento = 0L;
        
        List<DTDepartamento> departamentos = dataPersistencia.obtenerDepartamentos();
        
        for(DTDepartamento de : departamentos){
            if(de.getNombre().equals(nombreDepartamento)){
                idDepartamento = de.getId();
            }
        }
        
        return dataPersistencia.obtenerActividadesTuristicas(idDepartamento);
    }
    
    @Override
    public DTActividadTuristica obtenerActividadTuristica(String nombreActividad){
        Long idActividad = 0L;
        List <DTActividadTuristica> dtActividadesTuristicas = dataPersistencia.obtenerActividadesTuristicas();
        
        for(DTActividadTuristica a : dtActividadesTuristicas){
            if(a.getNombre().equals(nombreActividad)){
                idActividad = a.getId();
            }
        }
        return dataPersistencia.obtenerActividadTuristica(idActividad);
    }
   
    @Override
    public void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete){
        dataPersistencia.altaPaqueteActividadTuristica(dtPaquete);
    }
    
    /**
     * 
     * @param nombre nombre del departamento a verificar su unicidad
     * @throws MyException en caso de encontrar un departamento con ese nombre en el sistema
     */
    @Override
    public void existeDepartamento(String nombre)throws MyException{
        dataPersistencia.existeDepartamento(nombre);
    }
    
    @Override
    public void altaDepartamento(DTDepartamento departamento){  
        dataPersistencia.altaDepartamento(departamento);
    }
    
    /**
     * @DTDepartamentos una linked list que se carga en persistencia con DTDepartamentos parseados desde un EDepartamento
     * puede devolver la lista vacia en caso de que no se haya encontrado nada en persistencia
     */
    @Override
    public List<DTDepartamento> obtenerDepartamentos(){
        List<DTDepartamento> DTDepartamentos = dataPersistencia.obtenerDepartamentos();        
        return DTDepartamentos;       
    }
    
    @Override
    public void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException {
        dataPersistencia.existeSalidaTuristica(dtSalidaTuristica.getNombre());
        dataPersistencia.altaSalidaTuristica(dtSalidaTuristica, nombreActividad);
    }
    
    @Override
    public List<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad) {
        return dataPersistencia.obtenerSalidasTuristicas(nombreActividad);
    }
    
    @Override
    public DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida) {
        return dataPersistencia.obtenerSalidaTuristica(nombreSalida);
    }
    
    @Override
    public void altaInscripcion(DTInscripcion dtInscripcion, String nombreSalida, String nicknameTurista) throws MyException {
        
    }
}
