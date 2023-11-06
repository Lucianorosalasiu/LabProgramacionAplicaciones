
package webservice;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 4.0.0-M4
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "MyException", targetNamespace = "http://webService/")
public class MyException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MyException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public MyException_Exception(String message, MyException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MyException_Exception(String message, MyException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservice.MyException
     */
    public MyException getFaultInfo() {
        return faultInfo;
    }

}
