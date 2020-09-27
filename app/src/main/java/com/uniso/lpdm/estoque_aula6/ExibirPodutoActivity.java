package com.uniso.lpdm.estoque_aula6;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.uniso.lpdm.estoque_aula6.adapters.AdaptersListaProdutos;
import com.uniso.lpdm.estoque_aula6.model.Produto;
import com.uniso.lpdm.estoque_aula6.model.ProdutoController;
import com.uniso.lpdm.estoque_aula6.model.SQLite;

import java.util.List;

public class ExibirPodutoActivity extends AppCompatActivity {

    private ListView lvProduto;
    private List<Produto> produtoList;
    private AdaptersListaProdutos adaptersListaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_poduto);

        /*
        this.produtoList = new ArrayList<>();

        Produto p = new Produto();
        p.setNome("tecoli");
        p.setQtd(12);
        p.setId(55);

        this.produtoList.add(p);
        this.produtoList.add(p);
        this.produtoList.add(p);
        */

        final ProdutoController produtoController = new ProdutoController(SQLite.getInstance(ExibirPodutoActivity.this));
        produtoList = produtoController.getListaProdutoController();

        this.lvProduto = (ListView) findViewById(R.id.msgRecebida);

        this.adaptersListaProdutos = new AdaptersListaProdutos(this, produtoList);

        this.lvProduto.setAdapter(this.adaptersListaProdutos);

        this.lvProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                final Produto produtoSelect = (Produto) adaptersListaProdutos.getItem(position);

                AlertDialog.Builder janelaEscolha = new AlertDialog.Builder(ExibirPodutoActivity.this);

                janelaEscolha.setTitle("Escolha");
                janelaEscolha.setMessage("O que dejesa fazer com o produto selecionado ?");

                janelaEscolha.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dialogInterface.cancel();
                    }
                });

                janelaEscolha.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dialogInterface.cancel();

                        boolean excluiu = produtoController.excluirProdutoController(produtoSelect.getId());

                        if (excluiu == true){

                            adaptersListaProdutos.removerProduto(position);

                            Toast.makeText(ExibirPodutoActivity.this, "Produto excluido com sucesso !", Toast.LENGTH_SHORT);
                        }else {
                            Toast.makeText(ExibirPodutoActivity.this,"Erro ao excluir o Produto",Toast.LENGTH_SHORT);
                        }


                    }
                });

                janelaEscolha.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                        Bundle bundleProduto = new Bundle();

                        bundleProduto.putLong("id_produto", produtoSelect.getId());
                        bundleProduto.putString("nome_produto", produtoSelect.getNome());
                        bundleProduto.putInt("quantidade_produto", produtoSelect.getQtd());

                        Intent intent = new Intent(ExibirPodutoActivity.this, AtualizaProdutoActivity.class);
                        intent.putExtras(bundleProduto);

                        startActivity(intent);

                    }
                });


                janelaEscolha.create().show();

            }
        });
    }
}