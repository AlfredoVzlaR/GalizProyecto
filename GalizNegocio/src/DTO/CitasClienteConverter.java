/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import dominio.Citas;

/**
 *
 * @author jvale
 */
public class CitasClienteConverter extends AbstractConverter<Citas, CitasClienteDTO>{

    @Override
    public Citas fromDto(CitasClienteDTO dto) {
       Citas cita = new Citas();
       cita.setId(dto.getId());
       cita.setCliente(dto.getCliente());
       cita.setFecha(dto.getFecha());
       cita.setServicios(dto.getServicios());
       return cita;
    }

    @Override
    public CitasClienteDTO fromEntity(Citas entity) {
        CitasClienteDTO citaDTO = new CitasClienteDTO();
        citaDTO.setId(entity.getId());
        citaDTO.setCliente(entity.getCliente());
        citaDTO.setFecha(entity.getFecha());
        citaDTO.setServicios(entity.getServicios());
        return citaDTO;
    }
    
}
