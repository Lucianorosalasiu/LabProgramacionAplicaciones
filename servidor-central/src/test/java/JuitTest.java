/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import dataTypes.DTDepartamento;
import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import exceptions.MyException;
import java.util.Date;
import java.util.List;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lucho
 */
public class JuitTest {
    
    public JuitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
    }
    @Test
    public void Test1(){
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        
        List<String> Resultado = controlador.obtenerPaqueteNombres();
        assertEquals(true,Resultado.contains("Disfrutar Rocha"));
        
    }
    @Test
    public void Test2(){
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        
        List<String> Resultado = controlador.obtenerPaqueteNombresActividades();
        assertEquals(true,Resultado.contains("Disfrutar Rocha"));
    }
    @Test
    public void Test3(){
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        
        float Resultado = controlador.obtenerCostoActividad("Degusta");
        assertEquals(800,Resultado);
    }
    @Test
    public void Test4(){
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        
        List<DTDepartamento> Resultado = controlador.obtenerDepartamentos();
        assertEquals(19,Resultado.size());
    }
    @Test
    public void Test5() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        Date date = new Date();
        DTProveedor proveedor = new DTProveedor("jorge", "jorge", "jorge", "jorge", date, "jorge", "jorge");
        //controlador.altaProveedor(proveedor);
    }
    @Test
    public void Test6() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        Date date = new Date();
        DTTurista turista = new DTTurista("A", "AA", "jor2gito", "jorgi2to", date, "jorg2ito");
        //controlador.altaTurista(turista);
    }
   
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
