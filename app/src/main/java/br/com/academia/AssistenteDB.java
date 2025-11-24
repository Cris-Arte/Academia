package br.com.academia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AssistenteDB extends SQLiteOpenHelper {
    public AssistenteDB(Context context) {
        super(context, "academia.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //comando para criar a tabela clientes, com 4 colunas: id, nome, telefone e plano
        String criarTabela = "CREATE TABLE clientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "telefone TEXT," +
                "plano TEXT)";
        db.execSQL(criarTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // atualizar o banco
        //DROP TABLE: Apaga a tabela antiga se existir
        //onCreate(db): Chama o metodo onCreate para recriar a tabela do zero
        db.execSQL("DROP TABLE IF EXISTS clientes");
        onCreate(db);
    }
    // CREATE - Criar cliente
    public long adicionarCliente(String nome, String telefone, String plano) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues e uma classe cria um novo pacote de valores chamado 'dados'
        // e colocar nele: nome, telefone e plano e transporta dados para o banco
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("telefone", telefone);
        values.put("plano", plano);
        //Abaixo INSERT do SQL, mas como metodo pronto do Android
        long id = db.insert("clientes", null, values);
        db.close();
        return id;
    }
    // READ - Buscar todos os clientes
    public Cursor getAllClientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("clientes", null, null, null, null, null, "nome ASC");
    }

    // READ - Buscar cliente por ID
    public Cursor getClienteById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("clientes", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
    }

    // UPDATE - Atualizar cliente
    public int atualizarCliente(int id, String nome, String telefone, String plano) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("telefone", telefone);
        values.put("plano", plano);

        // UPDATE do SQL como metodo pronto
        return db.update("clientes", values, "id = ?", new String[]{String.valueOf(id)});
    }

    // DELETE - Excluir cliente
    public int excluirCliente(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE do SQL como metodo pronto
        return db.delete("clientes", "id = ?", new String[]{String.valueOf(id)});
    }

}
