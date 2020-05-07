package example.abhiandriod.tablelayoutexample.ui.listForms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Model.Datos;
import Model.Form;
import example.abhiandriod.tablelayoutexample.ui.home.Home;
import example.abhiandriod.tablelayoutexample.R;

public class ListJobFormActivity extends AppCompatActivity implements ListJobFormAdapter.JobFormAdapterListener{
    private RecyclerView mRecyclerView;
    private ListJobFormAdapter mAdapter;
    private List<Form> JobsList;
    private static Datos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerView);
        JobsList = new ArrayList<>();
        datos= new Datos();
        JobsList=datos.getForms();

        mAdapter = new ListJobFormAdapter(JobsList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Form aux = (Form) getIntent().getSerializableExtra("FormN");
            JobsList.add(aux);
            Toast.makeText(this, "Job Form Confirmed", Toast.LENGTH_SHORT).show();
        }


        mAdapter.notifyDataSetChanged();

    }


    @Override
    public void onBackPressed() { //TODO it's not working yet
        Intent a = new Intent(this, Home.class);
        startActivity(a);
        super.onBackPressed();
    }

    @Override
    public void onContactSelected(Form form) {

    }
}
