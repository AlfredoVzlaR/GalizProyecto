package conexion;

import com.mongodb.client.MongoDatabase;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alfredo Valenzuela
 */
public class ConexionBDSQL implements IConexionBD{

    public ConexionBDSQL() {
    }

    @Override
    public EntityManager crearEntityManager() throws IllegalStateException {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("GalizDatosPU");
        EntityManager em = emFactory.createEntityManager();
        return em;
    }
    
    @Override
    public MongoDatabase crearConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MongoDatabase ConexionBD() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
