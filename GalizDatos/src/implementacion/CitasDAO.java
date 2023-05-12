/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementacion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import conexion.ConexionMongo;
import conexion.IConexionBD;
import dominio.Citas;
import dominio.Cliente;
import dominio.Servicio;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
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

    private MongoCollection getCollection() {
        return this.baseDatos.getCollection("Citas", Citas.class);
    }

    public boolean agregarCita(Citas cita) {
        MongoCollection<Citas> coleccion = this.getCollection();
        coleccion.insertOne(cita);
        return true;
    }

    public List<Citas> consultarCitas() {
        MongoCollection<Citas> coleccion = this.getCollection();
        List<Citas> listaCitas = new LinkedList<>();

        // Obtener la fecha de hoy en el formato adecuado para MongoDB
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate hoy = ahora.toLocalDate();
        Instant instant = hoy.atStartOfDay().toInstant(ZoneOffset.UTC);
        Date fechaHoy = Date.from(instant);

        // Filtrar los documentos para obtener solo las citas a partir de hoy
        Bson filtro = Filters.gte("fecha", fechaHoy);

        // Buscar los documentos que corresponden a las citas a partir de hoy
        coleccion.find(filtro).into(listaCitas);

        return listaCitas;
    }

    public List<Citas> consultarCitasHoy() {
        MongoCollection<Document> coleccion = this.getCollection();

        // Obtener la fecha de hoy en el formato adecuado para MongoDB
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate hoy = ahora.toLocalDate();
        Instant instant = hoy.atStartOfDay().toInstant(ZoneOffset.UTC);
        Date fechaHoyInicio = Date.from(instant);
        Date fechaHoyFin = Date.from(instant.plus(Duration.ofDays(1)).minus(Duration.ofSeconds(1)));

        // Filtrar los documentos para obtener solo las citas de hoy
        Bson filtro = Filters.and(
                Filters.gte("fecha", fechaHoyInicio),
                Filters.lte("fecha", fechaHoyFin)
        );

        // Buscar los documentos que corresponden al día de hoy
        List<Citas> citasHoy = new ArrayList<>();
        coleccion.find(filtro, Citas.class).into(citasHoy);

        return citasHoy;
    }
    public List<Citas> consultarCitasPorFecha(Date fecha) {
    MongoCollection<Document> coleccion = this.getCollection();

    // Obtener el rango de fechas para el día seleccionado
    Instant instantInicio = fecha.toInstant().truncatedTo(ChronoUnit.DAYS);
    Instant instantFin = instantInicio.plus(1, ChronoUnit.DAYS).minus(1, ChronoUnit.SECONDS);

    // Filtrar los documentos para obtener solo las citas del día seleccionado
    Bson filtro = Filters.and(
            Filters.gte("fecha", Date.from(instantInicio)),
            Filters.lte("fecha", Date.from(instantFin))
    );

    // Buscar los documentos que corresponden al día seleccionado
    List<Citas> citas = new ArrayList<>();
    coleccion.find(filtro, Citas.class).into(citas);

    return citas;
}


    public List<Citas> consultarTodas() {
        MongoCollection<Citas> coleccion = this.getCollection();
        List<Citas> listaCitas = new LinkedList();
        coleccion.find().into(listaCitas);
        return listaCitas;
    }

    public Citas consultarCita(ObjectId id) {
        MongoCollection<Citas> coleccion = this.getCollection();
        Citas cita = coleccion.find(eq(id)).first();
        return cita;
    }

    public boolean eliminarCita(ObjectId id) {
        MongoCollection<Citas> coleccion = this.getCollection();
        DeleteResult resultado = coleccion.deleteOne(Filters.eq(id));
        return resultado.getDeletedCount() > 0;
    }

}
