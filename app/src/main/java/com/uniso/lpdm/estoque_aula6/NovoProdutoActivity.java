package com.uniso.lpdm.estoque_aula6;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.uniso.lpdm.estoque_aula6.model.SQLite;
import com.uniso.lpdm.estoque_aula6.model.Produto;
import com.uniso.lpdm.estoque_aula6.model.ProdutoController;

public class NovoProdutoActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtNome;
    private EditText edtqtd;

    private Button btSalvar;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        edtId = (EditText) findViewById(R.id.edtIdProdutoNovo);
        edtNome = (EditText) findViewById(R.id.edtProdutoNovo);
        edtqtd = (EditText) findViewById(R.id.edtQtdNovo);

        btSalvar = (Button) findViewById(R.id.btnSalvarNovo);

        this.clickButton();

    }



    private Produto getDados(){
        this.produto = new Produto();

        if(this.edtId.getText().toString().isEmpty() == false){
            this.produto.setId(Long.parseLong(this.edtId.getText().toString()));
        }else {
            return null;
        }
        if(this.edtNome.getText().toString().isEmpty() == false){
            this.produto.setNome(this.edtNome.getText().toString());
        }else {
            return null;
        }
        if(this.edtqtd.getText().toString().isEmpty() == false){
            int n = Integer.parseInt(this.edtqtd.getText().toString());

            this.produto.setQtd(n);
        }else {
            return null;
        }

        return produto;

    }
/*
    public void onClickSalvar(View view){
        /*Quando o botão de salvar for clicado, esse código será executado.
        * Para esse exemplo, a unica coisa que esse código deve fazer é iniciar
        * a atividade ExibirProdutoActivity *

        Intent intent = new Intent(this, ExibirPodutoActivity.class);
        intent.setType("text/plain");

        EditText edt;

        edt = (EditText) findViewById(R.id.edtIdProdutoNovo);
        String idProdN = edtId.getText().toString();

        edt = (EditText) findViewById(R.id.edtProdutoNovo);
        String prodN = edtNome.getText().toString();

        edt = (EditText) findViewById(R.id.edtQtdNovo);
        String qtdN = edtqtd.getText().toString();

        String text = "Foram adicionadas " + qtdN +
                " unidades do produto " + prodN +
                "(" + idProdN + ")";

        intent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(intent);


    } */

    private void clickButton(){
        this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto cadastro = getDados();

                if (cadastro != null){
                    ProdutoController produtoController = new ProdutoController(SQLite.getInstance(NovoProdutoActivity.this));
                    long idProd = produtoController.salvarProduto(cadastro);

                    if (idProd > 0){
                        Toast.makeText(NovoProdutoActivity.this, "Produto Salvo com Sucesso!! :)", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NovoProdutoActivity.this, ExibirPodutoActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NovoProdutoActivity.this, "Produto Não pode ser Salvo! :'(", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NovoProdutoActivity.this, "Todos os campos são Obrigatórios ! >:(", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}