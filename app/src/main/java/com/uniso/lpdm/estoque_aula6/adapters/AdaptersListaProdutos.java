package com.uniso.lpdm.estoque_aula6.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.uniso.lpdm.estoque_aula6.R;
import com.uniso.lpdm.estoque_aula6.model.Produto;

import java.util.List;

public class AdaptersListaProdutos extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;


    public AdaptersListaProdutos(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;

    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void removerProduto(int position){
        this.produtoList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View v1 = View.inflate(this.context, R.layout.layout_produto, null);



        TextView idProd = (TextView) v1.findViewById(R.id.idProd);
        TextView idNome = (TextView) v1.findViewById(R.id.idNome);
        TextView idQuant = (TextView) v1.findViewById(R.id.idQuant);



        idProd.setText(Long.toString(this.produtoList.get(position).getId()));
        idNome.setText(this.produtoList.get(position).getNome());
        idQuant.setText(String.valueOf(this.produtoList.get(position).getQtd()));


        return v1;
    }
}
