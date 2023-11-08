package webService.dataTypesWS;

import dataTypes.DTActividadTuristica;
import java.util.LinkedList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author alexis
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaqueteActividadTuristicaWS {

    private String nombre;
    private String descripcion;
    private int validez;
    private float descuento;
    private String fechaAlta;
    private float costo;
    private LinkedList<DTActividadTuristica> actividades;
    private byte[] imagen;

    public DTPaqueteActividadTuristicaWS(String nombre, String descripcion, int validez, float descuento, String fechaAlta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.costo = 0;
        this.actividades = new LinkedList<DTActividadTuristica>();
        this.imagen = null;
    }

    public DTPaqueteActividadTuristicaWS(String nombre, String descripcion, int validez, float descuento, String fechaAlta, float costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validez = validez;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.costo = costo;
        this.actividades = new LinkedList<DTActividadTuristica>();
        this.imagen = null;
    }

    public DTPaqueteActividadTuristicaWS(String nombre) {
        this.nombre = nombre;
    }
}
