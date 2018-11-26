package com.example.nicolas.appprogmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.util.stream.Stream.empty;


public class AddEmpresa extends AppCompatActivity {
    private EditText edtNomeEmpresa;
    private EditText edtCnpj;
    private EditText edtEmailEmpresa;
    private EditText edtUserEmpresa;
    private EditText edtPasswordEmpresa;
    private EditText edtConfirmPasswordEmpresa;
    private Button btnCalcel;
    private Button btnSaveEmpresa;
    private DBHelper mDBHelper;
    private Empresa mEmpresa;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_empresa);

        mDBHelper = new DBHelper(AddEmpresa.this);
        edtNomeEmpresa = findViewById(R.id.edtNomeEmpresa);
        edtCnpj = findViewById(R.id.edtCnpj);
        edtEmailEmpresa = findViewById(R.id.edtEmailEmpresa);
        edtUserEmpresa = findViewById(R.id.edtUserEmpresa);
        edtPasswordEmpresa = findViewById(R.id.edtPasswordEmpresa);
        edtConfirmPasswordEmpresa = findViewById(R.id.edtConfirmPasswordEmpresa);

        btnCalcel = findViewById(R.id.btnCalcel);
        btnCalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSaveEmpresa  = findViewById(R.id.btnSaveEmpresa);
        btnSaveEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmpresa = new Empresa();
                mEmpresa.setNomeEmpresa(edtNomeEmpresa.getText().toString());
                mEmpresa.setCnpj(edtCnpj.getText().toString());
                mEmpresa.setEmailEmpresa(edtEmailEmpresa.getText().toString());
                mEmpresa.setUserEmpresa(edtUserEmpresa.getText().toString());
                mEmpresa.setPasswordEmpresa(edtPasswordEmpresa.getText().toString());
                String verifyPassword = edtConfirmPasswordEmpresa.getText().toString();
                if (verifyPassword.equals(mEmpresa.getPasswordEmpresa())) {

                    boolean verify = mDBHelper.insertEmpresa(mEmpresa);
                    if (verify) {
                        Toast.makeText(AddEmpresa.this, "Empresa cadastrada com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddEmpresa.this, LoginEmpresa.class);
                        startActivity(intent);
                        empty();
                    } else {
                        Toast.makeText(AddEmpresa.this, "Erro ao cadastrar!\nE-mail ou Empresa já cadastrados.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AddEmpresa.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void empty() {
        edtNomeEmpresa.setText("");
        edtEmailEmpresa.setText("");
        edtUserEmpresa.setText("");
        edtPasswordEmpresa.setText("");
        edtConfirmPasswordEmpresa.setText("");
    }

}
