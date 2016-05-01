package com.example.dapid.bimbelku;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dapid on 29/04/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    List <ListItem> items;

    public CardAdapter(String [] name, String [] les) {
        super();
        items = new ArrayList<ListItem>();
        for (int i = 0; i < name.length; i++) {
            ListItem item = new ListItem();
            item.setName(name[i]);
            item.setLes(les[i]);
            items.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem list = items.get(position);
        holder.txtName.setText(list.getName());
        holder.txtLes.setText(list.getLes());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtLes;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtLes = (TextView) itemView.findViewById(R.id.txtLes);
        }
    }
}