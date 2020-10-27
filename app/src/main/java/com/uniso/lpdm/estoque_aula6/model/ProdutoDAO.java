package com.uniso.lpdm.estoque_aula6.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final SQLite sqLite;

    public ProdutoDAO(SQLite sqLite) {

        this.sqLite = sqLite;
    }

    public long salvarProdutoDAO(Produto produto){
        SQLiteDatabase db = sqLite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("id", produto.getId());
            values.put("nome", produto.getNome());
            values.put("quantidade", produto.getQtd());

            long idProdutoInserido;
            idProdutoInserido = db.insert("produto", null, values);

            return idProdutoInserido;

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null){
                db.close();
            }
        }
        return 0;
    }

    public List<Produto> getListaProdutoDAO(){

        List<Produto> listProduto = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produto;";

        try{

            db = this.sqLite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()){

                Produto produtoTemp = null;

                do {

                    produtoTemp = new Produto();
                    produtoTemp.setId(cursor.getLong(0));
                    produtoTemp.setNome(cursor.getString(1));
                    produtoTemp.setQtd(cursor.getInt(2));

                    listProduto.add(produtoTemp);

                }while (cursor.moveToNext());

            }


        }catch (Exception e){
            Log.d("ERRO LISTA PRODUTOS", "Erro ao retonar os produtos");
            return null;
        }finally {
            if (db != null){
                db.close();
            }
        }

        return listProduto;
    }

    public boolean excluirProdutoDAO(long pIdProduto){

        SQLiteDatabase db = null;

        try{

            db = this.sqLite.getReadableDatabase();

            db.delete(
                    "produto",
                    "id = ?",
                    new String[]{String.valueOf(pIdProduto)}
            );

        }catch (Exception e){
            Log.d("PRODUTODAO", "Não foi possivel deletar o Produto");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }

        return true;
    }


    public boolean atualizadrProdutoDAO(Produto pProduto){
        SQLiteDatabase db = null;

        try{
            db = this.sqLite.getReadableDatabase();

            ContentValues atributosProduto = new ContentValues();
            atributosProduto.put("nome", pProduto.getNome());
            atributosProduto.put("quantidade", pProduto.getQtd());

            int atualizou = db.update("produto",
                    atributosProduto,
                    "id = ?",
                    new String[]{String.valueOf(pProduto.getId())}
                    );
            if (atualizou > 0){
                return true;
            }

        }catch (Exception e){
            Log.d("PRODUTODAO", "Não foi possivel atualizar");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
        return false;
    }
}
