package persistencia;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTProveedor;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTTurista;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.clases.Departamento;
import exceptions.MyException;
import javax.persistence.Query;
import logica.clases.PaqueteActividadTuristica;
import logica.clases.Proveedor;
import logica.clases.Turista;
import persistencia.entidades.EActividadTuristica;
import persistencia.entidades.EDepartamento;
import persistencia.entidades.EInscripcion;
import persistencia.entidades.EPaqueteActividadTuristica;
import persistencia.entidades.EProveedor;
import persistencia.entidades.ESalidaTuristica;
import persistencia.entidades.ETurista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author all
 */

public class DataPersistencia implements IDataPersistencia {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("puConexionBD");
    private static DataPersistencia instancia = null;

    private DataPersistencia(){}

    public static DataPersistencia getInstance() {
        if (instancia == null)
            instancia = new DataPersistencia();
        return instancia;
    }
    
    /**
     * 
     * @param email del usuario, ya sea proveedor o turista
     * @param nickname lo mismo que el email
     * @throws MyException en caso de que exista un usuario que coincida con el
     * nick o el email recibidos
     */
    @Override
    public void existeUsuario(String email, String nickname) throws MyException {
        EntityManager em = emf.createEntityManager();    
        String qryProveedor = "SELECT p from EProveedor p where p.email = :emailProveedor OR p.nickname = :nicknameProveedor";
        String qryTurista = "SELECT t from ETurista t where t.email = :emailTurista OR t.nickname = :nicknameTurista";
        List<EProveedor> resultadoProveedor = new LinkedList<>();
        List<ETurista> resultadoTurista = new LinkedList<>();
        
        try{
            resultadoProveedor = em.createQuery(qryProveedor, EProveedor.class)
                    .setParameter("emailProveedor", email)
                    .setParameter("nicknameProveedor", nickname)
                    .getResultList();
            
            resultadoTurista = em.createQuery(qryTurista, ETurista.class)
                    .setParameter("emailTurista", email)
                    .setParameter("nicknameTurista", nickname)
                    .getResultList();  
        }catch(Exception e){
            throw new MyException("¡ERROR! Algo salio mal al realizar la consulta");
        }finally{
            em.close();
        }   
        
        if(!resultadoProveedor.isEmpty() || !resultadoTurista.isEmpty() ){   
            throw new MyException("Ya existe un usuario con el email o nickname ingresado.");
        }
    }

    @Override
    public void altaProveedor(Proveedor objProveedor) throws MyException {
        EntityManager em = emf.createEntityManager();   
        try {
            EProveedor nProveedor = new EProveedor(
                    objProveedor.getNickname(),
                    objProveedor.getName(),
                    objProveedor.getLastName(),
                    objProveedor.getEmail(),
                    objProveedor.getBirthDate(),
                    objProveedor.getDescription(),
                    objProveedor.getWebsiteURL()            
            );

            em.getTransaction().begin();
            em.persist(nProveedor);
            em.getTransaction().commit(); 
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new MyException("¡ERROR! Algo salio mal al insertar el nuevo proveedor");
        } finally {
            em.close();
        }
    }
    
    @Override
    public void altaTurista(Turista objTurista) throws MyException {
        EntityManager em = emf.createEntityManager();   
        try {
            ETurista nTurista = new ETurista(
                    objTurista.getNickname(),
                    objTurista.getName(),
                    objTurista.getLastName(),
                    objTurista.getEmail(),
                    objTurista.getBirthDate(),
                    objTurista.getNacionality()
            );

            em.getTransaction().begin();
            em.persist(nTurista);
            em.getTransaction().commit(); 
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new MyException("¡ERROR! Algo salio mal al insertar el nuevo turista");
        } finally {
            em.close();
        }
    }
    
