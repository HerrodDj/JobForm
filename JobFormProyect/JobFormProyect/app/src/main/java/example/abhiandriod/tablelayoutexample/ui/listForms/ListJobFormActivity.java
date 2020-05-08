package example.abhiandriod.tablelayoutexample.ui.listForms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Model.Datos;
import Model.Form;
import example.abhiandriod.tablelayoutexample.ui.home.Home;
import example.abhiandriod.tablelayoutexample.R;
import example.abhiandriod.tablelayoutexample.ui.jobForm.JobFormActivity;

public class ListJobFormActivity extends AppCompatActivity implements ListJobFormAdapter.JobFormAdapterListener,ListJobFormHelper.RecyclerItemTouchHelperListener{
    private RecyclerView mRecyclerView;
    private ListJobFormAdapter mAdapter;
    private List<Form> JobsList;
    private static Datos datos;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout=findViewById(R.id.coordinator_layout);

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


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ListJobFormHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof ListJobFormAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = JobsList.get(viewHolder.getAdapterPosition()).getName();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item from adapter
                        mAdapter.restoreItem(deletedIndex);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        } else {
            //If is editing a row object
            Form aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, JobFormActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("form", aux);
            mAdapter.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }

    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);

    }
}
