package example.abhiandriod.tablelayoutexample.ui.listForms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Model.Form;
import example.abhiandriod.tablelayoutexample.R;

public class ListJobFormAdapter extends RecyclerView.Adapter<ListJobFormAdapter.MyViewHolder> implements Filterable {
    private List<Form> formList;
    private List<Form> formListFiltered;
    private JobFormAdapterListener listener;
    private Form deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, title2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            title1 = view.findViewById(R.id.titleFirstLbl);
            title2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(formListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public ListJobFormAdapter(List<Form> formList, JobFormAdapterListener listener) {
        this.formList = formList;
        this.listener = listener;
        //init filter
        this.formListFiltered = formList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final Form form = formListFiltered.get(position);
        holder.title1.setText(form.getPhoneNumber());
        holder.title2.setText(form.getLastName());
        holder.description.setText(form.getName());
    }

    @Override
    public int getItemCount() {
        return formListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = formListFiltered.remove(position);
        Iterator<Form> iter = formList.iterator();
        while (iter.hasNext()) {
            Form aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (formListFiltered.size() == formList.size()) {
            formListFiltered.add(position, deletedItem);
        } else {
            formListFiltered.add(position, deletedItem);
            formList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Form getSwipedItem(int index) {
        if (this.formList.size() == this.formListFiltered.size()) { //not filtered yet
            return formList.get(index);
        } else {
            return formListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (formList.size() == formListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(formList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(formList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(formListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(formListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    formListFiltered = formList;
                } else {
                    List<Form> filteredList = new ArrayList<>();
                    for (Form row : formList) {
                        // filter use two parameters
                        if (row.getLastName().toLowerCase().contains(charString.toLowerCase()) || row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    formListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = formListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                formListFiltered = (ArrayList<Form>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface JobFormAdapterListener {
        void onContactSelected(Form form);
    }
}