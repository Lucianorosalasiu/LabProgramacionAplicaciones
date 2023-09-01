package persistencia;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.clases.Departamento;
import logica.clases.MyException;
import persistencia.entidades.EActividadTuristica;
import persistencia.entidades.EDepartamento;
import persistencia.entidades.EPaqueteActividadTuristica;

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

    public void existeDepartamento(String nombreDepartamento)throws MyException{
        EntityManager em = emf.createEntityManager();
        String consulta = "select d from EDepartamento d where d.nombre = :nombreDepartamento";
        
        List<EDepartamento> resultado = em.createQuery(consulta,EDepartamento.class)
                .setParameter("nombreDepartamento",nombreDepartamento).getResultList();

        em.close();
        
        if(!resultado.isEmpty()){
            throw new MyException("ERROR! Ya existe un departamento con ese nombre en el sistema. ");
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
        List<EDepartamento> EDepartamentos = new LinkedList<>();
        List<DTDepartamento> DTDepartamentos = new LinkedList<>();  
        
        try{
            em.getTransaction().begin();
            EDepartamentos = em.createNamedQuery("EDepartamento.obtenerDepartamentos",EDepartamento.class).getResultList();
            em.getTransaction().commit();
            
            /**
             * una vez obtenida la lista de EDepartamentos los parseo a DTDepartamentos
             * para respetar la arquitectura de capas y no pasar objetos
             */
            for(EDepartamento ed: EDepartamentos){
                DTDepartamento DTDepartamento = new DTDepartamento(ed.getId(),ed.getNombre(),ed.getDescripcion(),ed.getUrl());
                DTDepartamentos.add(DTDepartamento);
            }
            
            return DTDepartamentos;
        }catch(Exception e){
            em.getTransaction().rollback();
            return DTDepartamentos;
        }finally{
            em.close();
        }
    }
    
    @Override
    public void existeActividadTuristica(String nombre)throws MyException{
        EntityManager em = emf.createEntityManager();    
        String consulta = "select a from EActividadTuristica a where a.nombre = :nombreActividad";
            
        List<EActividadTuristica> resultado = em.createQuery(consulta,EActividadTuristica.class)
                .setParameter("nombreActividad",nombre)
                .getResultList();
            
        em.close();
        
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
}
