package com.uniso.lpdm.estoque_aula6.model;

import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;

    public ProdutoController(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public ProdutoController(SQLite sqLite){
        produtoDAO = new ProdutoDAO(sqLite);
    }

    public long salvarProdutoController(Produto produto){
        return this.produtoDAO.salvarProdutoDAO(produto);
    }

    public List<Produto> getListaProdutoController(){
        return this.produtoDAO.getListaProdutoDAO();
    }

    public boolean excluirProdutoController (long pIdProduto){
        return this.produtoDAO.excluirProdutoDAO(pIdProduto);
    }

    public boolean atualizarProdutoController(Produto pProduto){
        return this.produtoDAO.atualizadrProdutoDAO(pProduto);
    }
}
