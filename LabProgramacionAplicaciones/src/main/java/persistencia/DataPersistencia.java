package persistencia;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.clases.Departamento;
import exceptions.MyException;
import persistencia.entidades.EActividadTuristica;
import persistencia.entidades.EDepartamento;
import persistencia.entidades.EPaqueteActividadTuristica;
import persistencia.entidades.ESalidaTuristica;

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
            throw new MyException("ERROR! Algo salio mal consultando la base de datos. ");
        }finally{
            em.close();
        }   
        
        if(!resultado.isEmpty()){   
            throw new MyException("ERROR! Ya existe una actividad turistica con ese nombre. ");
        }
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento){
        EntityManager em = emf.createEntityManager();   
        
        EDepartamento eDepartamento = em.find(EDepartamento.class,idDepartamento);
        
        EActividadTuristica nuevaActividad = new EActividadTuristica(dtActividadTuristica.getNombre(),
        dtActividadTuristica.getDescripcion(),dtActividadTuristica.getDuracion(),
                dtActividadTuristica.getCosto(),dtActividadTuristica.getCiudad(),
                dtActividadTuristica.getFechaAlta(),eDepartamento);
        
        try{
            em.getTransaction().begin();
            em.persist(nuevaActividad);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
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
                throw new MyException("ERROR! Algo salio mal consultando la base de datos. ");
        }finally{
            em.close();
        }
        
        if(!resultado.isEmpty()){
            throw new MyException("ERROR! Ya existe un departamento con ese nombre en el sistema.");
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
            throw new MyException("ERROR! Algo salio mal consultando la base de datos. ");
        }finally{
            em.close();
        }   
        
        if(!resultado.isEmpty()){   
            throw new MyException("ERROR! Ya existe una salida turistica con el nombre ingresado. ");
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
    
    public DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida){
        EntityManager em = emf.createEntityManager();
        
        try{
            String query = "select s from ESalidaTuristica s where s.nombre = :nombreSalida";
            ESalidaTuristica eSalidaTuristica = em.createQuery(query, ESalidaTuristica.class)
                            .setParameter("nombreSalida", nombreSalida)
                            .getSingleResult();
            
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
}
