/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.interfaces;

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTBusqueda;
import dataTypes.DTCategoria;
import dataTypes.DTCompraPaquete;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTInscripcion;
import dataTypes.DTProveedor;
import dataTypes.DTTop;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import java.util.List;
import exceptions.MyException;
import java.util.ArrayList;

/**
 *
 * @author lucho
 */
public interface  IControlador {
    /*CU USUARIOS*/
    DTUsuario obtenerUsuario(String nickname);
    DTUsuario obtenerUsuarioAlternativo(String nickname);
    List<DTUsuario> obtenerUsuarios();
    List<DTTurista> obtenerTuristas();
    List<DTProveedor> obtenerProveedores();
    void altaProveedor(DTProveedor nuevoProveedor) throws MyException;
    void altaTurista(DTTurista nuevoTurista) throws MyException;
    void actualizarUsuario(DTUsuario usuario) throws MyException;    
    void seguirUsuario(long idSeguidor, long idSeguido) throws MyException;
    void dejarDeSeguirUsuario(long idSeguidor, long idSeguido) throws MyException ;
    ArrayList<Long> obtenerSeguidos(long idSeguidor);
    ArrayList<Long> obtenerSeguidores(long idSeguido);
    
    List<DTSalidaTuristica> obtenerSalidasDeTurista(long idTurista);
    List<DTSalidaTuristica> obtenerSalidasDeProveedor(long idProveedor);
    List<DTActividadTuristica> obtenerActividadesDeProveedor(long idProveedor);
    List<DTSalidaTuristica> obtenerSalidasDeProveedorCompleto(long idProveedor);
    List<DTActividadTuristica> obtenerActividadesDeProveedorCompleto(long idProveedor);
    ArrayList<DTActividadTuristica> obtenerActividadesFinalizables(long idProveedor);
    
    /*CU4*/
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica,Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto);
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica,Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto, String url);
    byte[] obtenerFotoActividadTuristica(Long id);
    String obtenerUrlVideo(Long id);
    
    /*CU5*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento);
    List<DTActividadTuristica> obtenerActividadesTuristicasConId(String nombreDepartamento);
    List<DTActividadTuristica> obtenerActividadesTuristicasPorCategoria(String nombreCategoria);
    DTActividadTuristica obtenerActividadTuristica(String nombreActividad);
    List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(String nombreActividad);
    DTActividadTuristica obtenerActividadTuristicaNull(Long idActividad);
    List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionadosCompletos(Long idActividad);

    /*CU6*/
    void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException;
    
    /*CU7*/
    ArrayList<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad);
    DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida);
    
    /*CU8*/
    void altaInscripcion(DTInscripcion dtInscripcion, String nombreActividad, String nombreSalida, String nicknameTurista) throws MyException;

            
    /*CU12*/
    void existeDepartamento(String nombre)throws MyException;
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    /*CU PAQUETES*/
    void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete, byte [] foto);
    List<String> obtenerPaqueteNombres();
    DTPaqueteActividadTuristica obtenerPaquete(String nombre);
    List<String> obtenerActividadesTuristicasCU10(String Departamento,String paquete);
    void agregarActividadPaquete(String paquete,String actividad);
    List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete);
    DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad);
    List<String> obtenerPaqueteNombresActividades();
    void agregarCompraPaquete(DTCompraPaquete compra);
     DTPaqueteActividadTuristica obtenerPaqueteCosto(String nombre);
    List<DTPaqueteActividadTuristica> obtenerPaquetes();
    DTTurista obtenerTurista(long idTurista);
    byte[] obtenerFotoPaqueteActividadTuristica(String selectedPaquete);
    DTActividadTuristica obtenerFotoActividadTuristicaID(String actividad);
    boolean compraExiste(DTCompraPaquete compra);
    /*CU13 ALTA DE CATEGORIA*/
    void altaCategoria(String nombre) throws MyException;
    List<DTCategoria> obtenerCategorias ();
    
    /*CU14 CONFIRMAR CATEGORIA*/
    List<DTActividadTuristica> obtenerActividadesSinConfirmar();
    void validarActividad(Long id, EstadoActividad estado);
    
    /*Obtener top*/
    List<DTTop> obtenerTop();
    
    /*Busqueda*/
    ArrayList<DTBusqueda> obtenerBusqueda(String peticionBusqueda);
    ArrayList<DTBusqueda> ordenarBusquedaFecha(String peticionBusqueda);
    ArrayList<DTBusqueda> ordenarBusquedaDepartamento(String peticionBusqueda, String departamentoNombre);
    ArrayList<DTBusqueda> ordenarBusquedaCategoria(String peticionBusqueda,String categoria);
    
    /*Servidor Web*/
    
    /*CU Alta Salida Turistica*/
    ArrayList<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor);
    List<DTPaqueteActividadTuristica> obtenerPaquetesComprados(Long idTurista, String nombreSalida, int cantTuristas);
    float obtenerCostoActividad(String nombreActividad);
    
    byte[] obtenerPdfInscripcion(String nickname, String nombreSalida);
    
    int obtenerCantidadFavoritos(String nombreActividad);
    List<String> obtenerActividadesFavoritas(String nickname);
    void agregarAFavoritos(Long idActividad, String nickname);
    void eliminarDeFavoritos(Long idActividad, String nickname);
    
}
