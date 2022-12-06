package smdecommerce.venda.controle;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import smdecommerce.venda.modelo.Venda;
import smdecommerce.venda.modelo.VendaDAO;
import smdecommerce.venda_produto.modelo.Venda_ProdutoDAO;


/**
 *
 * 
 * Classe que implementa a ação de inserir uma nova venda/pedido de um usuário do tipo cliente
 */
public class NovaVendaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        
        /* entrada */
        int id = data.get("id_usuario").getAsInt();
        JsonArray items = data.get("items").getAsJsonArray();
        ArrayList json = new Gson().fromJson(items, ArrayList.class);

        int pagamento = Integer.parseInt(data.get("pagamento").getAsString());
        int entrega = Integer.parseInt(data.get("entrega").getAsString());
        
        /* processamento */
        VendaDAO vendaDAO = new VendaDAO();
        Venda_ProdutoDAO vendaProdutoDAO = new Venda_ProdutoDAO();
       
        boolean sucesso = false;
        String mensagem = null;
        Venda venda = null;
        try {
            vendaDAO.inserir(id, pagamento, entrega);
            venda = vendaDAO.obterUltima(id);
            for (int i = 0; i < json.size(); i++) {
                LinkedTreeMap item = (LinkedTreeMap) json.get(i);
                vendaProdutoDAO.inserir(venda.getId(), ((Double) item.get("id")).intValue(), 
                        ((Double) item.get("quantidade_carrinho")).intValue());
            }
            
            sucesso = true;
            mensagem = "Venda inserida com sucesso";
            response.setStatus(200);
        } catch (Exception ex) {
            response.setStatus(400);
            sucesso = false;
            mensagem = ex.getMessage(); 
        }
        try (PrintWriter out = response.getWriter()) {
            
            JSONObject myResponse = new JSONObject();
            Gson gson = new Gson();
            myResponse.put("sucesso", sucesso);
            myResponse.put("data", gson.toJson(venda));
            myResponse.put("mensagem", sucesso ? "Produto encontrado com sucesso" : mensagem);
            out.print(myResponse);
            out.flush();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

}
