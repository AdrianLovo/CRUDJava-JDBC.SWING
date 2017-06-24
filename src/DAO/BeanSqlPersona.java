/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.IParametro;
import com.crud.entidades.Persona;
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
public class BeanSqlPersona extends Sql{

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
                persona.setId(rs.getInt("Edad"));
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
    
}
