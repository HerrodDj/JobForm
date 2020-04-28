package example.abhiandriod.tablelayoutexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Datos;
import Model.User;

public class changePassActivity extends AppCompatActivity {
    private Datos lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        lista = new Datos();

        Button cnlBtn = (Button) findViewById(R.id.canBtn);
        Button okBtn = (Button) findViewById(R.id.OkBtn);
        final EditText userN = (EditText) findViewById(R.id.userName);
        final EditText pass = (EditText) findViewById(R.id.curPas);
        final EditText Npass = (EditText) findViewById(R.id.Newpassword);

        cnlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(changePassActivity.this, MainActivity.class);
                changePassActivity.this.startActivity(intent);
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = userN.getText().toString();
                String password = pass.getText().toString();
                String Npassword = Npass.getText().toString();
                if(validUser(nombre,password) && Npass!=null){
                    Toast.makeText(getApplicationContext(), "Contraseña Modificada", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(changePassActivity.this, MainActivity.class);
                    User nU = new User(nombre,Npassword,2);
                    intent.putExtra("ChangePass", nU);
                    startActivity(intent);
                    finish(); //prevent go back
                }
            }
        });
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

}
