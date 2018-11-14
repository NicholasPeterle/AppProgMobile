package com.example.nicolas.appprogmobile;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtUser;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnCalcel;
    private Button btnRegister;
    private DBHelper mDBHelper;
    private Contact mContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mDBHelper = new DBHelper(Add.this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnCalcel = findViewById(R.id.btnCalcel);
        btnCalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister = findViewById(R.id.btnSave);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContact = new Contact();
                mContact.setName(edtName.getText().toString());
                mContact.setEmail(edtEmail.getText().toString());
                mContact.setUser(edtUser.getText().toString());
                mContact.setPassword(edtPassword.getText().toString());
                String verifyPassword = edtConfirmPassword.getText().toString();
                if(verifyPassword.equals(mContact.getPassword())){
                    // Add on database
                    boolean verify = mDBHelper.insert(mContact);
                    if(verify){
                        Toast.makeText(Add.this, "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Add.this, Login.class);
                        startActivity(intent);
                        empty();
                    }else{
                        Toast.makeText(Add.this, "Erro ao cadastrar!\nE-mail ou usuário já cadastrados.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Add.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void empty() {
        edtName.setText("");
        edtEmail.setText("");
        edtUser.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }
}