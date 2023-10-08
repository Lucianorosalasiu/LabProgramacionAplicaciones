/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTCategoria;
import dataTypes.DTDepartamento;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTProveedor;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTTurista;
import java.util.List;
import exceptions.MyException;
import logica.clases.Proveedor;
import logica.clases.Turista;
import persistencia.entidades.EProveedor;
import persistencia.entidades.ETurista;

/**
 *
 * @author all
 */
public interface IDataPersistencia {
    /* CU USUARIOS */
    void existeUsuario(String email, String nickname) throws MyException;
    void altaProveedor(Proveedor objProveedor) throws MyException;
    void altaTurista(Turista objTurista) throws MyException;
    
    List<DTTurista> obtenerTuristas();
    ETurista buscarTuristaPorNickname(String nickname) throws MyException;
    EProveedor buscarProveedorPorNickname(String nickname) throws MyException;
    void actualizarProveedor(Proveedor objProveedor) throws MyException;
    void actualizarTurista(Turista objTurista) throws MyException;
    
    List<DTSalidaTuristica> obtenerSalidasDeTurista(long idTurista);
    List<DTSalidaTuristica> obtenerSalidasDeProveedor(long idProveedor);
    List<DTActividadTuristica> obtenerActividadesDeProveedor(long idProveedor); 
    
    /*CU4*/
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor,List<Long> categorias);
    List<DTProveedor> obtenerProveedores();
    

    /*CU5*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(Long idDepartamento);
    List<DTActividadTuristica> obtenerActividadesTuristicas();
    DTActividadTuristica obtenerActividadTuristica(Long idActividad);
    List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(Long idActividad);

    /*CU6*/
    void existeSalidaTuristica(String nombre) throws MyException;
    void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException;
    
    /*CU7*/
    List<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad);
    DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida);
    
    /*CU8*/
    float obtenerCostoActividad(String nombreActividad);
    void altaInscripcion(DTInscripcion dtInscripcion, String nombreSalida, String nickname) throws MyException;
    
    /*CU12*/
    void existeDepartamento(String nombre)throws MyException;
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete);
    List<String> obtenerPaqueteNombre();
    DTPaqueteActividadTuristica obtenerPaquete(String nombre);
    List<String> obtenerActividadesTuristicasCU10(String departamento,String paquete);
    void agregarActividadPaquete(String paquete,String actividad);
    List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete);
    DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad);
     
    void altaCategoria(String nombre) throws MyException;
    List<DTCategoria> obtenerCategorias ();
    
    /*CU 14 CONFIRMAR CATEGORIA*/
    List<DTActividadTuristica> obtenerActividadesSinConfirmar();
    void validarActividad(Long id, EstadoActividad estado);

    
    List<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor);
}
