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
            e.printStackTrace();
            em.getTransaction().rollback();
            /*si bien se dio un error y no sabemos si realmente existe una coincidencia, devolvemos true
            por seguridad ya que tiene que haber un return en este bloque obligadamente*/
            return true;
        }finally{
            em.close();
        }
    }
}
