package smdecommerce.venda_produto.modelo;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import smdecommerce.produto.modelo.Produto;

/*
 * Classe que representa a entidade venda_produto
 */

public class Venda_Produto extends Produto implements Serializable {
    
    private Integer id_venda;
    private Integer id_produto;
    
    private Integer quantidade_venda;
 
    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id) {
        this.id_venda = id_venda;
    }
    
    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }
    
    public Integer getQuantidadeVenda() {
        return quantidade_venda;
    }

    public void setQuantidadeVenda(Integer quantidade_venda) {
        this.quantidade_venda = quantidade_venda;
    }

}
