package example.abhiandriod.tablelayoutexample;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.List;

import Model.Datos;
import Model.Form;
import Model.User;
import example.abhiandriod.tablelayoutexample.ui.home.Home;

public class MainActivity extends AppCompatActivity {
    private Datos lista;
    public static List<Form> l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new Datos();

        // initiate a button
        ImageButton loginButton = (ImageButton) findViewById(R.id.loginBtn);
        final EditText name = (EditText) findViewById(R.id.userName);
        final EditText pass = (EditText) findViewById(R.id.password);
        final TextView changePassBtn = (TextView) findViewById(R.id.changPass);
        final TextView regisBtn = (TextView) findViewById(R.id.regis);
        // perform click event on the button

        changePassBtn.setPaintFlags(changePassBtn.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        check();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = name.getText().toString();
                String password = pass.getText().toString();
                if(validUser(nombre,password)){
                    int rol = getRol(nombre,password);
                    Toast.makeText(getApplicationContext(), "Autentificado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    intent.putExtra("rol", rol);
                    MainActivity.this.startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "No pudo Autentificar", Toast.LENGTH_LONG).show();  // display a toast message
                }
            }
        });

        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cambiar Contraseña", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, changePassActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });

        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Autentificado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, registerActivity.class);
                    MainActivity.this.startActivity(intent);
                    finish();
            }
        });
    }

    private void check() {
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            User aux0 = (User) getIntent().getSerializableExtra("Register");
            if(aux0!=null){
                add(aux0);
            }
            User aux = (User) getIntent().getSerializableExtra("ChangePass");
            if(aux!=null){
                //cambio contrasenia
                search(aux);
            }
        }
    }

    private boolean validUser(String name, String pass) {
        boolean flag = false;
        for (User user : lista.getUsuarios()) {
            if (user.getUserName().equals(name) && user.getPass().equals(pass)) {
                flag = true;
            }
        }
        return flag;
    }

    private boolean search(User p) {
        boolean flag = false;
        String n = p.getUserName();
        String pa = p.getPass();
        for (User user : lista.getUsuarios()) {
            if (user.getUserName().equals(n)) {
                user.setPass(pa);
                flag=true;
                break;
            }
        }
        return flag;
    }

    private int getRol(String n, String p){
        int r =0;
        for (User user : lista.getUsuarios()) {
            if (user.getUserName().equals(n)) {
                r = user.getRol();
                break;
            }
        }
        return r;
    }


    private void add(User p){
        lista.getUsuarios().add(p);
    }




}
