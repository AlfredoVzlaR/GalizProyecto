/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementacion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import conexion.ConexionMongo;
import conexion.IConexionBD;
import dominio.Citas;
import dominio.Cliente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jvale
 */
public class CitasDAO {
    private IConexionBD conexion = ConexionMongo.getInstance();
    private final MongoDatabase baseDatos;

    public CitasDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.crearConexion();
    }
    private MongoCollection getCollection(){
        return this.baseDatos.getCollection("Citas",Citas.class);
    }
    
    public boolean agregarCita(Citas cita) {
        MongoCollection<Citas> coleccion = this.getCollection();
        coleccion.insertOne(cita);
        return true;
    }
    public List<Citas> consultarCitas() {
        MongoCollection <Citas> coleccion = this.getCollection();
        List<Citas> listPublicaciones = new LinkedList();
        coleccion.find().into(listPublicaciones);
        return listPublicaciones;
    }
    public Citas consultarCita(ObjectId id) {
    MongoCollection<Citas> coleccion = this.getCollection();
    Citas cita = coleccion.find(eq(id)).first();
    return cita;
}

    
    public boolean eliminarCita(Citas cita) {
        MongoCollection<Citas> coleccion = this.getCollection();
        List<Citas> lista = new LinkedList<>();
        coleccion.find(new Document().append("fecha", new Document().append("$eq", cita.getFecha()))).into(lista);
        coleccion.deleteOne(new Document().append("fecha", lista.get(0).getFecha()));
        return coleccion.find(new Document().append("fecha", new Document().append("$eq", cita.getFecha()))).into(lista)!=null;
    }

}
