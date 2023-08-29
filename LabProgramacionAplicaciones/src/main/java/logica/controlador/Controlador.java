/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controlador;
import dataTypes.DTDepartamento;
import logica.interfaces.IControlador;
import logica.clases.Departamento;
import logica.clases.MyException;
import java.util.ArrayList;
import persistencia.DataPersistencia;
import persistencia.FDataPersistencia;
import persistencia.IDataPersistencia;
/**
 *
 * @author lucho
 */
public class Controlador implements IControlador{
    private static Controlador instance = null;
    private static ArrayList<Departamento> departamentos;
    private FDataPersistencia fabPersistencia = new FDataPersistencia();
    private IDataPersistencia dataPersistencia = fabPersistencia.getInterface();
    
    private Controlador(){
        departamentos = new ArrayList<Departamento>();
    }
    
    public static Controlador getInstance(){
        if(instance == null){
            instance = new Controlador();
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
        /* Departamento nuevoDepartamento = new Departamento(departamento.getNombre(),
                departamento.getDescripcion(),departamento.getURL());
        */
        
        //departamentos.add(nuevoDepartamento); //se guarda en local
        //aca va la persistencia
        
        dataPersistencia.persistirDepartamento(departamento);
    }
    
//    public void changeFavoritos(Socio user) throws UsuarioNoExisteException {
//        if (favoritos.contains(user)) 
//            favoritos.remove(user);
//        else 
//            favoritos.add(user); 
//	}
}
