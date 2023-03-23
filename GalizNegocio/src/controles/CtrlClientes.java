/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import DTO.ClienteConverter;
import DTO.ClienteDTO;
import conexion.ConexionBDSQL;
import conexion.IConexionBD;
import dominio.Cliente;
import implementacion.ClientesDAO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jvale
 */
public class CtrlClientes {
    
    private IConexionBD conexion = new ConexionBDSQL();
    private ClientesDAO clientesDAO;
    private Cliente cliente;
    private ClienteConverter converter;
    private  List<ClienteDTO> listaClientesDTO;
    private  List<Cliente> listaClientes;

    public CtrlClientes() {
        clientesDAO = new ClientesDAO(conexion);
        converter = new ClienteConverter();
        listaClientes = new LinkedList<>();
        listaClientesDTO = new LinkedList<>();
    }
    
    public List<ClienteDTO> consultarClientes(){
        listaClientes.addAll(clientesDAO.consultarTodos());
        for(int i=0;i<listaClientes.size();i++){
            listaClientesDTO.add(converter.fromEntity(listaClientes.get(i)));
        }
        return listaClientesDTO;
    }
    public ClienteDTO consultarCliente(String telefono){
        cliente = clientesDAO.consultarCliente(telefono);
        return converter.fromEntity(cliente);
    }
    
}
