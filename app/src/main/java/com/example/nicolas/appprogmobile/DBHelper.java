package com.example.nicolas.appprogmobile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Instância do database
    SQLiteDatabase mDatabase;
    Context mContext;
    //Constatntes referente ao banco de dados
    private static final String DATABASE_NAME = "db_eventos";
    private static final int DATABASE_VERSION = 1;

    // nomes das tabelas do banco de dados
    private static final String TABLE_EMPRESA = "empresa";
    private static final String TABLE_CLIENTE = "cliente";
    private static final String TABLE_EVENTO = "evento";

    //Tabela EMPRESA
    private static final String TABLE_EMPRESA_NOME = "empresa";
    private static final String TABLE_EMPRESA_COLUM_NAME = "nome";
    private static final String TABLE_EMPRESA_COLUM_CNPJ = "cnpj";
    private static final String TABLE_EMPRESA_COLUM_EMAIL = "email";
    private static final String TABLE_EMPRESA_COLUM_PASSWORD = "senha";

    /** Constantes referente a tabela: cliente*/
    private static final String TABLE_CLIENTE_NAME = "cliente"; // Nome da tabela
    private static final String TABLE_CLIENTE_COLUM_ID = "id";
    private static final String TABLE_CLIENTE_COLUM_NAME = "nome";
    private static final String TABLE_CLIENTE_COLUM_EMAIL = "email";
    private static final String TABLE_CLIENTE_COLUM_USER = "user";
    private static final String TABLE_CLIENTE_COLUM_PASSWORD = "senha";

    // TABELA EVENTO
    private static final String TABLE_EVENTO_NAME = "evento";
    private static final String TABLE_EVENTO_COLUM_ID = "id";
    private static final String TABLE_EVENTO_COLUM_TIPO = "tipo";
    private static final String TABLE_EVENTO_COLUM_LOCAL = "local";
    private static final String TABLE_EVENTO_COLUM_DATA = "data";
    private static final String TABLE_EVENTO_COLUM_HORA = "hora";
    private static final String TABLE_EVENTO_COLUM_QTDPESSOAS = "qtdPessoas";

    /** SQL para criaçao da tablea cliente*/
    private static final String SQL_CREATE_TABLE_CONTACTS = " CREATE TABLE "
            + TABLE_CLIENTE_NAME + " ( "
            + TABLE_CLIENTE_COLUM_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + TABLE_CLIENTE_COLUM_NAME + " TEXT NOT NULL,"
            + TABLE_CLIENTE_COLUM_EMAIL + " TEXT NOT NULL UNIQUE,"
            + TABLE_CLIENTE_COLUM_USER + " TEXT NOT NULL UNIQUE,"
            + TABLE_CLIENTE_COLUM_PASSWORD + " TEXT NOT NULL ) ; " ;

    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "onCreate");
        db.execSQL(SQL_CREATE_TABLE_CONTACTS);
        this.mDatabase = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        Log.i("TAG", "onUpgrade");
        Log.i("TAG", "Versão antiga: " + versaoAntiga);
        Log.i("TAG", "Versão nova: " + versaoNova);
        String query = "DROP TABLE IF EXISTS "+ TABLE_CLIENTE_NAME ;
        mDatabase = db;
        mDatabase.execSQL(query);
        this.onCreate(mDatabase);
    }

    // ContactDAO - CRUD
    boolean insert(Contact contact){
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_CLIENTE_COLUM_NAME, contact.getName());
        values.put(TABLE_CLIENTE_COLUM_EMAIL, contact.getEmail());
        values.put(TABLE_CLIENTE_COLUM_USER, contact.getUser());
        values.put(TABLE_CLIENTE_COLUM_PASSWORD, contact.getPassword());

        long i = mDatabase.insert(TABLE_CLIENTE_NAME, null,values );
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        if(i > 0){
            Log.i("TAG","Sucesso ao inserir no banco de dados");
            return true;
        }else {
            Log.e("TAG","Erro ao inserir no banco de dados");
            return false;
        }

    }
    public String getPassword(String user) {
        mDatabase = this.getWritableDatabase();
        String query = "SELECT "+TABLE_CLIENTE_COLUM_USER+", " +TABLE_CLIENTE_COLUM_PASSWORD+ " FROM " + TABLE_CLIENTE_NAME + ";";
        Cursor cursor = mDatabase.rawQuery(query,null);
        String a, b;
        b="Não encontrado";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString((cursor.getColumnIndex(TABLE_CLIENTE_COLUM_USER)));
                if (a.equals(user)) {
                    b = cursor.getString((cursor.getColumnIndex(TABLE_CLIENTE_COLUM_PASSWORD)));
                    break;
                }
            } while (cursor.moveToNext());
        }
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        return  b;
    }
    public long delete(Contact c){
        long retornoBD;
        mDatabase = this.getWritableDatabase();
        String[] args = {String.valueOf(c.getName())};
        retornoBD = mDatabase.delete(TABLE_CLIENTE_NAME, TABLE_CLIENTE_COLUM_NAME + "=?", args);
        mDatabase.close();
        return retornoBD;
    }

    public long update(Contact contact) {
        long retornoBD;
        mDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_CLIENTE_COLUM_NAME, contact.getName());
        values.put(TABLE_CLIENTE_COLUM_EMAIL, contact.getEmail());
        values.put(TABLE_CLIENTE_COLUM_USER, contact.getUser());
        values.put(TABLE_CLIENTE_COLUM_PASSWORD, contact.getPassword());
        String[] args = {String.valueOf(contact.getUser())};
        retornoBD = mDatabase.update(TABLE_CLIENTE_NAME, values, TABLE_CLIENTE_COLUM_USER + "=?", args);
        mDatabase.close();
        return retornoBD;
    }
    public ArrayList<Contact> getAll(){
        // Cria um List guardar os pessoas consultados no banco de dados
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        // Contato auxiliar
        Contact contact = null;
        // Colunas desejadas no retorno
        String[] coluns = {TABLE_CLIENTE_COLUM_NAME, TABLE_CLIENTE_COLUM_EMAIL, TABLE_CLIENTE_COLUM_USER, TABLE_CLIENTE_COLUM_PASSWORD};

        // Instancia uma nova conexão com o banco de dados em modo leitura
        mDatabase = this.getWritableDatabase();

        // Executa a consulta no banco de dados
        Cursor cursor = mDatabase.query(TABLE_CLIENTE_NAME, coluns ,null, null, null, null, TABLE_CLIENTE_COLUM_ID +" ASC");
        //db.rawQuery("SELECT * FROM contacts;",null);
        /**
         * Percorre o Cursor, injetando os dados consultados em um objeto definido do
         * tipo {@link Contact} e adicionando-os na List
         */
        try{
            while (cursor.moveToNext()){
                contact = new Contact();
                contact.setName(cursor.getString(cursor.getColumnIndex(TABLE_CLIENTE_COLUM_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(TABLE_CLIENTE_COLUM_EMAIL)));
                contact.setUser(cursor.getString(cursor.getColumnIndex(TABLE_CLIENTE_COLUM_USER)));
                contact.setPassword(cursor.getString(cursor.getColumnIndex(TABLE_CLIENTE_COLUM_PASSWORD)));

                contacts.add(contact);
            }
        }finally {
            // Encerra o Cursor
            cursor.close();
        }
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        //retorno a lista
        return contacts;
    }


}