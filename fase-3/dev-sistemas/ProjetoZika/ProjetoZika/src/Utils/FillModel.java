package Utils;

import Models.Fornecedor;
import Models.GraphModel;
import Models.NotaFiscal;
import Models.NotaFiscalProduto;
import Models.Pedido;
import Models.PedidoProduto;
import Models.Produto;
import Models.RelatorioNota;
import Models.RelatorioPedido;
import Models.RelatorioProduto;
import Models.RelatorioUsuario;
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
            Methods.getLogger().error("FillModel.fillFornecedor: " + error);
            throw new RuntimeException("FillModel.fillFornecedor: " + error);
        }
    }
    
    /**
     * Popula a nota fiscal corrente com o resultado da consulta
     * @param notaFiscal a nota fiscal a ser populada
     * @param rs o ResultSet da consulta
     */
    public void fillNota(NotaFiscal notaFiscal, ResultSet rs) {
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
            Methods.getLogger().error("FillModel.fillNota: " + error);
            throw new RuntimeException("FillModel.fillNota: " + error);
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
            Methods.getLogger().error("FillModel.fillUser: " + error);
            throw new RuntimeException("FillModel.fillUser: " + error);
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
            Methods.getLogger().error("FillModel.fillProduto: " + error);
            throw new RuntimeException("FillModel.fillProduto: " + error);
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
            pedido.setDone(rs.getString("pedidos.Done"));
            pedido.setAproved(rs.getString("pedidos.Aproved"));
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillPedido: " + error);
            throw new RuntimeException("FillModel.fillPedido: " + error);
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
            Methods.getLogger().error("FillModel.fillPedidoProduto: " + error);
            throw new RuntimeException("FillModel.fillPedidoProduto: " + error);
        }
    }
    
    /**
     * Popula o objeto do gráfico com o resultado da consulta
     * @param graph o objeto a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillGraph(GraphModel graph, ResultSet rs) {
        try {
            graph.setQuantidade(rs.getInt("total"));
            graph.setMonth(rs.getInt("month"));
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillGraph: " + error);
            throw new RuntimeException("FillModel.fillGraph: " + error);
        }
    }
    
    /**
     * Popula o relatório do produto com o resultado da consulta
     * @param item o relatório do produto a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillRelatorioProduto(RelatorioProduto item, ResultSet rs) {
        try {
            item.setCodigo(rs.getInt("codigo"));
            item.setProduto(rs.getString("produto"));
            item.setEntrada(rs.getInt("entrada"));
            item.setSaida(rs.getInt("saida"));
            item.setEstoqueAtual(rs.getInt("estoqueAtual"));
            item.setFornecedores(rs.getString("fornecedores"));
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillRelatorioProduto: " + error);
            throw new RuntimeException("FillModel.fillRelatorioProduto: " + error);
        }
    }
    
    /**
     * Popula o relatório do nota fiscal com o resultado da consulta
     * @param item o relatório do nota fiscal a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillRelatorioNota(RelatorioNota item, ResultSet rs) {
        try {
            item.setCodigo(rs.getInt("codigo"));
            item.setEntrada(rs.getString("data"));
            item.setFornecedore(rs.getString("fornecedor"));
            item.setProdutos(rs.getString("produtos"));
            item.setNumero(rs.getLong("numero"));
            item.setValor(rs.getDouble("valor"));
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillRelatorioNota: " + error);
            throw new RuntimeException("FillModel.fillRelatorioNota: " + error);
        }
    }
    
    /**
     * Popula o relatório de usuários com o resultado da consulta
     * @param item o relatório de usuários a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillRelatorioUsuario(RelatorioUsuario item, ResultSet rs) {
        try {
            item.setCodigo(rs.getInt("codigo"));
            item.setNome(rs.getString("nome"));
            item.setData(rs.getString("data"));
            item.setSetor(rs.getString("setor"));
            item.setPermissao(rs.getString("permissao"));
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillRelatorioUsuario: " + error);
            throw new RuntimeException("FillModel.fillRelatorioUsuario: " + error);
        }
    }
    
    /**
     * Popula o relatório do pedido com o resultado da consulta
     * @param item o relatório do pedido a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillRelatorioPedido(RelatorioPedido item, ResultSet rs) {
        try {
            item.setCodigo(rs.getInt("codigo"));
            item.setSoliciante(rs.getString("solicitante"));
            item.setAprovador(rs.getString("aprovador"));
            item.setStatus(rs.getString("status"));
            item.setTotal(rs.getInt("total"));
            item.setData(rs.getString("data"));
            item.setProdutos(rs.getString("produtos"));
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillRelatorioPedido: " + error);
            throw new RuntimeException("FillModel.fillRelatorioPedido: " + error);
        }
    }
    
    /**
     * Popula o produto da nota fiscal com o resultado da consulta
     * @param nfp o produto da nota fiscal a ser populado
     * @param rs o ResultSet da consulta
     */
    public void fillNotaFiscalProduto(NotaFiscalProduto nfp, ResultSet rs) {
        try {
            nfp.setQuantidade(rs.getInt("Quantidade"));
            nfp.setValor(rs.getFloat("Valor"));
            nfp.setCreated(rs.getString("notasfiscaisprodutos.Created"));
            // o produto da nota
            Produto p = new Produto();
            p.setId(rs.getInt("Id"));
            p.setNome(rs.getString("Nome"));
            p.setDescricao(rs.getString("Descricao"));
            p.setStatus(rs.getString("Status"));
            p.setUnidade(rs.getString("Unidade"));
            p.setCreated(rs.getString("produtos.Created"));
            nfp.setProduto(p);
        } catch(SQLException error) {
            Methods.getLogger().error("FillModel.fillNotaFiscalProduto: " + error);
            throw new RuntimeException("FillModel.fillNotaFiscalProduto: " + error);
        }
    }
}
