
package smdecommerce.administrador.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import smdecommerce.ServerConf;
import smdecommerce.abstracao.BaseCrudInterface;

/**
 *
 * @author vitor
 */
public class AdministradorDAO implements BaseCrudInterface{
    
    /**
     * Método utilizado para obter um administrador pelo seu identificador
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    @Override
    public Administrador obter(int id_usuario) throws Exception {
        Administrador adm = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
   
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT u.* FROM administrador a, usuario u WHERE a.id_usuario = ? and a.id_usuario = u.id");
        preparedStatement.setInt(1, id_usuario);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            adm = new Administrador();
            adm.setId(resultSet.getInt("id"));
            adm.setNome(resultSet.getString("nome"));
            adm.setEmail(resultSet.getString("email"));
            adm.setLogin(resultSet.getString("login"));
            adm.setSenha(resultSet.getString("senha"));
        } 
        resultSet.close();
        preparedStatement.close();
        connection.close();
        /*if (adm == null) {
            throw new Exception("Administrador não encontrado");
        }*/
        return adm;
    }
    
    /**
     * Método utilizado para inserir um novo administrador
     *
     * @param id_usuario
     * @throws Exception
     */
    public void inserir(Integer id_usuario) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
   
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO administrador (id_usuario) VALUES (?)");
        preparedStatement.setInt(1, id_usuario);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o administrador");
        }
    }
    
    /**
     * Método utilizado para excluir um  administrador
     * 
     * @param id_usuario
     * @throws Exception
     */
    @Override
    public void excluir(Integer id_usuario) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM administrador WHERE id_usuario = ?");
        preparedStatement.setInt(1, id_usuario);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir o administrador");
        }
    }
}
