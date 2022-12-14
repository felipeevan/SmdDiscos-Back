package smdecommerce.categoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import smdecommerce.ServerConf;
import java.util.ArrayList;
import java.util.List;
import smdecommerce.abstracao.BaseCrudInterface;

/**
 *
 * Classe que implementa o padrão DAO para a entidade categoria
 */
public class CategoriaDAO implements BaseCrudInterface{

    /**
     * Método utilizado para obter uma categoria pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Categoria obter(int id) throws Exception {
        Categoria categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, descricao FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (categoria == null) {
            throw new Exception("Categoria não encontrada");
        }
        return categoria;
    }

    /**
     * Método utilizado para obter uma categoria pelo seu identificador
     *
     * @param descricao
     * @return
     * @throws Exception
     */
    public Categoria obter(String descricao) throws Exception {
        Categoria categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
  
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, descricao FROM categoria WHERE descricao = ?");
        preparedStatement.setString(1, descricao);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (categoria == null) {
            throw new Exception("Categoria não encontrada");
        }
        return categoria;
    }
    
    /**
     * Método utilizado para obter uma lista de todas as categorias
     *
     * @return
     * @throws Exception
     */
    public List<Categoria> obterCategorias() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT id, descricao FROM categoria");
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
            categorias.add(categoria);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return categorias;
    }
    
    /**
     * Método utilizado para inserir uma nova categoria
     *
     * @param id
     * @param descricao
     * @throws Exception
     */
    
    public void inserir(String descricao) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
     
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO categoria (descricao) VALUES (?)");
        preparedStatement.setString(1, descricao);
       
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir a categoria");
        }
    }
    
    /**
     * Método utilizado para atualizar uma  categoria
     * 
     * @param id
     * @param descricao
     * @throws java.lang.Exception
     */
    
    public void atualizar(Integer id, String descricao) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection;
        connection = ServerConf.getConnection();     
   
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE categoria SET descricao=? WHERE id = ?");
        preparedStatement.setString(1, descricao);
        preparedStatement.setInt(2, id);
        
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar a categoria");
        }
    }
    
    /**
     * Método utilizado para excluir uma  categoria
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
        preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível excluir a categoria");
        }
    }
}
