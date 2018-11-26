package com.example.nicolas.appprogmobile;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginEmpresa extends AppCompatActivity {
    private EditText edtUser;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private DBHelper mDBHelper;
    private Toolbar mToolbar;
    private Toolbar mToolbarButtom;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_empresa);

        mToolbar = (Toolbar) findViewById(R.id.tb_login);
        setSupportActionBar(mToolbar);
        //mToolbar.setTitle("Login");
        mToolbar.setSubtitle("Aplicativo de Gerenciamento de Eventos");
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it = null;

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(LoginEmpresa.this, Inicial.class));
                        break;
                    case R.id.sair:
                        finish();
                        break;
                }
                return true;

            }
        });
        mToolbar.inflateMenu(R.menu.login_menu_buttom);

        /*
        MENU DE RODAPÉ, DESNECESSÁRIO
        mToolbarButtom = (Toolbar) findViewById(R.id.inc_tb_buttom);
        mToolbarButtom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent it = null;

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(Login.this, Inicial.class));
                        break;
                    case R.id.sair:
                        break;
                }
               return true;
            }
        });
        mToolbarButtom.inflateMenu(R.menu.login_menu_buttom);
        */

        mDBHelper = new DBHelper(LoginEmpresa.this);

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnSave);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmpresa = edtUser.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmResult = mDBHelper.getPasswordEmpresa(userEmpresa);
                if(password.equals(confirmResult)){
                    //AQUI.........................................................................
                    startActivity(new Intent(LoginEmpresa.this, EmpresaView.class).putExtra("user_key", userEmpresa));
                }else{
                    Toast.makeText(LoginEmpresa.this, "Usuário ou senha não conferem", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginEmpresa.this, "Cadastrar", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginEmpresa.this, AddEmpresa.class));
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.login_menu_buttom, menu);
        return true;
    }
}