package com.example.nicolas.appprogmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmpresaView extends AppCompatActivity {
    private EditText edtUser;
        private ListView lvEventosSolicitados;
    private ArrayList<Evento> mListEventosSolicitados;
    private DBHelper mDBHelper;
    private ArrayAdapter<Evento> mAdapter;
    private Evento mEvento;
    private int ID_DELETE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_view);

        edtUser = findViewById(R.id.edtUser);
        lvEventosSolicitados =  findViewById(R.id.lvEventosSolicitados);
        mEvento = new Evento();
        // Registrando  omenu de contexto
        registerForContextMenu(lvEventosSolicitados);

        Bundle args = getIntent().getExtras();
        String user = args.getString("user_key");
        if(user != null){
            edtUser.setText(user);
        }else{
            Toast.makeText(this, "Erro ao acessar os argumentos!", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Clique simples na Lista
        lvEventosSolicitados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(EmpresaView.this, "Click Simples: Nome: "+mListEventosSolicitados.get(position).getTipo(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EmpresaView.this, Update.class);
                intent.putExtra("evento", (CharSequence) mListEventosSolicitados.get(position));
                startActivity(intent);
            }
        });
        //Clique Longo
        lvEventosSolicitados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(EmpresaView.this, "Click Longo: Nome: "+mListEventosSolicitados.get(position).getTipo(), Toast.LENGTH_SHORT).show();
                mEvento = mListEventosSolicitados.get(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem mItemDelete = menu.add(Menu.NONE, ID_DELETE, 1 ,"Deletar");
        mItemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.e("TAG","Clicou excluír!");
                int ret = (int) mDBHelper.delete(mEvento);
                if(ret > 0){
                    Toast.makeText(EmpresaView.this, "Evento excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    fillList();
                }else{
                    Toast.makeText(EmpresaView.this, "O evento não foi excluído!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDBHelper = new DBHelper(EmpresaView.this);
        fillList();
    }

    public  void fillList(){

        mListEventosSolicitados = mDBHelper.getAllEvento();

        if(mListEventosSolicitados != null){
            mAdapter = new ArrayAdapter<>(
                    EmpresaView.this,
                    android.R.layout.simple_list_item_1,
                    mListEventosSolicitados);
            // Setando o adaptador
            lvEventosSolicitados.setAdapter(mAdapter);
        }
    }
}