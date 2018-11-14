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

public class Login extends AppCompatActivity {
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
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.tb_login);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Tela de Login");


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


        mDBHelper = new DBHelper(Login.this);

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnSave);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmResult = mDBHelper.getPassword(user);
                if(password.equals(confirmResult)){
                    //AQUI.........................................................................
                    startActivity(new Intent(Login.this, ClienteEscolheServico.class));
                }else{
                    Toast.makeText(Login.this, "Usuário ou senha não conferem", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Cadastrar", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, Add.class));
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.login_menu_buttom, menu);
        return true;
    }
}