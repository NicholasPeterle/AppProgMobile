package com.example.nicolas.appprogmobile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nicolas.appprogmobile.ClienteSolicitaAgendamentoEvento;
import com.example.nicolas.appprogmobile.Contact;
import com.example.nicolas.appprogmobile.Evento;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Instância do database
    SQLiteDatabase mDatabase;
    Context mContext;
    //Constatntes referente ao banco de dados
    private static final String DATABASE_NAME = "db_eventos.db";
    private static final int DATABASE_VERSION = 4;

    //Tabela EMPRESA
    private static final String TABLE_EMPRESA_NOME = "empresa";//nome da tabela
    private static final String TABLE_EMPRESA_COLUM_ID = "id";
    private static final String TABLE_EMPRESA_COLUM_NAME = "nome";
    private static final String TABLE_EMPRESA_COLUM_CNPJ = "cnpj";
    private static final String TABLE_EMPRESA_COLUM_USER = "userempresa";
    private static final String TABLE_EMPRESA_COLUM_EMAIL = "email";
    private static final String TABLE_EMPRESA_COLUM_PASSWORD = "senha";

    /** Constantes referente a tabela: cliente*/
    private static final String TABLE_CONTACT_NAME = "cliente"; // Nome da tabela
    private static final String TABLE_CONTACT_COLUM_ID = "id";
    private static final String TABLE_CONTACT_COLUM_NAME = "nome";
    private static final String TABLE_CONTACT_COLUM_EMAIL = "email";
    private static final String TABLE_CONTACT_COLUM_USER = "user";
    private static final String TABLE_CONTACT_COLUM_PASSWORD = "senha";

    // TABELA EVENTO
    private static final String TABLE_EVENTO_NAME = "evento";
    private static final String TABLE_EVENTO_COLUM_ID = "id";
    private static final String TABLE_EVENTO_COLUM_TIPO = "tipo"; //Será setado através do botão da tela ClienteEscolheServico.java
    private static final String TABLE_EVENTO_COLUM_DATA = "data";
    private static final String TABLE_EVENTO_COLUM_HORA = "hora";
    private static final String TABLE_EVENTO_COLUM_LOCAL = "local";
    private static final String TABLE_EVENTO_COLUM_QTDPESSOAS = "qtdPessoas";
    private static final String TABLE_EVENTO_COLUM_CLIENTE = "cliente";

    /* SQL para criaçao da tabela cliente*/
    private static final String SQL_CREATE_TABLE_CONTACTS = " CREATE TABLE "
            + TABLE_CONTACT_NAME + " ( "
            + TABLE_CONTACT_COLUM_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + TABLE_CONTACT_COLUM_NAME + " TEXT NOT NULL,"
            + TABLE_CONTACT_COLUM_EMAIL + " TEXT NOT NULL UNIQUE,"
            + TABLE_CONTACT_COLUM_USER + " TEXT NOT NULL UNIQUE,"
            + TABLE_CONTACT_COLUM_PASSWORD + " TEXT NOT NULL ) ; " ;

    // SQL para criaçao da tabela Empresa
    private static final String SQL_CREATE_TABLE_EMPRESA_NOME = " CREATE TABLE "
            + TABLE_EMPRESA_NOME + " ( "
            + TABLE_EMPRESA_COLUM_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + TABLE_EMPRESA_COLUM_NAME + " TEXT NOT NULL,"
            + TABLE_EMPRESA_COLUM_EMAIL + " TEXT NOT NULL UNIQUE,"
            + TABLE_EMPRESA_COLUM_USER + " TEXT NOT NULL UNIQUE,"
            + TABLE_EMPRESA_COLUM_CNPJ + " TEXT NOT NULL UNIQUE,"
            + TABLE_EMPRESA_COLUM_PASSWORD + " TEXT NOT NULL ) ; " ;
    // SQL para criacao da tabela EVENTO
    private static final String SQL_CREATE_TABLE_EVENTO_NAME = " CREATE TABLE "
            + TABLE_EVENTO_NAME + " ( "
            + TABLE_EVENTO_COLUM_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + TABLE_EVENTO_COLUM_TIPO + " TEXT NOT NULL, "
            + TABLE_EVENTO_COLUM_LOCAL + " TEXT NOT NULL,"
            + TABLE_EVENTO_COLUM_DATA + " TEXT NOT NULL, "
            + TABLE_EVENTO_COLUM_HORA + " TEXT NOT NULL, "
            + TABLE_EVENTO_COLUM_QTDPESSOAS + " INT NOT NULL, "
            + TABLE_EVENTO_COLUM_CLIENTE + " TEXT NOT NULL) ; " ;
    //+ "FOREIGN KEY(" +TABLE_EVENTO_COLUM_CLIENTE+ ") REFERENCES "+SQL_CREATE_TABLE_CONTACTS+"("+TABLE_CONTACT_COLUM_ID+"));" ;




    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "onCreate");
        db.execSQL(SQL_CREATE_TABLE_CONTACTS);
        db.execSQL(SQL_CREATE_TABLE_EMPRESA_NOME);
        db.execSQL(SQL_CREATE_TABLE_EVENTO_NAME);
        this.mDatabase = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        Log.i("TAG", "onUpgrade");
        Log.i("TAG", "Versão antiga: " + versaoAntiga);
        Log.i("TAG", "Versão nova: " + versaoNova);
        String query = "DROP TABLE IF EXISTS "+ TABLE_CONTACT_NAME;
        String query2 = "DROP TABLE IF EXISTS "+ TABLE_EMPRESA_NOME;
        String query3 = "DROP TABLE IF EXISTS "+ TABLE_EVENTO_NAME;

        mDatabase = db;
        mDatabase.execSQL(query);
        mDatabase.execSQL(query2);
        mDatabase.execSQL(query3);
        this.onCreate(mDatabase);
    }
    //*************************************************************************************
    // ContactDAO - CRUD
    boolean insert(Contact contact){
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_CONTACT_COLUM_NAME, contact.getName());
        values.put(TABLE_CONTACT_COLUM_EMAIL, contact.getEmail());
        values.put(TABLE_CONTACT_COLUM_USER, contact.getUser());
        values.put(TABLE_CONTACT_COLUM_PASSWORD, contact.getPassword());

        long i = mDatabase.insert(TABLE_CONTACT_NAME, null,values );
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        if(i > 0){
            Log.i("TAG","Cliente cadastrado com sucesso.");
            return true;
        }else {
            Log.e("TAG","Erro ao cadastrar cliente. Por favor, tente novamente.");
            return false;
        }

    }
    public String getPassword(String user) {
        mDatabase = this.getWritableDatabase();
        String query = "SELECT "+ TABLE_CONTACT_COLUM_USER +", " + TABLE_CONTACT_COLUM_PASSWORD + " FROM " + TABLE_CONTACT_NAME + ";";
        Cursor cursor = mDatabase.rawQuery(query,null);
        String a, b;
        b="Não encontrado";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString((cursor.getColumnIndex(TABLE_CONTACT_COLUM_USER)));
                if (a.equals(user)) {
                    b = cursor.getString((cursor.getColumnIndex(TABLE_CONTACT_COLUM_PASSWORD)));
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
        retornoBD = mDatabase.delete(TABLE_CONTACT_NAME, TABLE_CONTACT_COLUM_NAME + "=?", args);
        mDatabase.close();
        return retornoBD;
    }

    public long update(Contact contact) {
        long retornoBD;
        mDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_CONTACT_COLUM_NAME, contact.getName());
        values.put(TABLE_CONTACT_COLUM_EMAIL, contact.getEmail());
        values.put(TABLE_CONTACT_COLUM_USER, contact.getUser());
        values.put(TABLE_CONTACT_COLUM_PASSWORD, contact.getPassword());
        String[] args = {String.valueOf(contact.getUser())};
        retornoBD = mDatabase.update(TABLE_CONTACT_NAME, values, TABLE_CONTACT_COLUM_USER + "=?", args);
        mDatabase.close();
        return retornoBD;
    }
    public ArrayList<Contact> getAll(){
        // Cria um List guardar os pessoas consultados no banco de dados
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        // Contato auxiliar
        Contact contact = null;
        // Colunas desejadas no retorno
        String[] coluns = {TABLE_CONTACT_COLUM_NAME, TABLE_CONTACT_COLUM_EMAIL, TABLE_CONTACT_COLUM_USER, TABLE_CONTACT_COLUM_PASSWORD};

        // Instancia uma nova conexão com o banco de dados em modo leitura
        mDatabase = this.getWritableDatabase();

        // Executa a consulta no banco de dados
        Cursor cursor = mDatabase.query(TABLE_CONTACT_NAME, coluns ,null, null, null, null, TABLE_CONTACT_COLUM_ID +" ASC");
        //db.rawQuery("SELECT * FROM contacts;",null);
        /**
         * Percorre o Cursor, injetando os dados consultados em um objeto definido do
         * tipo {@link Contact} e adicionando-os na List
         */
        try{
            while (cursor.moveToNext()){
                contact = new Contact();
                contact.setName(cursor.getString(cursor.getColumnIndex(TABLE_CONTACT_COLUM_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(TABLE_CONTACT_COLUM_EMAIL)));
                contact.setUser(cursor.getString(cursor.getColumnIndex(TABLE_CONTACT_COLUM_USER)));
                contact.setPassword(cursor.getString(cursor.getColumnIndex(TABLE_CONTACT_COLUM_PASSWORD)));

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
    //*********************************************************************************************8
    // EMPRESA - CRUD
    boolean insertEmpresa(Empresa empresa){
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_EMPRESA_COLUM_NAME, empresa.getNomeEmpresa());
        values.put(TABLE_EMPRESA_COLUM_CNPJ, empresa.getCnpj());
        values.put(TABLE_EMPRESA_COLUM_EMAIL, empresa.getEmailEmpresa());
        values.put(TABLE_EMPRESA_COLUM_USER, empresa.getUserEmpresa());
        values.put(TABLE_EMPRESA_COLUM_PASSWORD, empresa.getPasswordEmpresa());

        long i = mDatabase.insert(TABLE_EMPRESA_NOME, null,values );
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        if(i > 0){
            Log.i("TAG","Empresa cadastrada com sucesso!");
            return true;
        }else {
            Log.e("TAG","A empresa não foi cadastrada. Por favor, tente novamente.");
            return false;
        }
    }
    public String getPasswordEmpresa(String userEmpresa) {
        mDatabase = this.getWritableDatabase();
        String query = "SELECT "+ TABLE_EMPRESA_COLUM_USER +", " + TABLE_EMPRESA_COLUM_PASSWORD + " FROM " + TABLE_EMPRESA_NOME + ";";
        Cursor cursor = mDatabase.rawQuery(query,null);
        String a, b;
        b="Não encontrado";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString((cursor.getColumnIndex(TABLE_EMPRESA_COLUM_USER)));
                if (a.equals(userEmpresa)) {
                    b = cursor.getString((cursor.getColumnIndex(TABLE_EMPRESA_COLUM_PASSWORD)));
                    break;
                }
            } while (cursor.moveToNext());
        }
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        return  b;
    }
    public long delete(Empresa empresa){
        long retornoBD;
        mDatabase = this.getWritableDatabase();
        String[] args = {String.valueOf(empresa.getNomeEmpresa())};
        retornoBD = mDatabase.delete(TABLE_EMPRESA_NOME, TABLE_EMPRESA_NOME + "=?", args);
        mDatabase.close();
        return retornoBD;
    }

    //*********************************************************************************************8
    // EVENTO - CRUD
    public boolean insert(Evento eventoSolicitado){
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_EVENTO_COLUM_TIPO, eventoSolicitado.getTipo());
        values.put(TABLE_EVENTO_COLUM_DATA, eventoSolicitado.getData());
        values.put(TABLE_EVENTO_COLUM_HORA, eventoSolicitado.getHora());
        values.put(TABLE_EVENTO_COLUM_LOCAL, eventoSolicitado.getLocal());
        values.put(TABLE_EVENTO_COLUM_QTDPESSOAS, eventoSolicitado.getQntPessoas());
        values.put(TABLE_EVENTO_COLUM_CLIENTE, eventoSolicitado.getCliente());

        long j = mDatabase.insert(TABLE_EVENTO_NAME, null,values );
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        if(j > 0){
            Log.i("TAG","Evento cadastrado com sucesso!");
            return true;
        }else {
            Log.e("TAG","O evento não foi cadastrado. Por favor, tente novamente.");
            return false;
        }
    }
    public long delete(Evento evento){
        long retornoBD;
        mDatabase = this.getWritableDatabase();
        String[] args = {String.valueOf(evento.getTipo())};
        retornoBD = mDatabase.delete(TABLE_EVENTO_NAME, TABLE_EVENTO_COLUM_ID + "=?", args);
        mDatabase.close();
        return retornoBD;
    }
    // Evento_Mostrar_todos - CRUD
    public ArrayList<Evento> getAllEvento(){
        // Cria um List guardar os pessoas consultados no banco de dados
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        // Contato auxiliar
        Evento evento = null;
        // Colunas desejadas no retorno
        String[] coluns = {TABLE_EVENTO_COLUM_TIPO , TABLE_EVENTO_COLUM_DATA ,TABLE_EVENTO_COLUM_HORA, TABLE_EVENTO_COLUM_LOCAL, TABLE_EVENTO_COLUM_QTDPESSOAS, TABLE_EVENTO_COLUM_CLIENTE};

        // Instancia uma nova conexão com o banco de dados em modo leitura
        mDatabase = this.getWritableDatabase();

        // Executa a consulta no banco de dados
        Cursor cursor = mDatabase.query(TABLE_EVENTO_NAME, coluns, null, null, null, null, TABLE_EVENTO_COLUM_ID +" ASC");
        //db.rawQuery("SELECT * FROM contacts;",null);
        /**
         * Percorre o Cursor, injetando os dados consultados em um objeto definido do
         * tipo {@link Evento} e adicionando-os na List
         */
        try{
            while (cursor.moveToNext()){
                evento = new Evento();
                evento.setTipo(cursor.getString(cursor.getColumnIndex(TABLE_EVENTO_COLUM_TIPO)));
                evento.setData(cursor.getString(cursor.getColumnIndex(TABLE_EVENTO_COLUM_DATA)));
                evento.setHora(cursor.getString(cursor.getColumnIndex(TABLE_EVENTO_COLUM_HORA)));
                evento.setLocal(cursor.getString(cursor.getColumnIndex(TABLE_EVENTO_COLUM_LOCAL)));
                evento.setQntPessoas(cursor.getString(cursor.getColumnIndex(TABLE_EVENTO_COLUM_QTDPESSOAS)));
                evento.setCliente(cursor.getString(cursor.getColumnIndex(TABLE_EVENTO_COLUM_CLIENTE)));


                eventos.add(evento);
            }
        }finally {
            // Encerra o Cursor
            cursor.close();
        }
        // Encerra o a conexao com banco de dados
        mDatabase.close();
        //retorno a lista
        return eventos;
    }


}