package com.tspolice.eticket.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.TextView;

import com.tspolice.eticket.R;
import com.tspolice.eticket.models.ViolationsModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<ViolationsModel> contactList;
    private List<ViolationsModel> contactListFiltered;
    private ViolationClickListener listener;

    public RecyclerAdapter(Context context, List<ViolationsModel> contactList, ViolationClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_violation_name, tv_vio_section;
        Spinner spinner_amount;
        //CheckBox violation_check;

        MyViewHolder(View view) {
            super(view);
            tv_violation_name = (TextView) view.findViewById(R.id.tv_violation_name);
            tv_vio_section = (TextView) view.findViewById(R.id.tv_vio_section);
            spinner_amount = (Spinner) view.findViewById(R.id.spinner_amount);
            //violation_check = (CheckBox) view.findViewById(R.id.violation_check);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onViolationSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.violations_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ViolationsModel model = contactListFiltered.get(position);
        holder.tv_violation_name.setText(model.getViolation());
        holder.tv_vio_section.setText(model.getVioSection());

        List<String> vioAmounts = new ArrayList<>();
        vioAmounts.add(model.getVioMinAmount());
        vioAmounts.add(model.getVioMaxAmount());
        vioAmounts.add(model.getVioAvgAmount());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vioAmounts);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner_amount.setAdapter(arrayAdapter);

        /*holder.violation_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onViolationSelected(contactListFiltered.get(position));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<ViolationsModel> filteredList = new ArrayList<>();
                    for (ViolationsModel row : contactList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getViolation().toLowerCase().contains(charString.toLowerCase()) || row.getVioSection().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<ViolationsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ViolationClickListener {
        void onViolationSelected(ViolationsModel contact);
    }
}
