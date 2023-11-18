package webService.dataTypesWS;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author alexis
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTActividadTuristicaWS {
    private Long id;
    private String nombre;
    private String descripcion;
    private String duracion;
    private float costo;
    private String ciudad;
    private LocalDate fechaAlta;
    private DTDepartamentoWS departamento;
    private ArrayList<Long> categorias;
    private String categoriasString;
    
    public DTActividadTuristicaWS(String nombre, String descripcion, String duracion,
            float costo, String ciudad, LocalDate fechaAlta, DTDepartamentoWS departamento){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.departamento = departamento;
    }
    
    /*constructor sin el departamento*/
    public DTActividadTuristicaWS(String nombre, String descripcion, String duracion,
            float costo, String ciudad, LocalDate fechaAlta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
    }
    
    public DTActividadTuristicaWS(Long id, String nombre, String descripcion, String duracion,
            float costo, String ciudad, LocalDate fechaAlta){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
    }
    
    public DTActividadTuristicaWS(String nombre, String descripcion, String duracion,
            float costo, String ciudad, LocalDate fechaAlta, String categoriasString){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.categoriasString = categoriasString;
    }
    
    public DTActividadTuristicaWS(Long id,String nombre, String descripcion, String duracion,
            float costo, String ciudad, LocalDate fechaAlta, String categoriasString){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.costo = costo;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.categoriasString = categoriasString;
    }
    
    public DTActividadTuristicaWS(String nombre){
        this.nombre = nombre;
    }
    
    public DTActividadTuristicaWS(Long id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getCostoToString(){
        Float costoString = this.costo;
        return costoString.toString();
    }
    
    public DTActividadTuristicaWS(Long id, String nombre, String descripcion,
            String ciudad, Float costo){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.costo = costo;
    }
    
}
