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

import Model.Datos;
import Model.User;

public class registerActivity extends AppCompatActivity {
    private Datos usuarios;
    private EditText nm;
    private EditText ps;
    private EditText cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usuarios = new Datos();

        ImageButton cnlBtn = (ImageButton) findViewById(R.id.canBtn);
        ImageButton okBtn = (ImageButton) findViewById(R.id.OKBtn);
        nm = (EditText) findViewById(R.id.userName);
        ps = (EditText) findViewById(R.id.password);
        cp = (EditText) findViewById(R.id.passwordC);

        cnlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                registerActivity.this.startActivity(intent);
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()) {
                    String name = nm.getText().toString();
                    String pass = ps.getText().toString();
                    User nU = new User(name, pass, 2);
                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(registerActivity.this, MainActivity.class);
                    intent.putExtra("Register", nU);
                    registerActivity.this.startActivity(intent);
                    finish();
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

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nm.getText())) {
            nm.setError("User Required");
            error++;
        }
        if (TextUtils.isEmpty(this.ps.getText())) {
            ps.setError("password Required");
            error++;
        }
        if (!ps.getText().toString().equals(cp.getText().toString())) {
            cp.setError("Both passwords must be the same");
            error++;
        }
        if (TextUtils.isEmpty(this.cp.getText())) {
            cp.setError("Confirm Password");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Complete all Fields", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
