/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jvale
 */
public class Citas {
    private ObjectId id;
    private Cliente cliente;
    private List<Servicio>servicios;
    private Date fecha;

    public Citas(Cliente cliente, List<Servicio> servicios, Date fecha) {
        this.cliente = cliente;
        this.servicios = servicios;
        this.fecha = fecha;
    }

    public Citas(ObjectId id, Cliente cliente, List<Servicio> servicios, Date fecha) {
        this.id = id;
        this.cliente = cliente;
        this.servicios = servicios;
        this.fecha = fecha;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    


    public Citas() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Citas{" + "cliente=" + cliente + ", servicios=" + servicios + ", fecha=" + fecha + '}';
    }
}
