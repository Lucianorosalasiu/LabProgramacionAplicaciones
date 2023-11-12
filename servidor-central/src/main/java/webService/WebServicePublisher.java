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
        
        // Luego se debería obtener el host y puerto desde el fichero .properties
        String host = "localhost";
        String port = "8889";
        String url = "http://" + host + ":" + port;

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
                "Servicios escuchando en: " + 
                resetColorCode +
                greenColorCode + 
                url + 
                resetColorCode
        );
    }
}
