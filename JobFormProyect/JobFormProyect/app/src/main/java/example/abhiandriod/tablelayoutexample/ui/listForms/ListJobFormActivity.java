package example.abhiandriod.tablelayoutexample.ui.listForms;

import android.app.SearchManager;
import android.content.Context;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Model.Datos;
import Model.Form;
import Model.SingletonFormList;
import example.abhiandriod.tablelayoutexample.ui.home.Home;
import example.abhiandriod.tablelayoutexample.R;
import example.abhiandriod.tablelayoutexample.ui.jobForm.JobFormActivity;

public class ListJobFormActivity extends AppCompatActivity implements ListJobFormAdapter.JobFormAdapterListener,ListJobFormHelper.RecyclerItemTouchHelperListener{
    private RecyclerView mRecyclerView;
    private ListJobFormAdapter mAdapter;
    private List<Form> JobsList;
    private static Datos datos;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private static int ft=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        mRecyclerView = findViewById(R.id.recyclerView);
        JobsList = new ArrayList<>();
        datos = new Datos();
        if (ft==1){
            for (int i = 0; i < datos.getForms().size(); i++) {
                SingletonFormList.getInstance().addToArray(datos.getForms().get(i));
            }
            ft++;
        }
        JobsList.addAll(SingletonFormList.getInstance().getArray());

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
            Form aux;
            aux = (Form) getIntent().getSerializableExtra("FormN");
            if (aux == null) {
                aux = (Form) getIntent().getSerializableExtra("FormNE");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Form form : JobsList) {
                        if (form.getName().equals(aux.getName())) {
                            form.setDate(aux.getDate());
                            form.setLastName(aux.getLastName());
                            form.setStreetAdrees(aux.getStreetAdrees());
                            form.setStreetAdrees2(aux.getStreetAdrees2());
                            form.setCiudad(aux.getCiudad());
                            form.setState(aux.getState());
                            form.setCountry(aux.getCountry());
                            form.setEmail(aux.getEmail());
                            form.setAreaCode(aux.getAreaCode());
                            form.setPhoneNumber(aux.getPhoneNumber());
                            form.setZipCode(aux.getZipCode());
                            form.setApplyingJob(aux.getApplyingJob());
                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getName()+" "+aux.getLastName()+" form"+" correctly edited", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getName()+" "+aux.getLastName()+ " not found", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                JobsList=SingletonFormList.getInstance().getArray();
                Toast.makeText(getApplicationContext(), aux.getName()+" "+aux.getLastName() + " successfully added", Toast.LENGTH_LONG).show();
            }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds carreraList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView   !IMPORTANT
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }
}
