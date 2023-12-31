package persistencia;

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTBusqueda;
import dataTypes.DTCategoria;
import dataTypes.DTCompraPaquete;
import dataTypes.DTDepartamento;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTProveedor;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTTop;
import dataTypes.DTTurista;
import java.util.List;
import exceptions.MyException;
import java.util.ArrayList;
import persistencia.entidades.EProveedor;
import persistencia.entidades.ETurista;

/**
 *
 * @author all
 */
public interface IDataPersistencia {
    /* CU USUARIOS */
    void existeUsuario(String email, String nickname) throws MyException;
    ETurista buscarTuristaPorNickname(String nickname) throws MyException;
    EProveedor buscarProveedorPorNickname(String nickname) throws MyException;
    DTTurista obtenerTurista(long idTurista);
    List<DTTurista> obtenerTuristas();
    List<DTProveedor> obtenerProveedores();
    void altaProveedor(DTProveedor proveedor) throws MyException;
    void altaTurista(DTTurista turista) throws MyException;
    void actualizarProveedor(DTProveedor proveedor) throws MyException;
    void actualizarTurista(DTTurista turista) throws MyException;
    void seguirUsuario(long idSeguidor, long idSeguido) throws MyException;
    void dejarDeSeguirUsuario(long idSeguidor, long idSeguido) throws MyException;
    ArrayList<Long> obtenerSeguidos(long idSeguidor);
    ArrayList<Long> obtenerSeguidores(long idSeguido);
    
    List<DTSalidaTuristica> obtenerSalidasDeTurista(long idTurista);
    List<DTSalidaTuristica> obtenerSalidasDeProveedor(long idProveedor);
    List<DTSalidaTuristica> obtenerSalidasDeProveedorCompleto(long idProveedor);
    List<DTActividadTuristica> obtenerActividadesDeProveedor(long idProveedor);
    List<DTActividadTuristica> obtenerActividadesDeProveedorCompleto(long idProveedor);
    ArrayList<DTActividadTuristica> obtenerActividadesFinalizables(long idProveedor);
    
    /*CU4*/
    void existeActividadTuristica(String nombre)throws MyException;
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor,List<Long> categorias, byte[] foto);
    void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor,List<Long> categorias, byte[] foto, String url);
    byte[] obtenerFotoActividadTuristica(Long id);
    String obtenerUrlVideo(Long id);
    
    /*CU5*/
    List<DTActividadTuristica> obtenerActividadesTuristicas(Long idDepartamento);
    List<DTActividadTuristica> obtenerActividadesTuristicasConId(Long idDepartamento);
    List<DTActividadTuristica> obtenerActividadesTuristicasPorCategoria(String nombreCategoria);
    List<DTActividadTuristica> obtenerActividadesTuristicas();
    DTActividadTuristica obtenerActividadTuristica(Long idActividad);
    List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(Long idActividad);
    DTActividadTuristica obtenerActividadTuristicaNull(Long idActividad);
    List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionadosCompletos(Long idActividad);

    /*CU6*/
    void existeSalidaTuristica(String nombre) throws MyException;
    void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException;
    
    /*CU7*/
    ArrayList<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad);
    DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida);
    
    /*CU8*/
    float obtenerCostoActividad(String nombreActividad);
    void altaInscripcion(DTInscripcion dtInscripcion, String nombreSalida, String nickname) throws MyException;
    
    /*CU12*/
    void existeDepartamento(String nombre)throws MyException;
    void altaDepartamento(DTDepartamento departamento);
    List<DTDepartamento> obtenerDepartamentos();
    
    void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete, byte [] foto);
    List<String> obtenerPaqueteNombre();
    DTPaqueteActividadTuristica obtenerPaquete(String nombre);
    List<String> obtenerActividadesTuristicasCU10(String departamento,String paquete);
    void agregarActividadPaquete(String paquete,String actividad);
    List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete);
    DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad);
    List<String> obtenerPaqueteNombresActividades();
    void agregarCompraPaquete(DTCompraPaquete compra);
    DTPaqueteActividadTuristica obtenerPaqueteCosto(String nombre);
    List<DTPaqueteActividadTuristica>obtenerPaquetes();
    byte[] obtenerFotoPaqueteActividadTuristica(String selectedPaquete);
    DTActividadTuristica obtenerFotoActividadTuristicaID(String actividad);         
    void altaCategoria(String nombre) throws MyException;
    List<DTCategoria> obtenerCategorias ();
    boolean compraExiste(DTCompraPaquete compra);
    
    /*CU 14 CONFIRMAR CATEGORIA*/
    List<DTActividadTuristica> obtenerActividadesSinConfirmar();
    void validarActividad(Long id, EstadoActividad estado);
    
    /* Obtener top */
    List<DTTop> obtenerTop();
    
    /*Busqueda*/
    ArrayList<DTBusqueda> obtenerBusqueda(String peticionBusqueda);
    ArrayList<DTBusqueda> ordenarBusquedaFecha(String peticionBusqueda);
    ArrayList<DTBusqueda> ordenarBusquedaDepartamento(String peticionBusqueda, String nombreDepartamento);
    ArrayList<DTBusqueda> ordenarBusquedaCategoria(String peticionBusqueda,String categoria);
    
    /* Servidor Web */
    
    /* CU Alta Salida Turistica */
    ArrayList<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor);
    List<DTPaqueteActividadTuristica> obtenerPaquetesCompradosDisponibles(Long idTurista, String nombreSalida, int cantTuristas);
    
    DTInscripcion obtenerInscripcion(String nickname, String nombreSalida);
    
    int obtenerCantidadFavoritos(String nombreActividad);
    List<String> obtenerActividadesFavoritas(String nickname);
    void agregarAFavoritos(Long idActividad, String nickname);
    void eliminarDeFavoritos(Long idActividad, String nickname);
}
