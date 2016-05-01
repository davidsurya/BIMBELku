package com.example.dapid.bimbelku;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dapid on 29/04/16.
 */
public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.ViewHolder>{

    List <ListNotif> items;
    Context context;
    private final String detail = "Ingin menambahkan Anda sebagai tentornya.";

    public NotifAdapter(Bitmap[] images, String [] nama) {
        super();
        items = new ArrayList<ListNotif>();
        for (int i = 0; i < nama.length; i++) {
            ListNotif item = new ListNotif();
            item.setImage(images[i]);
            item.setNama(nama[i]);
            items.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        context = v.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListNotif list = items.get(position);
        holder.imageView.setImageBitmap(list.getImage());
        holder.txtNama.setText(list.getNama());
        holder.txtDetail.setText(this.detail);
        holder.currentItem = items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txtNama;
        public TextView txtDetail;
        public TextView txtRate;
        public ListNotif currentItem;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            imageView = (ImageView) itemView.findViewById(R.id.img);
            txtNama = (TextView) itemView.findViewById(R.id.txtName);
            txtDetail = (TextView) itemView.findViewById(R.id.txtLes);
            txtRate = (TextView) itemView.findViewById(R.id.rate);
            txtRate.setEnabled(false);
            txtRate.setVisibility(View.INVISIBLE);
        }
    }
}