/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import interfaces.IControlador;
import java.util.Map;
import clases.Departamento;
import java.util.HashMap;
/**
 *
 * @author lucho
 */
public class Controlador implements IControlador{
    private Controlador(){}
    private static Controlador instance = null;
    private Map<String,Departamento> departamentos = new HashMap<>();
    
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
    public void altaDepartamento(String nombre, String descripcion, String url){

        if(departamentos.containsKey(nombre)){
            System.out.println("repetido");
        }else{
            System.out.println("no repetido");
            Departamento nuevoDepartamento = new Departamento(nombre,descripcion,url);
            departamentos.put(nombre, nuevoDepartamento);
        }
        /*Si se repite nombre
        devolver excepcion
        si no
        crear departamento y guardarlo en una arraylist en el controlador*/
    }
}
