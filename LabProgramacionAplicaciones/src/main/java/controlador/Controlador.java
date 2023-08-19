/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import interfaces.IControlador;
/**
 *
 * @author lucho
 */
public class Controlador implements IControlador{
    private Controlador(){}
    private static Controlador instance = null;
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
}
