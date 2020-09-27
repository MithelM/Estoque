package com.uniso.lpdm.estoque_aula6;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.uniso.lpdm.estoque_aula6.adapters.AdaptersListaProdutos;
import com.uniso.lpdm.estoque_aula6.model.Produto;
import com.uniso.lpdm.estoque_aula6.model.ProdutoController;
import com.uniso.lpdm.estoque_aula6.model.SQLite;

import java.util.List;

public class AtualizaProdutoActivity extends AppCompatActivity {

    private EditText edtIdProd;
    private EditText edtNomeProd;
    private EditText edtQtdProd;

    private Button btnSalvarAlteração;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_produto);


        this.edtIdProd = (EditText) findViewById(R.id.edtIdProdutoAtt);
        this.edtNomeProd = (EditText) findViewById(R.id.edtProdutoAtt);
        this.edtQtdProd = (EditText) findViewById(R.id.edtQtdAtt);

        this.btnSalvarAlteração = (Button) findViewById(R.id.btnSalvarAtt);

        Bundle bundleProd = getIntent().getExtras();

        Produto produto = new Produto();

        produto.setId(bundleProd.getLong("id_produto"));
        produto.setNome(bundleProd.getString("nome_produto"));
        produto.setQtd(bundleProd.getInt("quantidade_produto"));

        this.setProduto(produto);

        this.clickButton();

    }

    private void setProduto (Produto produto){

        this.edtIdProd.setText(String.valueOf(produto.getId()));
        this.edtNomeProd.setText(produto.getNome());
        this.edtQtdProd.setText(String.valueOf(produto.getQtd()));
    }

    private Produto getDados(){
        this.produto = new Produto();

        if(this.edtIdProd.getText().toString().isEmpty() == false){
            this.produto.setId(Long.parseLong(this.edtIdProd.getText().toString()));
        }else {
            return null;
        }
        if(this.edtNomeProd.getText().toString().isEmpty() == false){
            this.produto.setNome(this.edtNomeProd.getText().toString());
        }else {
            return null;
        }
        if(this.edtQtdProd.getText().toString().isEmpty() == false){
            int n = Integer.parseInt(this.edtQtdProd.getText().toString());

            this.produto.setQtd(n);
        }else {
            return null;
        }

        return produto;
    }

    private void clickButton(){
        this.btnSalvarAlteração.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Produto atualizar = getDados();

                if (atualizar != null){
                    ProdutoController produtoController = new ProdutoController(SQLite.getInstance(AtualizaProdutoActivity.this));
                    boolean atualizou = produtoController.atualizarProdutoController(atualizar);

                    if (atualizou == true){
                        Toast.makeText(AtualizaProdutoActivity.this, "Produto Atualizado com Sucesso!! :)", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AtualizaProdutoActivity.this, ExibirPodutoActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AtualizaProdutoActivity.this, "Produto Não pode ser Atualizado! :'(", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AtualizaProdutoActivity.this, "Todos os campos são Obrigatórios ! >:(", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}