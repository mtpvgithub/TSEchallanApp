package com.tspolice.eticket.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tspolice.eticket.R;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private final String[] module_name;
    private final int[] module_image;

    public GridAdapter(Context context, String[] module_name, int[] module_image) {
        this.context = context;
        this.module_name = module_name;
        this.module_image = module_image;
    }

    @Override
    public int getCount() {
        return module_name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            //grid = new View(context);
            assert inflater != null;
            grid = inflater.inflate(R.layout.grid_item, null);
            TextView textView = grid.findViewById(R.id.grid_text);
            ImageView imageView = grid.findViewById(R.id.grid_image);
            textView.setText(module_name[position]);
            imageView.setImageResource(module_image[position]);
        } else {
            grid = convertView;
        }
        return grid;
    }
}
