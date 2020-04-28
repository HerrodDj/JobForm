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

public class registerActivity extends AppCompatActivity {
    private Datos usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usuarios = new Datos();

        Button cnlBtn = (Button) findViewById(R.id.canBtn);
        Button okBtn = (Button) findViewById(R.id.OkRBtn);
        final EditText nm = (EditText) findViewById(R.id.userName);
        final EditText ps = (EditText) findViewById(R.id.password);

        cnlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                registerActivity.this.startActivity(intent);
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nm.getText().toString();
                String pass = ps.getText().toString();
                User nU = new User(name,pass,2);
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                intent.putExtra("Register", nU);
                registerActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}
