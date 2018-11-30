package com.example.nicolas.appprogmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmpresaAceitarRecusarEvento extends AppCompatActivity {
    private EditText edtTipoEvento;
    private EditText edtDataEvento;
    private EditText edtHorarioEvento;
    private EditText edtLocalEvento;
    private EditText edtQntPessoas;
    private EditText edtCliente;
    private Button btnAceitarEvento;
    private Button btnRecusarEvento;
    private DBHelper mDBHelper;
    private Evento mEvento = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_aceitar_recusar_evento);
        mDBHelper = new DBHelper(EmpresaAceitarRecusarEvento.this);

        edtTipoEvento = findViewById(R.id.edtTipoEvento);
        edtDataEvento = findViewById(R.id.edtDataEvento);
        edtHorarioEvento = findViewById(R.id.edtHorarioEvento);
        edtLocalEvento = findViewById(R.id.edtLocalEvento);
        edtQntPessoas = findViewById(R.id.edtQntPessoas);
        edtCliente = findViewById(R.id.edtCliente);


        Bundle args = getIntent().getExtras();
        mEvento = (Evento) args.getSerializable("evento");
        if (mEvento != null){
            fillFilds();
        }else{
            Toast.makeText(this, "Erro ao acessar evento!", Toast.LENGTH_SHORT).show();
            finish();
        }
        btnRecusarEvento = findViewById(R.id.btnRecusarEvento);
        btnRecusarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EmpresaAceitarRecusarEvento.this, "Evento Recusado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //ADD ao listView dos meus eventos
        btnAceitarEvento = findViewById(R.id.btnAceitarEvento);
        btnAceitarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvento = new Evento();
                mEvento.setTipo(edtTipoEvento.getText().toString());
                mEvento.setData(edtDataEvento.getText().toString());
                mEvento.setHora(edtHorarioEvento.getText().toString());
                mEvento.setLocal(edtLocalEvento.getText().toString());
                mEvento.setQntPessoas(edtQntPessoas.getText().toString());
                mEvento.setCliente(edtCliente.getText().toString());

                boolean verify = mDBHelper.insertMeusEventos(mEvento);
                    if (verify){
                        Toast.makeText(EmpresaAceitarRecusarEvento.this, "Eventos aceito!", Toast.LENGTH_LONG).show();
                        //deletar evento da lista de eventos solicitados allEvento
                        mDBHelper.delete(mEvento);
                        finish();
                    }else{
                        Toast.makeText(EmpresaAceitarRecusarEvento.this, "Evento não aceito", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
    private void fillFilds(){
        edtTipoEvento.setText(mEvento.getTipo());
        edtDataEvento.setText(mEvento.getData());
        edtHorarioEvento.setText(mEvento.getHora());
        edtLocalEvento.setText(mEvento.getLocal());
        edtQntPessoas.setText(mEvento.getQntPessoas());
        edtCliente.setText(mEvento.getCliente());
    }
}
