package Utils;

import Models.Fornecedor;
import Models.NotaFiscal;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Produto;
import Models.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * classe utilitária para popular os diversos modelos da aplicação
 * @author welison
 */
public class FillModel {
    
    /**
     * Popula o fornecedor corrente com o resultado da consulta
     * @param fornecedor o fornecedor a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillFornecedor(Fornecedor fornecedor, ResultSet rs) {
        try {
            fornecedor.setId(rs.getInt("Id"));
            fornecedor.setCnpj(rs.getString("Cnpj"));
            fornecedor.setNome(rs.getString("Nome"));
            fornecedor.setStatus(rs.getString("Status"));
            fornecedor.setTelefone(rs.getString("Telefone"));
            fornecedor.setCreated(rs.getString("Created"));
        } catch (SQLException error) {
            throw new RuntimeException("FornecedorDAO.fillFornecedor: " + error);
        }
    }
    
    /**
     * Popula a nota fiscal corrente com o resultado da consulta
     * @param notaFiscal a nota fiscal a ser populada
     * @param rs o ResultSet da consulta
     */
    public void fillNotas(NotaFiscal notaFiscal, ResultSet rs) {
        try {
            notaFiscal.setId(rs.getInt("nId"));
            notaFiscal.setNumero(rs.getLong("nNumero"));
            notaFiscal.setSerie(rs.getInt("nSerie"));
            notaFiscal.setData(rs.getString("nData"));
            notaFiscal.setValor(rs.getFloat("nValor"));
            notaFiscal.setStatus(rs.getString("nStatus"));
            notaFiscal.setCreated(rs.getString("nCreated"));
            // cria o fornecedor da nota
            Fornecedor  fornecedor = new Fornecedor();
            fornecedor.setId(rs.getInt("fId"));
            fornecedor.setCnpj(rs.getString("fCnpj"));
            fornecedor.setNome(rs.getString("fNome"));
            fornecedor.setStatus(rs.getString("fStatus"));
            fornecedor.setTelefone(rs.getString("fTelefone"));
            fornecedor.setCreated(rs.getString("fCreated"));
            notaFiscal.setFornecedor(fornecedor);
        } catch(SQLException error) {
            throw new RuntimeException("NotaFiscalDAO.fillUser: " + error);
        }
    }
    
    /**
     * Popula o usuario corrente com o resultado da consulta
     * @param usuario o usuario a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillUsuario(Usuario usuario, ResultSet rs) {
        try {
            usuario.setId(rs.getInt("usuarios.Id"));
            usuario.setCpf(rs.getString("usuarios.Cpf"));
            usuario.setNome(rs.getString("usuarios.Nome"));
            usuario.setEmail(rs.getString("usuarios.Email"));
            usuario.setDataNascimento(rs.getString("usuarios.DataNascimento"));
            usuario.setCelular(rs.getString("usuarios.Celular"));
            usuario.setTelefone(rs.getString("usuarios.Telefone"));
            usuario.setLogin(rs.getString("usuarios.Login"));
            usuario.setSenha(rs.getString("usuarios.Senha"));
            usuario.setSetor(rs.getString("usuarios.Setor"));
            usuario.setCreated(rs.getString("usuarios.Created"));
            usuario.setPermissao(rs.getString("usuarios.Permissao"));
            usuario.setStatus(rs.getString("usuarios.Status"));
            usuario.setSexo(rs.getString("usuarios.Sexo"));
        } catch(SQLException error) {
            throw new RuntimeException("PedidoDAO.fillUser: " + error);
        }
    }
    
    /**
     * Popula o produto corrente com o resultado da consulta
     * @param produto o produto a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillProduto(Produto produto, ResultSet rs) {
        try {
            produto.setId(rs.getInt("produtos.Id"));
            produto.setNome(rs.getString("produtos.Nome"));
            produto.setDescricao(rs.getString("produtos.Descricao"));
            produto.setStatus(rs.getString("produtos.Status"));
            produto.setUnidade(rs.getString("produtos.Unidade"));
            produto.setTotal(rs.getInt("estoque.Total"));
            produto.setCreated(rs.getString("produtos.Created"));
        } catch(SQLException error) {
            throw new RuntimeException("PedidoDAO.fillProduto: " + error);
        }
    }
    
    /**
     * Popula o pedido corrente com o resultado da consulta
     * @param pedido o pedido a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillPedido(Pedido pedido, ResultSet rs) {
        try {
            pedido.setId(rs.getInt("pedidos.Id"));
            pedido.setStatus(rs.getString("pedidos.Status"));
            pedido.setCreated(rs.getString("pedidos.Created"));
            pedido.setAlmoxarifeId(rs.getInt("pedidos.AlmoxarifeId"));
        } catch(SQLException error) {
            throw new RuntimeException("PedidoDAO.fillProduto: " + error);
        }
    }
    
    /**
     * Popula o pedidoProduto corrente com o resultado da consulta
     * @param pedidoProduto o pedidoProduto
     * @param pedido o pedido
     * @param produto o produto
     * @param rs o ResultSet da consulta
     */
    public void fillPedidoProduto(PedidoProduto pedidoProduto, Pedido pedido, Produto produto, ResultSet rs) {
        try {
            pedidoProduto.setId(rs.getInt("pedidosprodutos.Id"));
            pedidoProduto.setQuantidade(rs.getInt("pedidosprodutos.QuantidadeSolicitada"));
            pedidoProduto.setQuantidadeAprovada(rs.getInt("pedidosprodutos.QuantidadeAprovada"));
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
        } catch(SQLException error) {
            throw new RuntimeException("PedidoDAO.fillPedidoProduto: " + error);
        }
    }
}
