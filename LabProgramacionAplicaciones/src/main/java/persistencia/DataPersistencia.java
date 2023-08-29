package persistencia;

import dataTypes.DTDepartamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.clases.Departamento;
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

    public void persistirDepartamento(DTDepartamento dtDepto) {
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
            String queryName = "EDepartamento.existeNombreDepartamento";
            em.getTransaction().begin();
            List<EDepartamento> resultado = em.createNamedQuery(queryName,EDepartamento.class)
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
}
