/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.abstracao;

/**
 *
 * @author Felipe
 */
public interface BaseCrudInterface {
    public Object obter(int key) throws Exception;
    //Não consegui implementar o inserir nem o deletar por conta de ter parametros diferentes
    //public void inserir(Object... o) throws Exception;
    //public void atualizar() throws Exception;
    public void excluir(Integer key) throws Exception;
}
