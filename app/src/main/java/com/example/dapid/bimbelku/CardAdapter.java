package com.example.dapid.bimbelku;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dapid on 29/04/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    List <ListItem> items;
    Context context;

    public CardAdapter(Bitmap[] images, String [] nama, String [] email, String [] alamat, String [] keahlian, String [] tingkat, String [] rating) {
        super();
        items = new ArrayList<ListItem>();
        for (int i = 0; i < nama.length; i++) {
            ListItem item = new ListItem();
            item.setImage(images[i]);
            item.setNama(nama[i]);
            item.setEmail(email[i]);
            item.setAlamat(alamat[i]);
            item.setKeahlian(keahlian[i]);
            item.setTingkat(tingkat[i]);
            item.setRating(rating[i]);
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListItem list = items.get(position);
        holder.imageView.setImageBitmap(list.getImage());
        holder.txtNama.setText(list.getNama());
        holder.txtKeahlian.setText(list.getKeahlian());
        holder.txtRating.setText(list.getRating());
        holder.currentItem = items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txtNama;
        public TextView txtKeahlian;
        public TextView txtRating;
        public Button btnTerima;
        public Button btnTolak;

        public ListItem currentItem;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Tentor) itemView.getContext()).openFragment(currentItem);
                }
            });
            imageView = (ImageView) itemView.findViewById(R.id.img);
            txtNama = (TextView) itemView.findViewById(R.id.txtName);
            txtKeahlian = (TextView) itemView.findViewById(R.id.txtLes);
            txtRating = (TextView) itemView.findViewById(R.id.rate);
            btnTerima = (Button) itemView.findViewById(R.id.btnTerima);
            btnTolak = (Button) itemView.findViewById(R.id.btnTolak);
            btnTerima.setVisibility(View.INVISIBLE);
            btnTolak.setVisibility(View.INVISIBLE);
        }
    }
}