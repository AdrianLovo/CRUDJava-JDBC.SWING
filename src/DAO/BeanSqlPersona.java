/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.IParametro;
import com.crud.entidades.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class BeanSqlPersona extends Sql {

    @Override
    public String queryListar() {
        String query = "SELECT * FROM persona";
        return query;
    }

    @Override
    public List<IParametro> listarResultado(ResultSet rs) {
        List<IParametro> lista = new ArrayList();
        try {
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getInt("IdPersona"));
                persona.setNombre(rs.getString("Nombre"));
                persona.setApellido(rs.getString("Apellido"));
                persona.setEdad(rs.getInt("Edad"));
                persona.setGenero(rs.getString("Genero"));
                persona.setFechaNac(rs.getDate("FechaNac"));
                lista.add(persona);               
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanSqlPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public String queryAgregar() {
        String query = "INSERT INTO persona (Nombre,Apellido,Edad,Genero,FechaNac)"
                + "values (?,?,?,?,?)";
        return query;
    }

    @Override
    public void paramentroAgregar(PreparedStatement ps, IParametro parametro) {
        Persona persona = (Persona) parametro;
        try {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setInt(3, persona.getEdad());
            ps.setString(4, persona.getGenero());

            //Convercion de java.util.date a java.util.sql para poder guardar en la BD
            java.util.Date fecha = persona.getFechaNac();
            java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());

            ps.setDate(5, sqlFecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String queryEliminar() {
        String query = "DELETE FROM persona WHERE IdPersona = ?";
        return query;
    }

    @Override
    public void paramentroEliminar(PreparedStatement ps, IParametro parametro) {
        Persona persona = (Persona) parametro;
        try {
            ps.setInt(1, persona.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String queryModificar() {
        String query = "UPDATE persona SET Nombre = ?, Apellido = ?, Edad = ?, Genero = ?, FechaNac = ? WHERE IdPersona = ?"; 
        return query;
    }

    @Override
    public void paramentroModificar(PreparedStatement ps, IParametro parametro) {
        Persona persona = (Persona) parametro;
        try {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setInt(3, persona.getEdad());
            ps.setString(4, persona.getGenero());
            
            //Convercion de java.util.date a java.util.sql para poder guardar en la BD
            java.util.Date fecha = persona.getFechaNac();
            java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
            
            ps.setDate(5, sqlFecha);
            ps.setInt(6, persona.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
