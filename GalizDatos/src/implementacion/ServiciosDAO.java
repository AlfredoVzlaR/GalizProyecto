/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementacion;

import conexion.IConexionBD;
import dominio.Cliente;
import dominio.Servicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jvale
 */
public class ServiciosDAO {
    private final IConexionBD conexion;
    
    public ServiciosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    
    public List<Servicio> consultarTodos() {
        EntityManager em = this.conexion.crearEntityManager();
        String jpql = "SELECT c from Servicio c";
        TypedQuery <Servicio> query = em.createQuery(jpql,Servicio.class);
        return query.getResultList();
    }
   
    public Servicio consultarServicio(String nombre) {
       EntityManager em = this.conexion.crearEntityManager();
       CriteriaBuilder builder = em.getCriteriaBuilder();
       CriteriaQuery criteria = builder.createQuery(Servicio.class);
       Root <Servicio> entidad = criteria.from(Servicio.class);
       
       if (nombre != null){
           criteria.where(builder.like(entidad.get("nombre"),  "%" + nombre + "%"));
           TypedQuery query = em.createQuery(criteria);
           return (Servicio) query.getSingleResult();
       } else {
           System.out.println("No existe el servicio");
           return null;
       }
    }
    public boolean agregarServicio(Servicio servicio) {
        try {
            EntityManager em = this.conexion.crearEntityManager();
            em.getTransaction().begin();
            em.persist(servicio);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException ex) {
            System.err.print("No se pudo agregar el servicio");
            ex.printStackTrace();
            return false;
        }
    }
    
}
