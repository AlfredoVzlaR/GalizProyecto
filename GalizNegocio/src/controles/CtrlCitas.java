/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import DTO.CitasClienteConverter;
import DTO.CitasClienteDTO;
import conexion.ConexionBDSQL;
import conexion.ConexionMongo;
import conexion.IConexionBD;
import dominio.Citas;
import dominio.Cliente;
import implementacion.CitasDAO;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author jvale
 */
public class CtrlCitas {

    private final IConexionBD conexion;
    private final CitasDAO citaDAO;

    private Citas cita;
    private final CitasClienteConverter converter;

    private final List<CitasClienteDTO> listaCitasDTO;
    private List<Citas> listaCitas;

    public CtrlCitas() {
        this.conexion = ConexionMongo.getInstance();
        this.citaDAO = new CitasDAO(conexion);
        cita = new Citas();
        this.converter = new CitasClienteConverter();
        this.listaCitasDTO = new LinkedList<>();
        this.listaCitas = new LinkedList<>();
    }

    private boolean horarioTrabajo(Date date) {
        if(date.getHours()<9||date.getHours()>19){
            return false;
        }
        return true;
    }

    private boolean disponibilidad(Date date,Date date2){
        if(date.getHours()==date2.getHours()){
            return false;
        }
        return true;
    }
    

    private boolean validarCita(CitasClienteDTO dto) throws ParseException {
        
        if(horarioTrabajo(dto.getFecha())==false){
            JOptionPane.showMessageDialog(null, "Fuera del horario de trabajo");
            return false;
        }
        listaCitas.addAll(citaDAO.consultarCitas());
        for (int i = 0; i < listaCitas.size(); i++) {
            listaCitasDTO.add(converter.fromEntity(listaCitas.get(i)));
        }
        for (int i = 0; i < listaCitasDTO.size(); i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String fecha1 = dateFormat.format(listaCitasDTO.get(i).getFecha());
            String fecha2 = dateFormat.format(dto.getFecha());
            LocalDateTime fechaObj1 = LocalDateTime.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime fechaObj2 = LocalDateTime.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Duration diff = Duration.between(fechaObj1, fechaObj2);
            long horas = Math.abs(diff.toMinutes());
            if(fecha2.equals(fecha1)){
                if(horas<=120){
                    JOptionPane.showMessageDialog(null, "Sin disponibilidad de horario");
                    return false;
                }
            }else{
                if(horas<=120){
                    JOptionPane.showMessageDialog(null, "Sin disponibilidad de horario");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean agregarCita(CitasClienteDTO dto) throws ParseException {
        if (validarCita(dto)) {
            return citaDAO.agregarCita(converter.fromDto(dto));
        } else {
            return false;
        }
    }
    public List<CitasClienteDTO> consultarCitas(){
        List<Citas> citas = new ArrayList<>();
        citas.addAll(citaDAO.consultarCitas());
        List<CitasClienteDTO> citas2 = new ArrayList<>();
        for(int i=0;i<citas.size();i++){
            citas2.add(converter.fromEntity(citas.get(i)));
        }
        return citas2;
    }
    public List<CitasClienteDTO> consultarCitasHoy(){
        List<Citas> citas = new ArrayList<>();
        citas.addAll(citaDAO.consultarCitasHoy());
        List<CitasClienteDTO> citas2 = new ArrayList<>();
        for(int i=0;i<citas.size();i++){
            citas2.add(converter.fromEntity(citas.get(i)));
        }
        return citas2;
    }
    public List<CitasClienteDTO> consultarTodas(){
        List<Citas> citas = new ArrayList<>();
        citas.addAll(citaDAO.consultarTodas());
        List<CitasClienteDTO> citas2 = new ArrayList<>();
        for(int i=0;i<citas.size();i++){
            citas2.add(converter.fromEntity(citas.get(i)));
        }
        return citas2;
    }
    
    public List<CitasClienteDTO> consultarCitasFecha(Date date){
        List<Citas> citas = new ArrayList<>();
        citas.addAll(citaDAO.consultarCitasPorFecha(date));
        List<CitasClienteDTO> citas2 = new ArrayList<>();
        for(int i=0;i<citas.size();i++){
            citas2.add(converter.fromEntity(citas.get(i)));
        }
        return citas2;
    }
    
    public CitasClienteDTO consultarCita(ObjectId id){
        Citas cita = citaDAO.consultarCita(id);
        CitasClienteDTO cita2 = converter.fromEntity(cita);
        return cita2;
    }
    
    public boolean cancelarCita(Citas cita){
        return citaDAO.eliminarCita(cita.getId());
    }
}
