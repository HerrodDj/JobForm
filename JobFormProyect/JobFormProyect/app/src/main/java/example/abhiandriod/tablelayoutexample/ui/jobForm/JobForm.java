package example.abhiandriod.tablelayoutexample.ui.jobForm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import example.abhiandriod.tablelayoutexample.ui.home.Home;
import example.abhiandriod.tablelayoutexample.R;

public class JobForm extends AppCompatActivity {
    private DatePickerDialog date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_form);

        final EditText fecha = (EditText) findViewById(R.id.fecha);
        final EditText nombre = (EditText) findViewById(R.id.nombre);
        final EditText apellido = (EditText) findViewById(R.id.apellido);
        final EditText calle1 = (EditText) findViewById(R.id.calle);
        final EditText calle2 = (EditText) findViewById(R.id.calle2);
        final EditText ciudad = (EditText) findViewById(R.id.ciudad);
        final EditText estado = (EditText) findViewById(R.id.estado);
        final EditText zip = (EditText) findViewById(R.id.zip);
        final Spinner paises = (Spinner) findViewById(R.id.spinner);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText area = (EditText) findViewById(R.id.area);
        final EditText telefono = (EditText) findViewById(R.id.telefono);
        final Spinner trabajo = (Spinner) findViewById(R.id.spinner2);
        final Button resume = (Button) findViewById(R.id.buttonFile);
        final Button btnSub = (Button) findViewById(R.id.button2);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                date = new DatePickerDialog(JobForm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                fecha.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                date.show();
            }
        });
    }




    @Override
    public void onBackPressed() { //TODO it's not working yet
        Intent a = new Intent(this, Home.class);
        startActivity(a);
        super.onBackPressed();
    }
}
