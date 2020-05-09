package example.abhiandriod.tablelayoutexample.ui.jobForm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.Datos;
import Model.Form;
import example.abhiandriod.tablelayoutexample.MainActivity;
import example.abhiandriod.tablelayoutexample.ui.home.Home;
import example.abhiandriod.tablelayoutexample.R;
import example.abhiandriod.tablelayoutexample.ui.listForms.ListJobFormActivity;
import example.abhiandriod.tablelayoutexample.ui.listForms.ListJobFormAdapter;

public class JobFormActivity extends AppCompatActivity {
    private DatePickerDialog date;
    private EditText fecha ;
    private EditText nombre ;
    private EditText apellido ;
    private EditText calle1 ;
    private EditText calle2 ;
    private EditText ciudad ;
    private EditText estado ;
    private EditText zip ;
    private Spinner paises ;
    private EditText email;
    private EditText area ;
    private EditText telefono ;
    private Spinner trabajo ;
    private ImageButton resume ;
    private ImageButton btnSub ;
    private boolean editable = false;
    static int rolNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_form);
        editable = false;


        fecha = (EditText) findViewById(R.id.fecha);
        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        calle1 = (EditText) findViewById(R.id.calle);
        calle2 = (EditText) findViewById(R.id.calle2);
        ciudad = (EditText) findViewById(R.id.ciudad);
        estado = (EditText) findViewById(R.id.estado);
        zip = (EditText) findViewById(R.id.zip);
        paises = (Spinner) findViewById(R.id.spinner);
        email = (EditText) findViewById(R.id.email);
        area = (EditText) findViewById(R.id.area);
        telefono = (EditText) findViewById(R.id.telefono);
        resume = (ImageButton) findViewById(R.id.resuBtn);
        trabajo=(Spinner) findViewById(R.id.spinner2);
        btnSub = (ImageButton) findViewById(R.id.confirmB);


        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                date = new DatePickerDialog(JobFormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                date.show();
            }
        });
        String[] tab_c = getResources().getStringArray(R.array.countries);
        String[] tab_j = getResources().getStringArray(R.array.JobList);


        ArrayAdapter<CharSequence> adap = ArrayAdapter.createFromResource(this,R.array.countries, R.layout.spinner_item);
        adap.setDropDownViewResource(R.layout.list_spinner);
        paises.setAdapter(adap);

        ArrayAdapter<CharSequence> adap1 = ArrayAdapter.createFromResource(this,R.array.JobList, R.layout.spinner_item);
        adap1.setDropDownViewResource(R.layout.list_spinner);
        trabajo.setAdapter(adap1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Form aux = (Form) getIntent().getSerializableExtra("form");
                fecha.setText(aux.getDate());
                nombre.setText(aux.getName());
                nombre.setEnabled(false);
                apellido.setText(aux.getLastName());
                calle1.setText(aux.getStreetAdrees());
                calle2.setText(aux.getStreetAdrees2());
                ciudad.setText(aux.getCiudad());
                estado.setText(aux.getState());
                zip.setText(aux.getZipCode());
                email.setText(aux.getEmail());
                area.setText(aux.getAreaCode());
                telefono.setText(aux.getPhoneNumber());
                for(int i=0; i<tab_c.length;i++) {
                    if (aux.getCountry().equals(tab_c[i])) {
                        paises.setSelection(i);
                        break;
                    }
                }
                for(int i=0; i<tab_j.length;i++) {
                    if (aux.getApplyingJob().equals(tab_j[i])) {
                        trabajo.setSelection(i);
                        break;
                    }
                }
            }
        }
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editable){
                    editJobF();
                }else{
                    addJobF();
                }
            }
        });
    }

    private void addJobF() {
        if(validateForm()){
            Form nF = new Form(nombre.getText().toString(),apellido.getText().toString(),calle1.getText().toString(),calle2.getText().toString(),ciudad.getText().toString(),
                    estado.getText().toString(),zip.getText().toString(),paises.getSelectedItem().toString(),email.getText().toString(),area.getText().toString(),
                    telefono.getText().toString(),trabajo.getSelectedItem().toString(),fecha.getText().toString());
            Intent intent = new Intent(getBaseContext(), ListJobFormActivity.class);
            intent.putExtra("FormN", nF);
            startActivity(intent);
        }
    }

    private void editJobF() {
        if(validateForm()){
            Form nF = new Form(nombre.getText().toString(),apellido.getText().toString(),calle1.getText().toString(),calle2.getText().toString(),ciudad.getText().toString(),
                    estado.getText().toString(),zip.getText().toString(),paises.getSelectedItem().toString(),email.getText().toString(),area.getText().toString(),
                    telefono.getText().toString(),trabajo.getSelectedItem().toString(),fecha.getText().toString());
            Intent intent = new Intent(getBaseContext(), ListJobFormActivity.class);
            intent.putExtra("FormNE", nF);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() { //TODO it's not working yet

        Intent a = new Intent(this, Home.class);
        startActivity(a);
        super.onBackPressed();
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nombre.getText())) {
            nombre.setError("Name Required");
            error++;
        }
        if (TextUtils.isEmpty(this.apellido.getText())) {
            apellido.setError("Last Name Required");
            error++;
        }
        if (TextUtils.isEmpty(this.calle1.getText())) {
            calle1.setError("Street1 Required");
            error++;
        }
        if (TextUtils.isEmpty(this.calle2.getText())) {
            calle2.setError("Street2 Required");
            error++;
        }
        if (TextUtils.isEmpty(this.ciudad.getText())) {
            ciudad.setError("City Required");
            error++;
        }
        if (TextUtils.isEmpty(this.estado.getText())) {
            estado.setError("State Required");
            error++;
        }
        if (TextUtils.isEmpty(this.zip.getText())) {
            zip.setError("Zip Required");
            error++;
        }
        if (TextUtils.isEmpty(this.email.getText())) {
            email.setError("Email Required");
            error++;
        }
        if (TextUtils.isEmpty(this.area.getText())) {
            area.setError("Area Code Required");
            error++;
        }
        if (TextUtils.isEmpty(this.telefono.getText())) {
            telefono.setError("Phone Required");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Complete all Fields", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
