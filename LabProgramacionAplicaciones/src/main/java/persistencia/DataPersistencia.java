package persistencia;

import dataTypes.DTDepartamento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    /*
    ActividadesDeportivas ap = new ActividadesDeportivas();
    ap.setNombre(act.getNombre());
    ap.setDescripcion(act.getDescripcion());
    ap.setDuracion(act.getDuracionMinutos());
    ap.setCosto(act.getCosto());
    ap.setFechaAlta(act.getFechaRegistro().toCalendar());
    ap.setInstitucion(ins.getNombre());
    ap.setEstado(act.getEstado().name());
    ap.setProfesor(act.getCreador().getNickname());
    em.getTransaction().begin();
    em.persist(ap);
    em.getTransaction().commit();
    */
}
