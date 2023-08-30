package persistencia;

import dataTypes.DTActividadTuristica;
import dataTypes.DTDepartamento;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.clases.Departamento;
import logica.clases.MyException;
import persistencia.entidades.EActividadTuristica;
import persistencia.entidades.EDepartamento;

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

    public boolean existeDepartamento(String nombreDepartamento){
        EntityManager em = emf.createEntityManager();
        try{
            /**
            @queryName tiene que hacer referencia a una query que ya hayamos creado, en este caso
            * la implementacion se encuentra en EDepartamento
            * 
            * @resultado se iguala el resultado de la consulta a una lista para saber si hubo coincidencias
            * 
            * @setParameter se le indica el nombre del atributo a reemplazar y el valor que va a llevar ese atributo 
            * una vez se haga la consulta
            */
            //String queryName = "EDepartamento.existeNombreDepartamento";
            em.getTransaction().begin();
            List<EDepartamento> resultado = em.createNamedQuery("EDepartamento.existeNombreDepartamento",EDepartamento.class)
                    .setParameter("nombreDepartamento",nombreDepartamento)
                    .getResultList();
            em.getTransaction().commit();
            
            if(!resultado.isEmpty()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            /**
             * comento la opcion para printear la traza del error ya que puede contener informacion sensible que no
             * queremos que se vea en produccion, en caso de querer debugear se descomenta nuevamente.
            e.printStackTrace();
            */
            em.getTransaction().rollback();
            /*si bien se dio un error y no sabemos si realmente existe una coincidencia, devolvemos true
            por seguridad ya que tiene que haber un return en este bloque obligadamente*/
            return true;
        }finally{
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
                DTDepartamento DTDepartamento = new DTDepartamento(ed.getNombre(),ed.getDescripcion(),ed.getUrl());
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
        String consultaSQL = "select a from EActividadTuristica a where a.nombre = :nombreActividad";
            
        List<EActividadTuristica> resultado = em.createQuery(consultaSQL,EActividadTuristica.class)
                .setParameter("nombreActividad",nombre)
                .getResultList();
            
        em.close();
        
        if(!resultado.isEmpty()){   
            throw new MyException("ERROR! Ya existe una actividad turistica con ese nombre. ");
        }
    }
    
    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, DTDepartamento dtDepartamento){
        EntityManager em = emf.createEntityManager();   
        
//        EDepartamento eDepartamento = new EDepartamento(dtDepartamento.getNombre(),dtDepartamento.getDescripcion(),
//        dtDepartamento.getURL());
        //Long pk = 151L;
        EDepartamento encontrar = em.find(EDepartamento.class,151L);
        
        EActividadTuristica nuevaActividad = new EActividadTuristica(dtActividadTuristica.getNombre(),
        dtActividadTuristica.getDescripcion(),dtActividadTuristica.getDuracion(),
                dtActividadTuristica.getCosto(),dtActividadTuristica.getCiudad(),
                dtActividadTuristica.getFechaAlta(),encontrar);
        
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
}
