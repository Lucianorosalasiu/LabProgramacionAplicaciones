package webService;

/**
 *
 * @author alexis
 */
public class WebServicePublisher {
    public static String greenColorCode = "\u001B[32m";
    public static String cyanColorCode = "\u001B[36m";
    public static String resetColorCode = "\u001B[0m";

    public static void main(String[] args) {
        System.out.println(greenColorCode + "Desplegando Web Services...\n" + resetColorCode);
        
        // Luego se debería utilizar el puerto extraído del fichero .properties
        String port = "8889";
        
        WSSalidaController servicio1 = new WSSalidaController();
        WSActividadController servicio2 = new WSActividadController();
        WSUsuarioController servicio3 = new WSUsuarioController();
        WSPaqueteController servicio4 = new WSPaqueteController();

        servicio1.publish();
        servicio2.publish();
        servicio3.publish();
        servicio4.publish();
        System.out.println(cyanColorCode + "\n¡Todos los WebServices han sido desplegados exitosamente!" + resetColorCode);
        System.out.println(cyanColorCode + 
                "Servicios escuchando en puerto: " + 
                resetColorCode + 
                greenColorCode + 
                port + 
                resetColorCode
        );
    }
}
