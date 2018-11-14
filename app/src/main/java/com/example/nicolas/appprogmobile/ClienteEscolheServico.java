package com.example.nicolas.appprogmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClienteEscolheServico extends AppCompatActivity {
    private Button btnEventoEmpresa;
    private Button btnFormatura;
    private Button btnQuinzeAnos;
    private Button btnCasamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_escolheservico);

        btnEventoEmpresa = findViewById(R.id.btnEventoEmpresa);
        btnEventoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClienteEscolheServico.this, "Evento para Empresas", Toast.LENGTH_SHORT).show();
                //passar para o ClienteAgendamentoEvento o texto FORMATURA
                Intent intent = new Intent(ClienteEscolheServico.this, ClienteSolicitaAgendamentoEvento.class);
                Bundle param = new Bundle();
                param.putString("Evento para Empresas", "EV");
                intent.putExtras(param);
                startActivity(intent);
            }
        });


        btnFormatura = findViewById(R.id.btnFormatura);
        btnFormatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClienteEscolheServico.this, "Formatura", Toast.LENGTH_SHORT).show();
                //passar para o ClienteAgendamentoEvento o texto FORMATURA
                Intent intent = new Intent(ClienteEscolheServico.this, ClienteSolicitaAgendamentoEvento.class);
                Bundle param = new Bundle();
                param.putString("Formatura", "EV");
                intent.putExtras(param);
                startActivity(intent);
            }
        });

        btnQuinzeAnos = findViewById(R.id.btnQuinzeAnos);
        btnQuinzeAnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClienteEscolheServico.this, "15 Anos", Toast.LENGTH_SHORT).show();
                //passar para o ClienteAgendamentoEvento o texto FORMATURA
                Intent intent = new Intent(ClienteEscolheServico.this, ClienteSolicitaAgendamentoEvento.class);
                Bundle param = new Bundle();
                param.putString("15 Anos", "EV");
                intent.putExtras(param);
                startActivity(intent);
            }
        });

        btnCasamento = findViewById(R.id.btnCasamento);
        btnCasamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClienteEscolheServico.this, "Casamento", Toast.LENGTH_SHORT).show();
                //passar para o ClienteAgendamentoEvento o texto FORMATURA
                Intent intent = new Intent(ClienteEscolheServico.this, ClienteSolicitaAgendamentoEvento.class);
                Bundle param = new Bundle();
                param.putString("Casamento", "EV");
                intent.putExtras(param);
                startActivity(intent);
            }
        });

    }
}
