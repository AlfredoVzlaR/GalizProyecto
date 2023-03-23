/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import dominio.Cliente;

/**
 *
 * @author jvale
 */
public class ClienteConverter extends AbstractConverter<Cliente,ClienteDTO> {

    @Override
    public Cliente fromDto(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }

    @Override
    public ClienteDTO fromEntity(Cliente entity) {
        ClienteDTO clienteDTO = new ClienteDTO(entity.getNombre(), entity.getTelefono());
        return clienteDTO;
    }
    
}
