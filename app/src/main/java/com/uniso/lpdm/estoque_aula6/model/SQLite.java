package com.uniso.lpdm.estoque_aula6.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    private static SQLite INSTACIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "produtos_app";

    public SQLite(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static SQLite getInstance(Context context){
        if (INSTACIA_CONEXAO == null){
            INSTACIA_CONEXAO = new SQLite(context);
        }
        return INSTACIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTableProduto =
                "CREATE TABLE IF NOT EXISTS produto" +
                "(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "quantidade INTEGER "+
                ")";

        sqLiteDatabase.execSQL(sqlTableProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
