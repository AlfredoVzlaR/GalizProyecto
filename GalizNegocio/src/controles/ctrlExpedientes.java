/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import DTO.ExpedienteConverter;
import DTO.ExpedienteDTO;
import conexion.ConexionMongo;
import dominio.Expediente;
import conexion.IConexionBD;
import implementacion.ExpedientesDAO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jvale
 */
public class ctrlExpedientes {
    
    private final IConexionBD conexion;
    private final ExpedientesDAO expedienteDAO;
    private Expediente expedientes;
    private final ExpedienteConverter converter;
    
    private final List<ExpedienteDTO> listaExpedienteDTO;
    private final List<Expediente> listaExpedientes;

    public ctrlExpedientes() {
        conexion = ConexionMongo.getInstance();
        expedienteDAO = new ExpedientesDAO(conexion);
        converter = new ExpedienteConverter();
        listaExpedienteDTO = new LinkedList<>();
        listaExpedientes = new LinkedList<>();
    }
    
    public boolean agregarExpediente(ExpedienteDTO expediente){
        expedientes = converter.fromDto(expediente);
        return expedienteDAO.agregarExpediente(expedientes);
    }
    public List<ExpedienteDTO> consultarExpedientes(){
        listaExpedientes.addAll(expedienteDAO.consultarExpedientes());
        for(int i = 0;i<listaExpedientes.size();i++){
            listaExpedienteDTO.add(i, converter.fromEntity(listaExpedientes.get(i)));
        }
        return listaExpedienteDTO;
    }
    public List<ExpedienteDTO> consultarExpedientesCliente(String cliente){
        listaExpedientes.addAll(expedienteDAO.consultarExpedientesCliente(cliente));
        for(int i = 0;i<listaExpedientes.size();i++){
            listaExpedienteDTO.add(i, converter.fromEntity(listaExpedientes.get(i)));
        }
        return listaExpedienteDTO;
    }
    public ExpedienteDTO consultarExpediente(String telefono){
        expedientes = expedienteDAO.consultarExpediente(telefono);
        if(expedientes==null){
            System.out.println("No hay");
            return null;
        }
        return converter.fromEntity(expedientes);
    }
    public boolean actualizarExpediente(ExpedienteDTO expediente){
        expedientes = converter.fromDto(expediente);
        return expedienteDAO.actualizarExpediente(expedientes);
    }
    public boolean eliminarExpediente(ExpedienteDTO expediente){
        expedientes = converter.fromDto(expediente);
        return expedienteDAO.eliminarExpediente(expedientes);
    }
    
}
