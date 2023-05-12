/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import dominio.Servicio;
import implementacion.ServiciosDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvale
 */
public class ServicioConverter extends AbstractConverter<Servicio, ServicioDTO>{

    @Override
    public Servicio fromDto(ServicioDTO dto) {
        Servicio servicio = new Servicio();
        servicio.setNombre(dto.getNombre());
        servicio.setCosto(dto.getCosto());
        servicio.setDescripcion(dto.getDescripcion());
        return servicio;
    }
    
    public List<Servicio> convertirLista(List<ServicioDTO> listaSer){
        
        List<Servicio> lista = new ArrayList<>();
        
        for(int i=0;i<listaSer.size();i++){
            lista.add(fromDto(listaSer.get(i)));
        }
        return lista;
    }

    @Override
    public ServicioDTO fromEntity(Servicio entity) {
        ServicioDTO dto = new ServicioDTO();
        dto.setNombre(entity.getNombre());
        dto.setCosto(entity.getCosto());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }
    
}
