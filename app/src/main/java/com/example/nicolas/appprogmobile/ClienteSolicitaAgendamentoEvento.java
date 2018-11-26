package com.example.nicolas.appprogmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteSolicitaAgendamentoEvento extends AppCompatActivity {
    private EditText edtTipoEvento;
    private EditText edtDataEvento;
    private EditText edtHorarioEvento;
    private EditText edtLocalEvento;
    private EditText edtQntPessoas;
    private EditText edtCliente;
    private Button btnSalvarAgendamento;
    private Toolbar mToolbar;
    private Evento mEvento;
    private DBHelper mDBHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_solicita_agendamento_evento);

        mDBHelper = new DBHelper(ClienteSolicitaAgendamentoEvento.this);

        mToolbar = (Toolbar) findViewById(R.id.tb_ClienteSolicitaAgendamentoEvento);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Informações do evento desejado:");
        mToolbar.setSubtitle("Aplicativo de Gerenciamento de Eventos");
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it = null;

                switch (item.getItemId()){
                    case R.id.voltaTelaSelecaoServiço:
                        startActivity(new Intent(ClienteSolicitaAgendamentoEvento.this, ClienteEscolheServico.class));
                        break;
                    case R.id.sair:
                        startActivity(new Intent(ClienteSolicitaAgendamentoEvento.this, Inicial.class));
                        break;
                }
                return true;

            }
        });
        mToolbar.inflateMenu(R.menu.clienteescolheservico_menu_buttom);

        edtTipoEvento = findViewById(R.id.edtTipoEvento);
        edtDataEvento = findViewById(R.id.edtDataEvento);
        edtHorarioEvento = findViewById(R.id.edtHorarioEvento);
        edtLocalEvento = findViewById(R.id.edtLocalEvento);
        edtQntPessoas = findViewById(R.id.edtQntPessoas);
        edtCliente = findViewById(R.id.edtCliente);


        btnSalvarAgendamento = findViewById(R.id.btnSalvarAgendamento);
        btnSalvarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvento = new Evento();
                mEvento.setTipo(edtTipoEvento.getText().toString());
                mEvento.setData(edtDataEvento.getText().toString());
                mEvento.setHora(edtHorarioEvento.getText().toString());
                mEvento.setLocal(edtLocalEvento.getText().toString());
                mEvento.setQntPessoas(edtQntPessoas.getText().toString());
                mEvento.setCliente(edtCliente.getText().toString());

                boolean verify = mDBHelper.insert(mEvento);

                if (verify){
                    Toast.makeText(ClienteSolicitaAgendamentoEvento.this, "Evento cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    empty();
                }else {
                    Toast.makeText(ClienteSolicitaAgendamentoEvento.this, "O evento não foi cadastrado, tente novamente.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void empty(){
        edtTipoEvento.setText("");
        edtDataEvento.setText("");
        edtHorarioEvento.setText("");
        edtLocalEvento.setText("");
        edtQntPessoas.setText("");
        edtCliente.setText("");
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.clientesolicitaagendamentoevento_menu_buttom, menu);
        return true;
    }

}
