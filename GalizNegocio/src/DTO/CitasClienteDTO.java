/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import dominio.Cliente;
import dominio.Servicio;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jvale
 */
public class CitasClienteDTO {
    private Date fecha;
    private Cliente cliente;
    private List<Servicio> servicios;
    private ObjectId id;

    public CitasClienteDTO() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    
    
    public CitasClienteDTO(Date fecha, Cliente cliente, List<Servicio> servicios) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.servicios = servicios;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CitasClienteDTO{" + "fecha=" + fecha + ", cliente=" + cliente + ", servicios=" + servicios + '}';
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
 
}
