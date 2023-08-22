/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import interfaces.IControlador;
import clases.Departamento;
import clases.MyException;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
    public void altaDepartamento(String nombre, String descripcion, String url){
        Departamento nuevoDepartamento = new Departamento(nombre, descripcion, url);
        departamentos.add(nuevoDepartamento);
    }
}
