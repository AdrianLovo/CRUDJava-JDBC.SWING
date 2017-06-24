/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interfaces.IOperacion;
import Interfaces.IParametro;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */
public abstract class Sql implements IOperacion{

    public abstract String queryListar();
    public abstract List<IParametro> listarResultado(ResultSet rs);
    
    
    
    public Connection cargarConexion() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrud?user=root&password=root12");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
    
    
    @Override
    public List<IParametro> listar() {
        PreparedStatement ps = null;
        List<IParametro> lista = new ArrayList();
        ResultSet rs = null;
        try{
            ps = cargarConexion().prepareStatement(queryListar());
            rs = ps.executeQuery();
            lista = listarResultado(rs);            
        }catch(Exception e){
            e.printStackTrace();
        }        
        return lista;        
    }

     
    @Override
    public void Elimina(IParametro parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Agregar(IParametro parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modificar(IParametro parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
