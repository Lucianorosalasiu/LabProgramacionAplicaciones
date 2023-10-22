/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTCategoria;
import dataTypes.DTCompraPaquete;
import dataTypes.DTDepartamento;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTProveedor;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;
import exceptions.MyException;
import java.util.ArrayList;
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
    @Test
    public void Test7() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        
        DTActividadTuristica resultado = controlador.obtenerActividadTuristica("Degusta");
        assertEquals("Degusta",resultado.getNombre());
    }
    @Test
    public void Test8() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTActividadTuristica resultado = controlador.obtenerActividadTuristicaNull(Long.valueOf(70));
        assertEquals("Degusta",resultado.getNombre());
    }
    @Test
    public void Test9() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTActividadTuristica resultado = controlador.obtenerFotoActividadTuristicaID("Degusta");
        assertEquals("Degusta",resultado.getNombre());
    }
    @Test
    public void Test10() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesDeProveedor(13);
        //me fijo si en la lista de resultados existe al menos una actividad que coincida con una de las actividades correspondientes al proovedor 13 (mercedes)
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Tour por Colonia del Sacramento")));
    }
    @Test
    public void Test11() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesSinConfirmar();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Bus turistico Colonia")));
    }
    @Test
    public void Test12() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesTuristicas("Rocha");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Degusta")));
    }
     @Test
    public void Test13() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesTuristicas("Rocha",Long.valueOf(11));
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Degusta")));
    }
    @Test
    public void Test14() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesTuristicasConId("Rocha");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Degusta")));
    }
    @Test
    public void Test15() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesTuristicasPorCategoria("Gastronomia");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Degusta")));
    }
    @Test
    public void Test16() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<String> resultados = controlador.obtenerActividadesTuristicasCU10("Rocha", "Disfrutar Rocha");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(false,resultados.contains("Teatro con Sabores"));
    }
    @Test
    public void Test17() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTCategoria> resultados = controlador.obtenerCategorias();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTCategoria -> DTCategoria.getNombre()
                .equals("Gastronomia")));
    }
    @Test
    public void Test18() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTDepartamento> resultados = controlador.obtenerDepartamentos();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTDepartamento -> DTDepartamento.getNombre()
                .equals("Rocha")));
    }
    @Test
    public void Test19() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTPaqueteActividadTuristica resultado = controlador.obtenerPaquete("Disfrutar Rocha");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Disfrutar Rocha",resultado.getNombre());
    }
    @Test
    public void Test20() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTPaqueteActividadTuristica resultado = controlador.obtenerPaqueteCosto("Disfrutar Rocha");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(1040.0,resultado.getCosto());
    }
    @Test
    public void Test21() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTUsuario> resultados = controlador.obtenerUsuarios();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTUsuario -> DTUsuario.getName()
                .equals("Mercedes")));
    }
    @Test
    public void Test22() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTUsuario resultado = controlador.obtenerUsuario("meche");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Mercedes", resultado.getName());
    }
    @Test
    public void Test23() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTUsuario resultado = controlador.obtenerUsuarioAlternativo("meche");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Mercedes", resultado.getName());
    }
    @Test
    public void Test24() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        DTUsuario resultado = controlador.obtenerUsuarioAlternativo("meche");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Mercedes", resultado.getName());
    }
    @Test
    public void Test25() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTSalidaTuristica> resultados = controlador.obtenerSalidasDeProveedor(Long.valueOf(13));
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTSalidaTuristica -> DTSalidaTuristica.getNombre()
                .equals("Almuerzo 1")));
    }
    @Test
    public void Test26() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
       
        List<DTSalidaTuristica> resultados = controlador.obtenerSalidasDeTurista(Long.valueOf(7));
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTSalidaTuristica -> DTSalidaTuristica.getNombre()
                .equals("Teatro con Sabores 2")));
    }
    @Test 
    public void Test27() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        controlador.existeActividadTuristica("Actividad No Existente"); //estoy testeando que no de error, con la excepcion
        List<DTProveedor> resultados = controlador.obtenerProveedores();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTProveedor -> DTProveedor.getName()
                .equals("Mercedes")));
    }
    @Test 
    public void Test28() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        controlador.existeDepartamento("Departamento No Existente"); //estoy testeando que no de error, con la excepcion
        List<DTPaqueteActividadTuristica> resultados = controlador.obtenerPaquetesRelacionadosCompletos(Long.valueOf(70));
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTPaqueteActividadTuristica -> DTPaqueteActividadTuristica.getNombre()
                .equals("Disfrutar Rocha")));
    }
    @Test
    public void Test29() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        
        List<DTPaqueteActividadTuristica> resultados = controlador.obtenerPaquetesRelacionados("Degusta");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTPaqueteActividadTuristica -> DTPaqueteActividadTuristica.getNombre()
                .equals("Disfrutar Rocha")));
    }
    @Test
    public void Test30() throws MyException{
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        
        List<DTSalidaTuristica> resultados = controlador.obtenerSalidasTuristicas("Degusta");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTSalidaTuristica -> DTSalidaTuristica.getNombre()
                .equals("Degusta Agosto")));
    }
    @Test
    public void Test31() throws MyException{
       Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        
        DTSalidaTuristica resultado = controlador.obtenerSalidaTuristica("Degusta Agosto");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Degusta Agosto",resultado.getNombre());
    }
    @Test
    public void Test32() throws MyException{
       Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        
        List<DTTurista> resultados = controlador.obtenerTuristas();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTTurista -> DTTurista.getName()
                .equals("Bob")));
    }
    @Test
    public void Test33() throws MyException{
       Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        
        List<DTActividadTuristica> resultados = controlador.obtenerActividadesRelacionadas("Disfrutar Rocha");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTActividadTuristica -> DTActividadTuristica.getNombre()
                .equals("Degusta")));
    }
    @Test
    public void Test34() throws MyException{
       Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        
        DTDepartamento resultado = controlador.CU11obtenerDepartamentoActividad("Degusta");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Rocha",resultado.getNombre());
    }
    @Test
    public void Test35() throws MyException{
       Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
       
        controlador.validarActividad(null,null);
        DTDepartamento resultado = controlador.CU11obtenerDepartamentoActividad("Degusta");
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals("Rocha",resultado.getNombre());
    }
    @Test
    public void Test36() throws MyException{
       Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        DTCompraPaquete test = new DTCompraPaquete(null, null, 0, null, null, 0);
        
        controlador.validarActividad(null,null);
        List <DTPaqueteActividadTuristica> resultados = controlador.obtenerPaquetes();
        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
        assertEquals(true,resultados.stream().anyMatch(DTPaqueteActividadTuristica -> DTPaqueteActividadTuristica.getNombre()
                .equals("Disfrutar Rocha")));
    }
    @Test
    public void Test37() throws MyException{
       Fabrica fabrica = new Fabrica();
       IControlador controlador = fabrica.getInterface();
        
        
       DTProveedor test = new DTProveedor(); 
       
      controlador.altaProveedor(test);


        //con la linea rara en el campo de actual estoy viendo si la lista contiene al menos un objeto y de ese objeto tiro el equals con lo que quiero checkar
       
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
