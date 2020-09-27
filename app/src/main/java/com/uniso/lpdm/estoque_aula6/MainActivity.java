package com.uniso.lpdm.estoque_aula6;

import android.widget.Button;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.uniso.lpdm.estoque_aula6.model.SQLite;

public class MainActivity extends Activity {

    private Button btnExibir;
    private Button btnAtualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLite sqLite = SQLite.getInstance(this);

        /*
        Produto produto1 = new Produto();
        produto1.setId(122);
        produto1.setQtd(182);
        produto1.setNome("Macarone");

        ProdutoController produtoController = new ProdutoController(sqLite);
        long resul = produtoController.salvarProduto(produto1);

        System.out.println("Resultado" + resul);
         */

    }

    public void onClickCadastrarEmail(View view){
        /*Aqui inserimos o código que será utilizado quando o botão para cadastrar
        * email da atividade for clicado*/

        Intent intent = new Intent(this, CriarEmailActivity.class);
        startActivity(intent);
    }

    public void onClickNovo(View view){
        /*Aqui inserimos o código que será utilizado quando o botão para cadastrar
         * novo produto da atividade for clicado*/

        Intent intent = new Intent(this, NovoProdutoActivity.class);
        startActivity(intent);

    }


    public void onClickExibir(View view){
        Intent intent = new Intent(this, ExibirPodutoActivity.class);
        startActivity(intent);
    }


}