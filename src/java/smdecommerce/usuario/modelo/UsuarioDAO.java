package smdecommerce.usuario.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import smdecommerce.ServerConf;
import smdecommerce.abstracao.BaseCrudInterface;

/**
 *
 * Classe que implementa o padrão DAO para a entidade usuário
 */
public class UsuarioDAO implements BaseCrudInterface {

    /**
     * Método utilizado para obter um usuário pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Usuario obter(int id) throws Exception {
        Usuario usuario = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
   
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, nome, email, login, senha FROM usuario WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
    }

    /**
     * Método utilizado para obter um usuário pelo seu login
     *
     * @param login
     * @return
     * @throws Exception
     */
    public Usuario obter(String login) throws Exception {
        Usuario usuario = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
   
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, nome, email, login, senha FROM usuario WHERE login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param email
     * @param login
     * @param senha

     * @throws Exception
     */
    public void inserir(String nome, String email, String login, String senha) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
   
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO usuario (nome, email, login, senha) VALUES (?, ?, ?, ?)");
         preparedStatement.setString(1, nome);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, login);
        preparedStatement.setString(4, senha);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o usuário");
        }
    }
    
    /**
     * Método utilizado para atualizar um  usuário
     * 
     * @param nome
     * @param email
     * @param login
     * @param senha
     * @throws Exception
     */
    public void atualizar(Integer id, String nome, String email, String login, String senha) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
  
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE usuario SET nome=?, email=?, login=?, senha=? WHERE id = ?");
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, login);
        preparedStatement.setString(4, senha);
        preparedStatement.setInt(5, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar o usuário");
        }
    }
    
    /**
     * Método utilizado para exclur um  usuário
     * 
     * @param id
     * @throws Exception
     */
    @Override
    public void excluir(Integer id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
  
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM usuario WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir o usuário");
        }
    }
}
