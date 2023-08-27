/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import logica.clases.Departamento;
import logica.interfaces.IControlador;
import logica.fabrica.Fabrica;
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
