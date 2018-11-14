package com.example.nicolas.appprogmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class ClienteSolicitaAgendamentoEvento extends AppCompatActivity {
    private EditText edtDataEvento;
    private EditText edtHorarioEvento;
    private EditText edtLocalEvento;
    private EditText edtQntPessoas;
    private Button btnSalvarAgendamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_solicita_agendamento_evento);

        edtDataEvento = findViewById(R.id.edtDataEvento);
        edtHorarioEvento = findViewById(R.id.edtHorarioEvento);
        edtLocalEvento = findViewById(R.id.edtLocalEvento);
        edtQntPessoas = findViewById(R.id.edtQntPessoas);
        btnSalvarAgendamento = findViewById(R.id.btnSalvarAgendamento);
    }
}
