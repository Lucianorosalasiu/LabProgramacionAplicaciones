/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webService;

import jakarta.xml.ws.Endpoint;

/*
 * @author diego
 */
public class Publisher {
    
    public static void main(String args[]) {
        Endpoint.publish("http://localhost:8889/wsSalidaController", new WSSalidaController());
        Endpoint.publish("http://localhost:8889/wsActividadController", new WSActividadController());
    }
}
