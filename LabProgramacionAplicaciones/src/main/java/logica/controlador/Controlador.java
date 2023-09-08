/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controlador;
import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTProveedor;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import logica.interfaces.IControlador;
import logica.clases.Departamento;
import exceptions.MyException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import logica.clases.Proveedor;
import logica.clases.Turista;
import persistencia.FDataPersistencia;
import persistencia.IDataPersistencia;

/**
 *
 * @author lucho
 */
public class Controlador implements IControlador{
    private static Controlador instance = null;
    private FDataPersistencia fabPersistencia = new FDataPersistencia();
    private IDataPersistencia dataPersistencia = fabPersistencia.getInterface();
    
    private Controlador(){}
    
    public static Controlador getInstance(){
        if(instance == null){
            instance = new Controlador();
        }
        return instance;
    }
       
    /* CU USUARIOS */
    @Override
    public void altaProveedor(DTProveedor nuevoProveedor) throws MyException {
        dataPersistencia.existeUsuario(
                nuevoProveedor.getEmail(), 
                nuevoProveedor.getNickname()
        );
        
        // Si no existe el proveedor, se crea el objeto correspondiente
        Proveedor objProveedor = new Proveedor(
                nuevoProveedor.getNickname(), 
                nuevoProveedor.getName(), 
                nuevoProveedor.getLastName(), 
                nuevoProveedor.getEmail(), 
                nuevoProveedor.getBirthDate(), 
                nuevoProveedor.getDescription(), 
                nuevoProveedor.getWebsiteURL()
        );
        
        // Se da de alta en la base de datos
        dataPersistencia.altaProveedor(objProveedor);
    }
    
    @Override
    public void altaTurista(DTTurista nuevoTurista) throws MyException {
        dataPersistencia.existeUsuario(
                nuevoTurista.getEmail(),
                nuevoTurista.getNickname()
        );
                
        // Si no existe el turista, se crea el objeto correspondiente
        Turista objTurista = new Turista(
                nuevoTurista.getNickname(), 
                nuevoTurista.getName(), 
                nuevoTurista.getLastName(), 
                nuevoTurista.getEmail(), 
                nuevoTurista.getBirthDate(), 
                nuevoTurista.getNacionality()
        );
              
        // Se da de alta en la base de datos
        dataPersistencia.altaTurista(objTurista);
    }
    
    @Override
    public List<DTUsuario> obtenerUsuarios() {
        List<DTUsuario> userList = new LinkedList<>();

        // Añadir usuarios proveedores a la lista
        List<DTProveedor> proveedores = dataPersistencia.obtenerProveedores(); 
        userList.addAll(proveedores);

        // Añadir usuarios turistas a la lista
        List<DTTurista> turistas = dataPersistencia.obtenerTuristas();
        userList.addAll(turistas);

        return userList;
    }
    
    @Override
    public void actualizarUsuario(DTUsuario usuario) throws MyException{
        // Se verifica el tipo de instancia recibida usando reflexión
        if (usuario instanceof DTTurista) {
            DTTurista turista = (DTTurista) usuario;
            // Se realizan las operaciones necesarias con la instancia de DTTurista
            // Se crea su objeto
            Turista objTurista = new Turista(
                    turista.getNickname(), 
                    turista.getName(), 
                    turista.getLastName(), 
                    turista.getEmail(), 
                    turista.getBirthDate(), 
                    turista.getNacionality()
            );
            dataPersistencia.actualizarTurista(objTurista);
        } else if (usuario instanceof DTProveedor) {
            DTProveedor proveedor = (DTProveedor) usuario;
            // Se realizan las operaciones necesarias con la instancia de DTProveedor
            // En caso de que sea proveedor
            Proveedor objProveedor = new Proveedor(
                    proveedor.getNickname(), 
                    proveedor.getName(), 
                    proveedor.getLastName(), 
                    proveedor.getEmail(), 
                    proveedor.getBirthDate(), 
                    proveedor.getDescription(), 
                    proveedor.getWebsiteURL()
            );
            dataPersistencia.actualizarProveedor(objProveedor);
        } else {
            throw new MyException("Tipo de usuario no válido");
        }
    }
    
    @Override
    public void existeActividadTuristica(String nombre)throws MyException{
        dataPersistencia.existeActividadTuristica(nombre);
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor){
        dataPersistencia.altaActividadTuristica(dtActividadTuristica, idDepartamento, idProveedor);
    }
    
    @Override
    public List<DTProveedor> obtenerProveedores(){
        return dataPersistencia.obtenerProveedores();
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
    public List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(String nombreActividad){
        Long idActividad = 0L;
        List <DTActividadTuristica> dtActividadesTuristicas = dataPersistencia.obtenerActividadesTuristicas();

        for(DTActividadTuristica a : dtActividadesTuristicas){
            if(a.getNombre().equals(nombreActividad)){
                idActividad = a.getId();
            }
        }

        return dataPersistencia.obtenerPaquetesRelacionados(idActividad);
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
    public void altaInscripcion(
            DTInscripcion dtInscripcion, String nombreActividad, String nombreSalida, String nicknameTurista) throws MyException {
        float costoActividad = dataPersistencia.obtenerCostoActividad(nombreActividad);
        
        dtInscripcion.setCostoTotal(costoActividad * dtInscripcion.getCantidadTuristas());
        
        dataPersistencia.altaInscripcion(dtInscripcion, nombreSalida, nicknameTurista);
    }
    
    @Override
    public List<DTTurista> obtenerTuristas() {
        return dataPersistencia.obtenerTuristas();
    }
    
    @Override
    public List<String> obtenerPaqueteNombres(){
         return dataPersistencia.obtenerPaqueteNombre();
     }
    @Override
    public DTPaqueteActividadTuristica obtenerPaquete(String nombre){
        return dataPersistencia.obtenerPaquete(nombre);
    }
    @Override
    public List<String> obtenerActividadesTuristicasCU10(String departamento,String paquete){
        return dataPersistencia.obtenerActividadesTuristicasCU10(departamento, paquete);
    }
    @Override
    public void agregarActividadPaquete(String paquete,String actividad){
        dataPersistencia.agregarActividadPaquete(paquete,actividad);
    }
    @Override
    public List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete){
        return dataPersistencia.obtenerActividadesRelacionadas(nomPaquete);
    }
    @Override
    public DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad){
        return dataPersistencia.CU11obtenerDepartamentoActividad(nombreActividad);
    }
}
