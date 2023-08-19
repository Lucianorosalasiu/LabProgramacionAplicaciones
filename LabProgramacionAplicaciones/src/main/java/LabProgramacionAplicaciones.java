/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import interfaces.IControlador;
import fabrica.Fabrica;
/**
 *
 * @author lucho
 */
public class LabProgramacionAplicaciones {

    public static void main(String[] args) {
        
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        controlador.test();
    }
}
