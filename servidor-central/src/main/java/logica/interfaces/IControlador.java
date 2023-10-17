/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.interfaces;

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTCategoria;
import dataTypes.DTCompraPaquete;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTInscripcion;
import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import java.util.List;
import exceptions.MyException;

/**
 *
 * @author lucho
 */
public interface  IControlador {
    /* CU USUARIOS */
    void altaProveedor(DTProveedor nuevoProveedor) throws MyException;
    void altaTurista(DTTurista nuevoTurista) throws MyException;
    List<DTUsuario> obtenerUsuarios();
    DTUsuario obtenerUsuario(String nickname);
    DTUsuario obtenerUsuarioAlternativo(String nickname);
    void actualizarUsuario(DTUsuario usuario) throws MyException;    
    List<DTSalidaTuristica> obtenerSalidasDeTurista(long idTurista);
    List<DTSalidaTuristica> obtenerSalidasDeProveedor(long idProveedor);
    List<DTActividadTuristica> obtenerActividadesDeProveedor(long idProveedor);
    
    /*CU4*/
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica,Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto);
    List<DTProveedor> obtenerProveedores();
    byte[] obtenerFotoActividadTuristica(Long id);
    
    /*CU5*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento);
    DTActividadTuristica obtenerActividadTuristica(String nombreActividad);
    List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(String nombreActividad);

    /*CU6*/
    void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException;
    
    /*CU7*/
    List<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad);
    DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida);
    
    /*CU8*/
    void altaInscripcion(DTInscripcion dtInscripcion, String nombreActividad, String nombreSalida, String nicknameTurista) throws MyException;
    List<DTTurista> obtenerTuristas();
            
    /*CU12*/
    void existeDepartamento(String nombre)throws MyException;
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    /*CU PAQUETES*/
    void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete);
    List<String> obtenerPaqueteNombres();
    DTPaqueteActividadTuristica obtenerPaquete(String nombre);
    List<String> obtenerActividadesTuristicasCU10(String Departamento,String paquete);
    void agregarActividadPaquete(String paquete,String actividad);
    List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete);
    public DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad);
    public List<String> obtenerPaqueteNombresActividades();
    void agregarCompraPaquete(DTCompraPaquete compra);
    public DTPaqueteActividadTuristica obtenerPaqueteCosto(String nombre);
    public List<DTPaqueteActividadTuristica> obtenerPaquetes();
    public DTTurista obtenerTurista(long idTurista);
    /*CU13 ALTA DE CATEGORIA*/
    void altaCategoria(String nombre) throws MyException;
    List<DTCategoria> obtenerCategorias ();
    
    /*CU14 CONFIRMAR CATEGORIA*/
    List<DTActividadTuristica> obtenerActividadesSinConfirmar();
    void validarActividad(Long id, EstadoActividad estado);
    
    
    /* Servidor Web */
    
    /* CU Alta Salida Turistica*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor);
    
}
