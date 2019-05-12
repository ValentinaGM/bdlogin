/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.Persona;

/**
 *
 * @author valen
 */
public class PersonaDao {

    Conexion con;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public PersonaDao() {
        this.con = new Conexion();
        connection = con.getConexion();
    }

    public boolean insertar(Persona persona) {
        boolean respuesta = false;
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO contactos (nombre, edad, rut)"
                    + "VALUES ('" + persona.getNombre() + "' , '" + persona.getEdad() + "' , '" + persona.getRut() + " ); ";
            statement.executeUpdate(sql);
            respuesta = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return respuesta;
        }
    }

    public boolean eliminar(Persona persona) {
        boolean respuesta = false;

        try {
            statement = connection.createStatement();
            String sql = "DELETE FROM contactos "
                    + "WHERE rut = '" + persona.getRut() + "'";
            statement.executeUpdate(sql);
            respuesta = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return respuesta;
        }

    }

    public boolean actualizar(Persona persona) {
        boolean respuesta = false;
        try {
            statement = connection.createStatement();
            String sql = "UPDATE contactos "
                    + "SET nombre='" + persona.getNombre() + "' , edad ='" + persona.getEdad() + "' , rut='" + persona.getRut()
                    + " WHERE rut= '" + persona.getRut() + "'";
            statement.executeUpdate(sql);
            respuesta = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return respuesta;
        }
    }

    public Persona buscar(String rut) {
        Persona respuesta = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM contactos WHERE rut = '" + rut + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                respuesta = new Persona();
                respuesta.setNombre(resultSet.getString("nombre"));
                respuesta.setEdad(Integer.parseInt(resultSet.getString("edad")));
                respuesta.setRut(resultSet.getString("rut"));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return respuesta;
        }
    }

    public ArrayList<Persona> buscarTodos() {
        ArrayList<Persona> respuesta = new ArrayList();
        try {

            statement = connection.createStatement();
            String sql = "SELECT * FROM contactos";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Persona persona = new Persona();
                persona.setNombre(resultSet.getString("nombre"));
                persona.setEdad(Integer.parseInt(resultSet.getString("edad")));
                persona.setRut(resultSet.getString("rut"));
                respuesta.add(persona);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            return respuesta;
        }
    }

}
