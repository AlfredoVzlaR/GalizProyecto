
import conexion.ConexionMongo;
import conexion.IConexionBD;
import dominio.Citas;
import implementacion.CitasDAO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author jvale
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Citas>citas = new LinkedList<>();
        
        IConexionBD conexion = ConexionMongo.getInstance();
        
        CitasDAO citasDAO = new CitasDAO(conexion);
        
        citas.addAll(citasDAO.consultarCitasHoy());
        
        for (Citas cita : citas) {
            System.out.println(cita.toString());
        }
    }
    
}
