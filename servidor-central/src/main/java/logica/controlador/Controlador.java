package logica.controlador;
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
import dataTypes.DTUsuario;
import logica.interfaces.IControlador;
import exceptions.MyException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.FDataPersistencia;
import persistencia.IDataPersistencia;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

/**
 *
 * @author todos
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
        
        // Si no existe el proveedor, se hashea el password para almacenarlo en la BD
        String hashedPassword = nuevoProveedor.hashPassword(nuevoProveedor.getPassword());
        nuevoProveedor.setPassword(hashedPassword);

        // Se da de alta en la base de datos
        dataPersistencia.altaProveedor(nuevoProveedor);
    }
    
    @Override
    public void altaTurista(DTTurista nuevoTurista) throws MyException {
        dataPersistencia.existeUsuario(
                nuevoTurista.getEmail(),
                nuevoTurista.getNickname()
        );
                
        // Si no existe el turista, se crea el objeto correspondiente
        String hashedPassword = nuevoTurista.hashPassword(nuevoTurista.getPassword());
        nuevoTurista.setPassword(hashedPassword);
              
        // Se da de alta en la base de datos
        dataPersistencia.altaTurista(nuevoTurista);
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
    public DTUsuario obtenerUsuario(String nickname) {
        List<DTUsuario> userList = this.obtenerUsuarios();
        
        // Se itera a través de la lista de usuarios y busca el usuario con el nickname proporcionado
        for (DTUsuario usuario : userList) {
            if (usuario.getNickname().equals(nickname)) {
                return usuario; // Devuelve el usuario cuando se encuentra la coincidencia
            }
        }
        
        return null;
    }
    
    @Override
    /**
     * Busca la coincidencia de un turista o un proveedor basandose en Email o Nickname
     * @nickname valor que puede ser el Email o el Nickname del usuario
     */
    public DTUsuario obtenerUsuarioAlternativo(String nickname) {
        List<DTUsuario> userList = this.obtenerUsuarios();
        
        // Se itera a través de la lista de usuarios y busca el usuario con el nickname proporcionado
        for (DTUsuario usuario : userList) {
            if (usuario.getNickname().equals(nickname) || usuario.getEmail().equals(nickname)) {
                return usuario; // Devuelve el usuario cuando se encuentra la coincidencia
            }
        }
        
        return null;
    }
     
    @Override
    public void actualizarUsuario(DTUsuario usuario) throws MyException {
        if((usuario.getPassword() != null) && !usuario.getPassword().isBlank()){
            String hashedPassword = usuario.hashPassword(usuario.getPassword());
            usuario.setPassword(hashedPassword);
        }
        // Se verifica el tipo de instancia recibida usando reflexión
        if (usuario instanceof DTTurista) {
            // Se realizan las operaciones necesarias con la instancia de DTTurista
            DTTurista turista = (DTTurista) usuario;
            dataPersistencia.actualizarTurista(turista);
        } else if (usuario instanceof DTProveedor) {
            // Se realizan las operaciones necesarias con la instancia de DTProveedor
            DTProveedor proveedor = (DTProveedor) usuario;
            dataPersistencia.actualizarProveedor(proveedor);
        } else {
            throw new MyException("Tipo de usuario no válido");
        }
    }
    
    @Override
    public List<DTSalidaTuristica> obtenerSalidasDeTurista(long idTurista){
        return dataPersistencia.obtenerSalidasDeTurista(idTurista); 
    }
    
    @Override
    public List<DTSalidaTuristica> obtenerSalidasDeProveedor(long idProveedor){
        return dataPersistencia.obtenerSalidasDeProveedor(idProveedor);
    }
    
    @Override
    public List<DTSalidaTuristica> obtenerSalidasDeProveedorCompleto(long idProveedor){
        return dataPersistencia.obtenerSalidasDeProveedorCompleto(idProveedor);
    }

    
    @Override
    public List<DTActividadTuristica> obtenerActividadesDeProveedor(long idProveedor){
        return dataPersistencia.obtenerActividadesDeProveedor(idProveedor); 
    }
    
    @Override
    public ArrayList<DTActividadTuristica> obtenerActividadesFinalizables(long idProveedor){
        return dataPersistencia.obtenerActividadesFinalizables(idProveedor);
    }
        
    @Override
    public List<DTActividadTuristica> obtenerActividadesDeProveedorCompleto(long idProveedor){
        return dataPersistencia.obtenerActividadesDeProveedorCompleto(idProveedor);
    }
    
    @Override
    public void existeActividadTuristica(String nombre)throws MyException{
        dataPersistencia.existeActividadTuristica(nombre);
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto){
        dataPersistencia.altaActividadTuristica(dtActividadTuristica, idDepartamento, idProveedor, categorias, foto);
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto, String url){
        dataPersistencia.altaActividadTuristica(dtActividadTuristica, idDepartamento, idProveedor, categorias, foto, url);
    }
    
    @Override
    public byte[] obtenerFotoActividadTuristica(Long id){
        return dataPersistencia.obtenerFotoActividadTuristica(id);
    }
    
    @Override
    public String obtenerUrlVideo(Long id){
        return dataPersistencia.obtenerUrlVideo(id);
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
    public List<DTActividadTuristica> obtenerActividadesTuristicasConId(String nombreDepartamento){
        Long idDepartamento = 0L;
        
        List<DTDepartamento> departamentos = dataPersistencia.obtenerDepartamentos();
        
        for(DTDepartamento de : departamentos){
            if(de.getNombre().equals(nombreDepartamento)){
                idDepartamento = de.getId();
            }
        }
        
        return dataPersistencia.obtenerActividadesTuristicasConId(idDepartamento);
    }
    
    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionadosCompletos(Long idActividad){
        return dataPersistencia.obtenerPaquetesRelacionadosCompletos(idActividad);
    }
    
    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicasPorCategoria(String nombreCategoria){
        return dataPersistencia.obtenerActividadesTuristicasPorCategoria(nombreCategoria);
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
    public DTActividadTuristica obtenerActividadTuristicaNull(Long idActividad){
        return dataPersistencia.obtenerActividadTuristicaNull(idActividad);
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
    public boolean compraExiste(DTCompraPaquete compra){
        return dataPersistencia.compraExiste(compra);
    }
    @Override
    public DTActividadTuristica obtenerFotoActividadTuristicaID(String actividad){
        return dataPersistencia.obtenerFotoActividadTuristicaID(actividad);
    }
    @Override
    public void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete, byte [] foto){
        dataPersistencia.altaPaqueteActividadTuristica(dtPaquete, foto);
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
    public ArrayList<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad) {
        return dataPersistencia.obtenerSalidasTuristicas(nombreActividad);
    }
    
    @Override
    public DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida) {
        return dataPersistencia.obtenerSalidaTuristica(nombreSalida);
    }
    
    @Override
    public void altaInscripcion(
            DTInscripcion dtInscripcion, String nombreActividad, String nombreSalida, String nicknameTurista) throws MyException {
        if (dtInscripcion.getCostoTotal() == 0) {
            float costoActividad = dataPersistencia.obtenerCostoActividad(nombreActividad);

            dtInscripcion.setCostoTotal(costoActividad * dtInscripcion.getCantidadTuristas());
        }
        
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
    
    @Override
    public void altaCategoria(String nombre) throws MyException{
        dataPersistencia.altaCategoria(nombre);
    }
    
    @Override
    public List<DTCategoria> obtenerCategorias(){
        return dataPersistencia.obtenerCategorias();
    }
    
    @Override
    public List<DTActividadTuristica> obtenerActividadesSinConfirmar(){
        return dataPersistencia.obtenerActividadesSinConfirmar();
    }
    
    @Override
    public void validarActividad(Long id, EstadoActividad estado){
        dataPersistencia.validarActividad(id, estado);
    }
    
    @Override
    public ArrayList<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor){
        return dataPersistencia.obtenerActividadesTuristicas(nombreDepartamento, idProveedor);
    }
    @Override
    public List<String> obtenerPaqueteNombresActividades(){
        return dataPersistencia.obtenerPaqueteNombresActividades();
    }
    @Override
    public void agregarCompraPaquete(DTCompraPaquete compra){
        dataPersistencia.agregarCompraPaquete(compra);
    }
    @Override
    public DTPaqueteActividadTuristica obtenerPaqueteCosto(String nombre){
         return dataPersistencia.obtenerPaqueteCosto(nombre);
    }
    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetes(){
        return dataPersistencia.obtenerPaquetes();
    }
    @Override
    public DTTurista obtenerTurista(long idTurista){
        return dataPersistencia.obtenerTurista(idTurista);
    }
    @Override
    public byte[] obtenerFotoPaqueteActividadTuristica(String selectedPaquete){
        return dataPersistencia.obtenerFotoPaqueteActividadTuristica(selectedPaquete);
    }
    
    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetesComprados(Long idTurista, String nombreSalida, int cantTuristas) {
        return dataPersistencia.obtenerPaquetesCompradosDisponibles(idTurista, nombreSalida, cantTuristas);
    }
    
    @Override
    public float obtenerCostoActividad(String nombreActividad) {
        return dataPersistencia.obtenerCostoActividad(nombreActividad);
    }
    
    @Override
    public byte[] obtenerPdfInscripcion(String nickname, String nombreSalida) {
        DTInscripcion inscripcion = dataPersistencia.obtenerInscripcion(nickname, nombreSalida);
        
        InputStream templateInputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("templates/turismoUyInscripcion.docx");

        XWPFDocument document;
        try {
            document = new XWPFDocument(templateInputStream);

            replaceInPdf(document, "$nombre", inscripcion.getTurista().getName());
            replaceInPdf(document, "$actividad", inscripcion.getSalidaTuristica().getDtActividadTuristica().getNombre());
            replaceInPdf(document, "$salida", inscripcion.getSalidaTuristica().getNombre());
            replaceInPdf(document, "cantTuristas", String.valueOf(inscripcion.getCantidadTuristas()));
            replaceInPdf(document, "$costo", String.valueOf(inscripcion.getCostoTotal()));
            
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
            
            LocalDateTime locaDateInscripcion = inscripcion.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            String fechaInscripcion = locaDateInscripcion.format(dateFormat);
            replaceInPdf(document, "fechaInscripcion", fechaInscripcion);
            
            String actualDate = LocalDateTime.now().format(dateFormat);
            replaceInPdf(document, "fechaEmitido", actualDate);

            
            // Convert XWPFDocument to Pdf
            PdfOptions options = PdfOptions.create();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfConverter pdfConverter = new PdfConverter();
            pdfConverter.convert(document, outputStream, options);
            
            return outputStream.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void replaceInPdf(XWPFDocument document, String prevText, String newText) {
        for (XWPFParagraph p : document.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains(prevText)) {
                        text = text.replace(prevText, newText);
                        r.setText(text, 0);
                    }
                }
            }
        }
    }

    @Override
    public List<DTTop> obtenerTop(){
        return dataPersistencia.obtenerTop();
    }
    
    
    @Override
    public ArrayList<DTBusqueda> obtenerBusqueda(String peticionBusqueda){
        return dataPersistencia.obtenerBusqueda(peticionBusqueda);
    }
    
    @Override
    public ArrayList<DTBusqueda> ordenarBusquedaFecha(String peticionBusqueda){
        return dataPersistencia.ordenarBusquedaFecha(peticionBusqueda);
    }
    
    @Override
    public ArrayList<DTBusqueda> ordenarBusquedaDepartamento(String peticionBusqueda, String nombreDepartamento){
        return dataPersistencia.ordenarBusquedaDepartamento(peticionBusqueda,nombreDepartamento);
    }
    
    @Override
    public ArrayList<DTBusqueda> ordenarBusquedaCategoria(String peticionBusqueda,String categoria){
        return dataPersistencia.ordenarBusquedaCategoria(peticionBusqueda,categoria);
    }
    
    @Override
    public int obtenerCantidadFavoritos(String nombreActividad){
        return dataPersistencia.obtenerCantidadFavoritos(nombreActividad);
    }
    
    @Override
    public List<String> obtenerActividadesFavoritas(String nickname){
        return dataPersistencia.obtenerActividadesFavoritas(nickname);
    }
    
    @Override
    public void agregarAFavoritos(Long idActividad, String nickname){
        dataPersistencia.agregarAFavoritos(idActividad, nickname);
    }
        
    @Override
    public void eliminarDeFavoritos(Long idActividad, String nickname){
        dataPersistencia.eliminarDeFavoritos(idActividad, nickname);
    }
    
}
