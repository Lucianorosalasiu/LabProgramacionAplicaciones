package webService;

/**
 *
 * @author alexis
 */
public class WebServicePublisher {

    public static void main(String[] args) {
        System.out.println("Desplegando Web Services...");
        WSSalidaController servicio1 = new WSSalidaController();
        WSActividadController servicio2 = new WSActividadController();
        WSUsuarioController servicio3 = new WSUsuarioController();
        WSPaqueteController servicio4 = new WSPaqueteController();

        servicio1.publish();
        servicio2.publish();
        servicio3.publish();
        servicio4.publish();
        System.out.println("¡Todos los WebServices han sido desplegados exitosamente!");
    }
}
