package smdecommerce.venda.controle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.util.List;
import smdecommerce.venda.modelo.Venda;
import smdecommerce.venda.modelo.VendaDAO;
import smdecommerce.venda_produto.modelo.Venda_Produto;
import smdecommerce.venda_produto.modelo.Venda_ProdutoDAO;

/**
 *
 * @author nicol
 */
public class ListarVendasClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        
        /* Entrada */
        int id = data.get("id").getAsInt();
        
        /* Processamento */
        VendaDAO vendaDAO = new VendaDAO();
        Venda_ProdutoDAO vendaProdutoDAO = new Venda_ProdutoDAO();
        
        List<Venda> vendas = null;
        
        boolean sucesso     = false;
        String mensagem     = null;
        
        try{
            vendas = vendaDAO.obterVendascliente(id);
            for(int x = 0; x< vendas.size(); x++){
                Venda v = vendas.get(x);
                List<Venda_Produto> produto = vendaProdutoDAO.obterProdutos(v.getId());
                v.setProdutos(produto);
            }
            response.setStatus(200);
            sucesso = true;
        } catch (Exception ex) {
            response.setStatus(400);
            sucesso = false;
            mensagem = ex.getMessage();
        }
        
        /* Saída */
        try (PrintWriter out = response.getWriter()) {
            JSONObject myResponse = new JSONObject();
            Gson gson = new Gson();
            myResponse.put("sucesso", sucesso);
            myResponse.put("data", gson.toJson(vendas));
            //myResponse.put("mensagem", sucesso ? "Venda listado  com sucesso" : mensagem);
            out.print(myResponse);
            out.flush();
        }catch(Exception ex){
            System.out.println(ex);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}
