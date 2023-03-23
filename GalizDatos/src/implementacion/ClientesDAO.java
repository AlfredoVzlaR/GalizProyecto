/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementacion;

import conexion.IConexionBD;
import dominio.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jvale
 */
public class ClientesDAO {
    
    private final IConexionBD conexion;
    
    public ClientesDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    
    public List<Cliente> consultarTodos() {
        EntityManager em = this.conexion.crearEntityManager();
        String jpql = "SELECT c from Cliente c";
        TypedQuery <Cliente> query = em.createQuery(jpql,Cliente.class);
        return query.getResultList();
    }
   
    public Cliente consultarCliente(String telefono) {
       EntityManager em = this.conexion.crearEntityManager();
       CriteriaBuilder builder = em.getCriteriaBuilder();
       CriteriaQuery criteria = builder.createQuery(Cliente.class);
       Root <Cliente> entidad = criteria.from(Cliente.class);
       
       if (telefono != null){
           criteria.where(builder.like(entidad.get("telefono"),  "%" + telefono + "%"));
           TypedQuery query = em.createQuery(criteria);
           return (Cliente) query.getSingleResult();
       } else {
           System.out.println("No existe el producto");
           return null;
       }
    }
    
}
