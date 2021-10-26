
package dao;

import com.dd.gestionclientes.models.Clientes;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDao {
    
    
    public Connection conectar(){
         String baseDeDatos ="java";
            String usuario = "root";
            String password = "";
            String host =  "localhost";
            String puerto = "3306";
            String driver = "com.mysql.jdbc.Driver";
            String conexionUrl ="jdbc:mysql://"+host+":"+puerto+"/"+baseDeDatos+"?useSSl=false";
            
            Connection conexion = null;
            try {
                Class.forName(driver);
                conexion = DriverManager.getConnection(conexionUrl, usuario, password);
            
            } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            return conexion;
    }
    
    
    
    
    
    
    
    
    
    public void agregar(Clientes cliente){
        

            try {
                Connection conexion = conectar();
                String sql="INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `telefono`, `email`) VALUES (NULL, "
                    + "'"+cliente.getNombre()+"', '"+cliente.getApellido()+"', "
                    + "'"+cliente.getTelefono()+"', '"+cliente.getEmail()+"');";
                Statement statement = conexion.createStatement();
                statement.execute(sql);
            
            } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    
    
     public void actualizar(Clientes cliente){
        

            try {
                Connection conexion = conectar();
                String sql="UPDATE `clientes` SET `nombre` = '"+cliente.getNombre()+"', `apellido` = '"+cliente.getApellido()+"', `telefono` = '"+cliente.getTelefono()+"', `email` = '"+cliente.getEmail()+"' WHERE `clientes`.`id` = "+cliente.getId()+"  ;";
                Statement statement = conexion.createStatement();
                statement.execute(sql);
            
            } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    public List<Clientes> listar(){
        
            
            List<Clientes> listado = new ArrayList<>();
            try {
                Connection conexion = conectar(); 
                String sql="SELECT * FROM `clientes`;";
                Statement statement = conexion.createStatement();
                ResultSet resultado=  statement.executeQuery(sql);
                while(resultado.next()){
                    Clientes cliente = new Clientes();
                    cliente.setId(resultado.getString("id"));
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido(resultado.getString("apellido"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    cliente.setEmail(resultado.getString("email"));
                    listado.add(cliente);
                }
            
            } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
            return listado;
            
    }
    
    
    
    public void eliminar(String id){
        
            try {
                Connection conexion = conectar();
                String sql="DELETE from clientes where id ="+id;
                Statement statement = conexion.createStatement();
                statement.execute(sql);
            } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public void guardar(Clientes cliente) {
        if(StringUtils.isEmptyOrWhitespaceOnly(cliente.getId())){
            agregar(cliente);
        }else{
            actualizar(cliente);
        }
    }
    
    
    
}
