package example.abhiandriod.tablelayoutexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;

import Model.Datos;
import Model.User;

public class changePassActivity extends AppCompatActivity {
    private Datos lista;
    private EditText userN;
    private EditText pass ;
    private EditText Npass;
    private EditText conPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        lista = new Datos();


        ImageButton cnlBtn = (ImageButton) findViewById(R.id.canBtn);
        ImageButton okBtn = (ImageButton) findViewById(R.id.OKBtn);
        userN = (EditText) findViewById(R.id.userName);
        pass = (EditText) findViewById(R.id.curPas);
        Npass = (EditText) findViewById(R.id.Newpassword);
        conPass = (EditText) findViewById(R.id.cpC);

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
                if (validateForm()){
                    String nombre = userN.getText().toString();
                    String password = pass.getText().toString();
                    String Npassword = Npass.getText().toString();
                    if (validUser(nombre, password) && Npass != null) {
                        Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(changePassActivity.this, MainActivity.class);
                        User nU = new User(nombre, Npassword, 2);
                        intent.putExtra("ChangePass", nU);
                        startActivity(intent);
                        finish(); //prevent go back
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() { //TODO it's not working yet
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
        super.onBackPressed();
    }

    private boolean validUser(String name, String pass) {
        boolean flag = false;
        for (User user : lista.getUsuarios()) {
            if (user.getUserName().equals(name) && user.getPass().equals(pass)) {
                flag = true;
            }
        }
        if(flag==false){
            Toast.makeText(getApplicationContext(), "User doesnt exist", Toast.LENGTH_LONG).show();
        }
        return flag;
    }


    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.userN.getText())) {
            userN.setError("User Required");
            error++;
        }
        if (TextUtils.isEmpty(this.pass.getText())) {
            pass.setError("Old password Required");
            error++;
        }
        if (!Npass.getText().toString().equals(conPass.getText().toString())) {
            conPass.setError("Both passwords must be the same");
            error++;
        }
        if (TextUtils.isEmpty(this.Npass.getText())) {
            Npass.setError("Confirm Password");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Complete all Fields", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}
