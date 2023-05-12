/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import DTO.ServicioConverter;
import DTO.ServicioDTO;
import conexion.ConexionBDSQL;
import conexion.IConexionBD;
import dominio.Servicio;
import implementacion.ServiciosDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvale
 */
public class CtrlServicios {
    
    private final IConexionBD conexion;
    private ServiciosDAO daoServicios;
    private List<ServicioDTO>listaServiciosDTO;
    List<Servicio>listaServicios;
    ServicioConverter converter;

    public CtrlServicios() {
        conexion = new ConexionBDSQL();
        daoServicios = new ServiciosDAO(conexion);
    }
    
    public List<ServicioDTO> consultarServicios(){
        converter = new ServicioConverter();
        listaServicios = new ArrayList<>();
        listaServiciosDTO = new ArrayList<>();
        listaServicios.addAll(daoServicios.consultarTodos());
        for(int i =0;i<listaServicios.size();i++){
            listaServiciosDTO.add(converter.fromEntity(listaServicios.get(i)));
        }
        return listaServiciosDTO;
    }
    public ServicioDTO consultarServicio(String nombre){
        converter = new ServicioConverter();
        ServicioDTO dto = new ServicioDTO();
        dto = converter.fromEntity(daoServicios.consultarServicio(nombre));
        return dto;
    }
}
