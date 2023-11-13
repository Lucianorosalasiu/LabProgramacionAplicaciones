package webService;

import config.ConfigManager;

/**
 *
 * @author alexis
 */
public class WebServicePublisher {
    public static String greenColorCode = "\u001B[32m";
    public static String cyanColorCode = "\u001B[36m";
    public static String resetColorCode = "\u001B[0m";

    public static void main(String[] args) {
        // Se obtiene la instancia de ConfigManager
        ConfigManager configManager = ConfigManager.getInstance();

        // Se accede a las propiedades de configuración necesarias
        String host = configManager.getConfigValue("WEB_SERVICES_HOST");
        String port = configManager.getConfigValue("WEB_SERVICES_PORT");

        System.out.println(greenColorCode + "Desplegando Web Services...\n" + resetColorCode);
        WSSalidaController servicio1 = new WSSalidaController();
        WSActividadController servicio2 = new WSActividadController();
        WSUsuarioController servicio3 = new WSUsuarioController();
        WSPaqueteController servicio4 = new WSPaqueteController();

        servicio1.publish(host, port);
        servicio2.publish(host, port);
        servicio3.publish(host, port);
        servicio4.publish(host, port);
        
        System.out.println(cyanColorCode + "\n¡Todos los WebServices han sido desplegados exitosamente!" + resetColorCode);
        System.out.println(cyanColorCode + "\nServicios escuchando en: " + resetColorCode + greenColorCode);
        System.out.println("  - " + servicio1.getAddress());
        System.out.println("  - " + servicio2.getAddress());
        System.out.println("  - " + servicio3.getAddress());
        System.out.println("  - " + servicio4.getAddress());
        System.out.println(resetColorCode);
    }
}
