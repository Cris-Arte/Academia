package br.com.academia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CriarBancoDados extends SQLiteOpenHelper {
    public CriarBancoDados(Context context) {
        super(context, "academia.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarTabela = "CREATE TABLE clientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "telefone TEXT," +
                "plano TEXT)";
        db.execSQL(criarTabela);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS clientes");
        onCreate(db);
    }
    // CREATE - Criar cliente
    public long adicionarCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("plano", cliente.getPlano());
        long id = db.insert("clientes", null, values);
        db.close();
        return id;
    }
    // READ - Buscar todos os clientes
    public List<Cliente> getAllClientesAsObjects(){
        List<Cliente> clientes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("clientes", null, null, null, null, null, "nome ASC");

        if (cursor.moveToFirst()){
            do{
                Cliente cliente = new Cliente(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                clientes.add(cliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return clientes;
    }

    // UPDATE - Atualizar cliente
    public int atualizarCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("plano", cliente.getPlano());
        return db.update("clientes", values, "id = ?",
                new String[]{String.valueOf(cliente.getId())});
    }

    // DELETE - Excluir cliente
    public void excluirCliente(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("clientes", "id = " + id, null);
        db.close();
    }

}
