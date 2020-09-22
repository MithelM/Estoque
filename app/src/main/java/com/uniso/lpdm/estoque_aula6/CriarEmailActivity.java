package com.uniso.lpdm.estoque_aula6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CriarEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_email);
    }

    public void onClickEnviar(View view){
        /*Quando o botão enviar for clicado devemos criar uma intenção para abrir qualquer
        * aplicativo/atividade que consiga processar nosso texto. Temos que montar a mensagem que
        * será enviada pegando os dados da tela, colocar na intenção e enviar de forma que
        * o android posso perguntar qual aplicativo o usuario vai querer usar para tratar aquela informação*/

    }
}