    @Override
    public void existeActividadTuristica(String nombre)throws MyException{
        EntityManager em = emf.createEntityManager();    
        String consulta = "select a from EActividadTuristica a where a.nombre = :nombreActividad";
        List<EActividadTuristica> resultado = new LinkedList<>();
        
        try{
        resultado = em.createQuery(consulta,EActividadTuristica.class)
                .setParameter("nombreActividad",nombre)
                .getResultList();
        }catch(Exception e){
            throw new MyException("Algo salio mal consultando la base de datos. ");
        }finally{
            em.close();
        }   
        
        if(!resultado.isEmpty()){   
            throw new MyException("Ya existe una actividad turistica con ese nombre. ");
        }
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor){
        EntityManager em = emf.createEntityManager();   
        
        EDepartamento eDepartamento = em.find(EDepartamento.class,idDepartamento);
        
        EActividadTuristica nuevaActividad = new EActividadTuristica(dtActividadTuristica.getNombre(),
        dtActividadTuristica.getDescripcion(),dtActividadTuristica.getDuracion(),
                dtActividadTuristica.getCosto(),dtActividadTuristica.getCiudad(),
                dtActividadTuristica.getFechaAlta(),eDepartamento);

        
        try{
            em.getTransaction().begin();
            /*consigo el proveedor al que le voy a agregar esta actividad a su lista*/
            EProveedor eProveedor = em.find(EProveedor.class,idProveedor);
            /*le paso la actividad recien creada al proveedor para que la agregue a su lista*/
            eProveedor.addActividad(nuevaActividad);
            /*finalmente persisto la nueva actividad*/
            em.persist(nuevaActividad);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    @Override 
    public List<DTProveedor> obtenerProveedores(){
        EntityManager em = emf.createEntityManager();
        List<DTProveedor> resultados = new LinkedList<>();
        List<EProveedor> resultados_consulta = new LinkedList<>();
        
        try{
            String query = "select e from EProveedor e";
            resultados_consulta = em.createQuery(query,EProveedor.class).getResultList();
            
            for(EProveedor e : resultados_consulta){
                DTProveedor dtProveedor = new DTProveedor(
                e.getId(),e.getNickname(),e.getEmail(),e.getDescription());
                resultados.add(dtProveedor);
            }
            
            return resultados;
        }catch(Exception e){
            return resultados;
        }finally{
            em.close();
        }
    }
    
    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicas(Long idDepartamento){
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados = new LinkedList<>();
        List <DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();
        
        try{
            String consulta = "select a from EActividadTuristica a where a.eDepartamento.id = :idDepartamento";
            resultados = em.createQuery(consulta,EActividadTuristica.class)
                    .setParameter("idDepartamento",idDepartamento).getResultList();
            
            for(EActividadTuristica a : resultados){
                DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(a.getNombre(),a.getDescripcion(),
                a.getDuracion(),a.getCosto(),a.getCiudad(),a.getFechaAlta());
                
                dtActividadesTuristicas.add(dtActividadTuristica);
            }
            return dtActividadesTuristicas;
        }catch(Exception e){
            return dtActividadesTuristicas;
        }finally{
            em.close();
        }
    }
    
    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicas(){
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados = new LinkedList<>();
        List <DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();
        
        try{
            String consulta = "select a from EActividadTuristica a ";
            resultados = em.createQuery(consulta,EActividadTuristica.class).getResultList();
            
            for(EActividadTuristica a : resultados){
                DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(a.getId(),a.getNombre(),a.getDescripcion(),
                a.getDuracion(),a.getCosto(),a.getCiudad(),a.getFechaAlta());
                
                dtActividadesTuristicas.add(dtActividadTuristica);
            }
            return dtActividadesTuristicas;
        }catch(Exception e){
            return dtActividadesTuristicas;
        }finally{
            em.close();
        }
    }
    
    @Override
    public DTActividadTuristica obtenerActividadTuristica(Long idActividad){
        EntityManager em = emf.createEntityManager();
        
        try{
            EActividadTuristica eActividadTuristica = em.find(EActividadTuristica.class, idActividad);
            
            DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(eActividadTuristica.getNombre(),
                    eActividadTuristica.getDescripcion(),eActividadTuristica.getDuracion(),
                    eActividadTuristica.getCosto(),eActividadTuristica.getCiudad(),eActividadTuristica.getFechaAlta());

            return dtActividadTuristica;
        }catch(Exception e){
            DTActividadTuristica dtActividadTuristica = new DTActividadTuristica();
            return dtActividadTuristica;
        }finally{
            em.close();
        }
    }
    
    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(Long idActividad){
         EntityManager em = emf.createEntityManager();
         List<EPaqueteActividadTuristica> resultados_consulta = new LinkedList<>();
         List<DTPaqueteActividadTuristica> resultados = new LinkedList<>();
         
         try{
             EActividadTuristica actividad = em.find(EActividadTuristica.class,idActividad);
             resultados_consulta = actividad.getPaquetes();
             
             for(EPaqueteActividadTuristica p : resultados_consulta){
                 DTPaqueteActividadTuristica dtPaqueteActividadTuristica = new
                     DTPaqueteActividadTuristica(p.getNombre());
                 resultados.add(dtPaqueteActividadTuristica);
             }
             
             return resultados;
         }catch(Exception e){
             return resultados;
         }finally{
             em.close();
         }
    }
    @Override
    public List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete){
        EntityManager em = emf.createEntityManager();
         List<EActividadTuristica> resultados_consulta = new LinkedList<>();
         List<DTActividadTuristica> resultados = new LinkedList<>();
         
         try{
             EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete"
                    ,EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete",nomPaquete)
                    .getSingleResult();
             
             EPaqueteActividadTuristica paquete = em.find(EPaqueteActividadTuristica.class,ePaquete.getId());
             resultados_consulta = paquete.getActividades();
             
             for(EActividadTuristica p : resultados_consulta){
                 DTActividadTuristica dtActividadTuristica = new
                     DTActividadTuristica(p.getId(),p.getNombre(),null,null,0,null,null);
                 resultados.add(dtActividadTuristica);
             }
             
             return resultados;
         }catch(Exception e){
             return resultados;
         }finally{
             em.close();
         }
    }
    @Override
    public void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete){
         EntityManager em = emf.createEntityManager();
         
         EPaqueteActividadTuristica nuevoPaquete = new EPaqueteActividadTuristica(dtPaquete.getNombre(),dtPaquete.getDescripcion(),dtPaquete.getValidez(),dtPaquete.getDescuento(),dtPaquete.getFechaAlta());
         try{
             em.getTransaction().begin();
             em.persist(nuevoPaquete);
             em.getTransaction().commit();
         }catch(Exception e){
             em.getTransaction().rollback();
         }finally{
            em.close();
         }
    }
    
