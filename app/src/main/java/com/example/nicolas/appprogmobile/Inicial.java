package com.example.nicolas.appprogmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class Inicial extends AppCompatActivity {
    private Button btnFornecedor;
    private Button btnCliente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        btnFornecedor = findViewById(R.id.btnFornecedor);
        btnFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Inicial.this, "Inicial", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Inicial.this, Login.class));
            }
        });

        btnCliente = findViewById(R.id.btnCliente);
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Inicial.this, "Inicial", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Inicial.this, Login.class));
            }
        });


    }
}
