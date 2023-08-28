/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controlador;
import DataTypes.DTDepartamento;
import logica.interfaces.IControlador;
import logica.clases.Departamento;
import logica.clases.MyException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author lucho
 */
public class Controlador implements IControlador{
    private Controlador(){}
    private static Controlador instance = null;
    private static ArrayList<Departamento> departamentos;
    
    public static Controlador getInstance(){
        if(instance == null){
            instance = new Controlador();
            departamentos = new ArrayList<Departamento>();
        }
        return instance;
    }
    
    @Override
    public void test(){
        System.out.println("Hola estoy fabricando!");
    }
    
    @Override
    public boolean existeDepartamento(String nombre){
        /*for(int i = 0; i < departamentos.size();i++){
            if(nombre.equals(departamentos.get(i).getNombre())){
                return true;
            }
        }*/
        for(Departamento elemento: departamentos){
            if(nombre.equals(elemento.getNombre())){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void altaDepartamento(DTDepartamento departamento){
        Departamento nuevoDepartamento = new Departamento(departamento.getNombre(),
                departamento.getDescripcion(),departamento.getURL());
        
        departamentos.add(nuevoDepartamento); //se guarda en local
        //aca va la persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("puConexionBD");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(nuevoDepartamento);
        em.getTransaction().commit();
        
    }
}