    public void existeDepartamento(String nombreDepartamento)throws MyException{
        EntityManager em = emf.createEntityManager();
        String consulta = "select d from EDepartamento d where d.nombre = :nombreDepartamento";
        List<EDepartamento> resultado = new LinkedList<>();
        
        try{
            resultado = em.createQuery(consulta,EDepartamento.class)
                    .setParameter("nombreDepartamento",nombreDepartamento).getResultList();
        }catch(Exception e){
                throw new MyException("Algo salio mal consultando la base de datos. ");
        }finally{
            em.close();
        }
        
        if(!resultado.isEmpty()){
            throw new MyException("Ya existe un departamento con ese nombre en el sistema.");
        }
    }
    
    public void altaDepartamento(DTDepartamento dtDepto) {
        EntityManager em = emf.createEntityManager();
        try {
            EDepartamento nuevoDepartamento = new EDepartamento(dtDepto.getNombre(),
            dtDepto.getDescripcion(),dtDepto.getURL());

            em.getTransaction().begin();
            em.persist(nuevoDepartamento);
            em.getTransaction().commit(); 
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<DTDepartamento> obtenerDepartamentos(){
        EntityManager em = emf.createEntityManager();
        String consulta = "select d from EDepartamento d";
        List<DTDepartamento> dtDepartamentos = new LinkedList<>();  
        
        try{
            List<EDepartamento> resultado = em.createQuery(consulta,EDepartamento.class).getResultList();
            /**
             * una vez obtenida la lista de EDepartamentos los parseo a DTDepartamentos
             * para respetar la arquitectura de capas y no pasar objetos
             */
            for(EDepartamento ed: resultado){
                DTDepartamento dtDepartamento = new DTDepartamento(ed.getId(),ed.getNombre(),
                        ed.getDescripcion(),ed.getUrl());
                
                dtDepartamentos.add(dtDepartamento);
            }
            
            return dtDepartamentos;
        }catch(Exception e){
            return dtDepartamentos;
        }finally{
            em.close();
        }
    }
    
    @Override
    public void existeSalidaTuristica(String nombre) throws MyException {
        EntityManager em = emf.createEntityManager();    
        String consulta = "SELECT s from ESalidaTuristica s where s.nombre = :nombreSalida";
        List<ESalidaTuristica> resultado = new LinkedList<>();
        
        try{
            resultado = em.createQuery(consulta, ESalidaTuristica.class)
                    .setParameter("nombreSalida", nombre)
                    .getResultList();
        }catch(Exception e){
            throw new MyException("Algo salio mal consultando la base de datos. ");
        }finally{
            em.close();
        }   
        
        if(!resultado.isEmpty()){   
            throw new MyException("Ya existe una salida turistica con el nombre ingresado. ");
        }
    }
    
    @Override
    public void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException {
        EntityManager em = emf.createEntityManager();   
        
        try{
            em.getTransaction().begin();
            
            String query = "SELECT a FROM EActividadTuristica a WHERE a.nombre = :nombreActividad";
            EActividadTuristica eActividadTuristica = em.createQuery(query, EActividadTuristica.class)
                                    .setParameter("nombreActividad", nombreActividad)
                                    .getSingleResult();

            ESalidaTuristica nuevaSalida = new ESalidaTuristica(
                    dtSalidaTuristica.getNombre(),
                    dtSalidaTuristica.getCantidadMaxTuristas(),
                    dtSalidaTuristica.getFechaSalida(),
                    dtSalidaTuristica.getLugar(),
                    dtSalidaTuristica.getFechaAlta(),
                    eActividadTuristica
            );

            em.persist(nuevaSalida);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new MyException("ERROR! Algo salio durante el alta de la salida turistica. ");
        }finally{
            em.close();
        }
    }
    
    @Override
    public List<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad) {
        EntityManager em = emf.createEntityManager();
        List<ESalidaTuristica> resultados;
        List <DTSalidaTuristica> dtActividadesTuristicas = new LinkedList<>();
        
        try{
            String query = "select s from ESalidaTuristica s where s.eActividadTuristica.nombre = :nombreActividad";
            resultados = em.createQuery(query, ESalidaTuristica.class)
                            .setParameter("nombreActividad", nombreActividad)
                            .getResultList();
            
            for(ESalidaTuristica s : resultados){
                dtActividadesTuristicas.add(    
                        new DTSalidaTuristica(
                            s.getNombre(),
                            s.getCantidadMaxTuristas(),
                            s.getFechaSalida(),
                            s.getLugar(),
                            s.getFechaAlta()
                        )
                );
            }
            return dtActividadesTuristicas;
        }catch(Exception e){
            return dtActividadesTuristicas;
        }finally{
            em.close();
        }
    }
    
    @Override
    public float obtenerCostoActividad(String nombreActividad) {
        EntityManager em = emf.createEntityManager();
        
        try{
            String query = "select a from EActividadTuristica a where a.nombre = :nombreActividad";
            EActividadTuristica eActividadTuristica = em.createQuery(query, EActividadTuristica.class)
                            .setParameter("nombreActividad", nombreActividad)
                            .getSingleResult();
            
            return eActividadTuristica.getCosto();
        }catch(Exception e){
            return 0.0f;
        }finally{
            em.close();
        }
    }
    
    @Override
    public void altaInscripcion(DTInscripcion dtInscripcion, String nombreSalida, String nickname) throws MyException {
        EntityManager em = emf.createEntityManager();   
        
        ESalidaTuristica eSalidaTuristica = obtenerSalidaPorNombre(nombreSalida);
            
        // Validamos que hayan cupos para la cantidad de turistas ingresados
        int cuposDisponibles = eSalidaTuristica.getCantidadMaxTuristas() - obtenerTotalTuristasInscriptos(nombreSalida);  
        if (dtInscripcion.getCantidadTuristas() > cuposDisponibles) {
            throw new MyException("La cantidad de turistas ingresada excede los cupos disponibles para la salida!");
        }

        String queryTurista = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
        ETurista eTurista = em.createQuery(queryTurista, ETurista.class)
                                .setParameter("nickname", nickname)
                                .getSingleResult();

        // Validamos que el turista no este inscripto en la salida
        String auxQuery = "SELECT i FROM EInscripcion i WHERE i.eTurista = :turista AND i.eSalidaTuristica = :salida";
        List<EInscripcion> eInscripcionList = em.createQuery(auxQuery, EInscripcion.class)
                                .setParameter("turista", eTurista)
                                .setParameter("salida", eSalidaTuristica)
                                .getResultList();

        if (!eInscripcionList.isEmpty()) {
            throw new MyException("El turista ya se encuentra inscripto en esta salida!");
        }
            
        try{
            em.getTransaction().begin();

            EInscripcion nuevaInscripcion = new EInscripcion(
                    eTurista,
                    eSalidaTuristica,
                    dtInscripcion.getFecha(),
                    dtInscripcion.getCantidadTuristas(),
                    dtInscripcion.getCostoTotal()
            );

            em.persist(nuevaInscripcion);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new MyException("ERROR! Algo salio durante el alta de la inscripcion. ");
        }finally{
            em.close();
        }
    }
    
    @Override
    public List<String> obtenerPaqueteNombre(){
        EntityManager em = emf.createEntityManager();
      
        try{
          
            Query query = em.createQuery("select p.nombre from EPaqueteActividadTuristica p");
            List<String> resultado = query.getResultList();
            
            return resultado;
          
        }catch(Exception e){
            List<String> resultado = null;
            return resultado;
        }finally{
            em.close();
        }
    }
    
    @Override
    public DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida){
        EntityManager em = emf.createEntityManager();
        
        try{
            ESalidaTuristica eSalidaTuristica = obtenerSalidaPorNombre(nombreSalida);
            
            return new DTSalidaTuristica(
                            eSalidaTuristica.getNombre(),
                            eSalidaTuristica.getCantidadMaxTuristas(),
                            eSalidaTuristica.getFechaSalida(),
                            eSalidaTuristica.getLugar(),
                            eSalidaTuristica.getFechaAlta()
                        );
        }catch(Exception e){
            return new DTSalidaTuristica();
        }finally{
            em.close();
        }
    }
    
