/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementacion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionMongo;
import dominio.Expediente;
import java.util.LinkedList;
import org.bson.Document;
import conexion.IConexionBD;
import java.util.List;

/**
 *
 * @author jvale
 */
public class ExpedientesDAO{
    
    private IConexionBD conexion = ConexionMongo.getInstance();
    private final MongoDatabase baseDatos;

    public ExpedientesDAO(IConexionBD conexion) {
        this.conexion = conexion;
        this.baseDatos = this.conexion.crearConexion();
    }
    
    private MongoCollection getCollection(){
        return this.baseDatos.getCollection("Expedientes",Expediente.class);
    }

    public List<Expediente> consultarExpedientes(){
        MongoCollection<Expediente> coleccion = this.getCollection();
        List<Expediente> listaExpedientes = new LinkedList<>();
        coleccion.find().into(listaExpedientes);
        return listaExpedientes;
    }
    public boolean agregarExpediente(Expediente expediente) {
        MongoCollection<Expediente> coleccion = this.getCollection();
        coleccion.insertOne(expediente);
        return true;
    }

    public boolean actualizarExpediente(Expediente expediente) {
        MongoCollection<Expediente> coleccion = this.getCollection();
        coleccion.updateOne(Filters.eq("cliente",expediente.getCliente()),new Document().append("$set",new Document().append("antecedentesPersonales",expediente.getAntecedentesPersonales()
        ).append("cosmeticosUso",expediente.getCosmeticosUso()).append("diagnosticoPiel",expediente.getDiagnosticoPiel()).append("cliente",expediente.getCliente()).append("antecedentesPatologicos", expediente.getAntecedentesPatologicos())));
        return true;
    }
    public boolean eliminarExpediente(Expediente expediente) {
        MongoCollection<Expediente> coleccion = this.getCollection();
        List<Expediente> lista = new LinkedList<>();
        coleccion.find(new Document().append("cliente", new Document().append("$eq", expediente.getCliente()))).into(lista);
        coleccion.deleteOne(new Document().append("cliente", lista.get(0).getCliente()));
        return coleccion.find(new Document().append("cliente", new Document().append("$eq", expediente.getCliente()))).into(lista)!=null;
    }
    public List<Expediente> consultarExpedientesCliente(String nombreCliente){
        MongoCollection<Expediente> coleccion = this.getCollection();
        List<Expediente> listaExpedientes = new LinkedList<>();
        coleccion.find(new Document().append("cliente", new Document().append("$eq", nombreCliente))).into(listaExpedientes);
        return listaExpedientes;
    }

    public Expediente consultarExpediente(String telefono) {
        MongoCollection<Expediente> coleccion = this.getCollection();
        List<Expediente> lista = new LinkedList<>();
        coleccion.find(new Document().append("telefonoCliente", new Document().append("$eq", telefono))).into(lista);
        if(lista.isEmpty()){
            return null;
        }
        return lista.get(0);
    }

}
