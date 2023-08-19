/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabrica;
import interfaces.IControlador;
import controlador.Controlador;
/**
 *
 * @author lucho
 */
public class Fabrica {
    public Fabrica(){};
    public IControlador getInterface(){
        return Controlador.getInstance();
    }
}