    @Override
    public DTPaqueteActividadTuristica obtenerPaquete(String nombre){
        EntityManager em = emf.createEntityManager();
        try{
          
            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete"
                    ,EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete",nombre)
                    .getSingleResult();
            return new DTPaqueteActividadTuristica(
                                            ePaquete.getNombre(),
                                            ePaquete.getDescripcion(),
                                            ePaquete.getValidez(),
                                            ePaquete.getDescuento(),
                                            ePaquete.getFechaAlta()
                                                  );
        }catch(Exception e){
            return new DTPaqueteActividadTuristica();
        }finally{
            em.close();
        }
    }
   
    public DTActividadTuristica CU11obtenerActividad(String nombre){
        EntityManager em = emf.createEntityManager();
        try{
          
            EActividadTuristica eActividad = em.createQuery("select p from EActividadTuristica p where p.nombre = :nombreActividad"
                    ,EActividadTuristica.class)
                    .setParameter("nombreActividad",nombre)
                    .getSingleResult();
            EActividadTuristica actividad = em.find(EActividadTuristica.class,eActividad.getId());
            EDepartamento eDepartamento = actividad.getEDepartamento();
            DTDepartamento dtDepartamento = new DTDepartamento(eDepartamento.getNombre(),eDepartamento.getDescripcion(),eDepartamento.getUrl());
            return new DTActividadTuristica(actividad.getId(),
                                            actividad.getNombre(),
                                            actividad.getDescripcion(),
                                            actividad.getDuracion(),
                                            actividad.getCosto(),
                                            actividad.getCiudad(),
                                            actividad.getFechaAlta()
                                                  );
           
        }catch(Exception e){
            return new DTActividadTuristica();
        }finally{
            em.close();
        }
    }
    @Override
    public DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad){
        EntityManager em = emf.createEntityManager();
        try{
          
            EActividadTuristica eActividad = em.createQuery("select p from EActividadTuristica p where p.nombre = :nombreActividad"
                    ,EActividadTuristica.class)
                    .setParameter("nombreActividad",nombreActividad)
                    .getSingleResult();
            EActividadTuristica actividad = em.find(EActividadTuristica.class,eActividad.getId());
            EDepartamento eDepartamento = actividad.getEDepartamento();
            DTDepartamento dtDepartamento = new DTDepartamento(eDepartamento.getNombre(),eDepartamento.getDescripcion(),eDepartamento.getUrl());
            return  dtDepartamento;
           
        }catch(Exception e){
            return new DTDepartamento();
        }finally{
            em.close();
        }
    }
    @Override
    public List<String> obtenerActividadesTuristicasCU10(String departamento,String paquete){
          EntityManager em = emf.createEntityManager();
      
        try{
           
             EDepartamento eDepartamento = em.createQuery("select d from EDepartamento d where d.nombre = :nombre"
                    ,EDepartamento.class)
                    .setParameter("nombre",departamento)
                    .getSingleResult();
            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete"
                    ,EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete",paquete)
                    .getSingleResult();
            
            EActividadTuristica eActividad = em.createQuery("select c from EActividadTuristica c WHERE c.eDepartamento.id = :idDepartamento"
                    ,EActividadTuristica.class)
                    .setParameter("idDepartamento",eDepartamento.getId()).getSingleResult();
            
            Query query = em.createNativeQuery("select nombre from actividadTuristica where EDEPARTAMENTO_ID = ?1 and actividadTuristica.id = ?2 AND actividadTuristica.id not in (select ACTIVIDAD_ID from paquetes join PAQUETE_ACTIVIDAD ON PAQUETE_ID = paquetes.id where paquetes.id = ?3)")
                    .setParameter(1,eDepartamento.getId())
                    .setParameter(2,eActividad.getId())
                    .setParameter(3,ePaquete.getId());        
            List<String> resultado = query.getResultList();
            return resultado;
          
        }catch(Exception e){
           List<String> resultado = null;
            return resultado;
        }finally{
           em.close();
        }
    }
    
    @Override
    public void agregarActividadPaquete(String paquete, String actividad){
        EntityManager em = emf.createEntityManager();
        
        
        try{
            em.getTransaction().begin();
          
            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete"
                    ,EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete",paquete)
                    .getSingleResult();
           
            EActividadTuristica eActividad = em.createQuery("SELECT c FROM EActividadTuristica c WHERE c.nombre = :nombreActividad"
                    ,EActividadTuristica.class)
                    .setParameter("nombreActividad",actividad)
                    .getSingleResult();
            ePaquete.getActividades().add(eActividad);
             
            em.persist(ePaquete);
            em.getTransaction().commit();
          
        }catch(Exception e){
           em.getTransaction().rollback();
           
        }finally{
            em.close();
        }
    }    
    
    private ESalidaTuristica obtenerSalidaPorNombre(String nombreSalida) {
        EntityManager em = emf.createEntityManager();
        String query = "select s from ESalidaTuristica s where s.nombre = :nombreSalida";
        
        return em.createQuery(query, ESalidaTuristica.class)
                        .setParameter("nombreSalida", nombreSalida)
                        .getSingleResult();
    }
    
    private int obtenerTotalTuristasInscriptos(String nombreSalida) {
        EntityManager em = emf.createEntityManager(); 
        
        String querySalida = "SELECT s FROM ESalidaTuristica s WHERE s.nombre = :nombreSalida";
        ESalidaTuristica eSalidaTuristica = em.createQuery(querySalida, ESalidaTuristica.class)
                                    .setParameter("nombreSalida", nombreSalida)
                                    .getSingleResult();
        
        String queryTotal = "SELECT SUM(i.cantidadTuristas) FROM EInscripcion i WHERE i.eSalidaTuristica = :salida";
        
        long total;
        try {
            total = em.createQuery(queryTotal, Long.class)
                    .setParameter("salida", eSalidaTuristica)
                    .getSingleResult();
        } catch (NullPointerException e) {
            total = 0;
        }

        return (int) total;
    }
    
    
}
