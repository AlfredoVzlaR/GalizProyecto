/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Alfredo Valenzuela
 */
public interface IConexionBD {
    MongoDatabase ConexionBD();
    MongoDatabase crearConexion();
}