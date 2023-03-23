/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import dominio.Expediente;

/**
 *
 * @author jvale
 */
public class ExpedienteConverter extends AbstractConverter<Expediente, ExpedienteDTO>{

    @Override
    public Expediente fromDto(ExpedienteDTO dto) {
        try{
            Expediente expediente = new Expediente();
            expediente.setCliente(dto.getNombreCliente());
            expediente.setAntecedentesPatologicos(dto.getAntecedentesPatologicos());
            expediente.setAntecedentesPersonales(dto.getAntecedentesPersonales());
            expediente.setCosmeticosUso(dto.getCosmeticosUso());
            expediente.setDiagnosticoPiel(dto.getDiagnosticoPiel());
            return expediente;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public ExpedienteDTO fromEntity(Expediente entity) {
        ExpedienteDTO dto = new ExpedienteDTO();
        
        dto.setNombreCliente(entity.getCliente());
        dto.setAntecedentesPatologicos(entity.getAntecedentesPatologicos());
        dto.setAntecedentesPersonales(entity.getAntecedentesPersonales());
        dto.setCosmeticosUso(entity.getCosmeticosUso());
        dto.setDiagnosticoPiel(entity.getDiagnosticoPiel());
        return dto;
    }
    
}
