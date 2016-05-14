package com.example.dapid.bimbelku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.ViewHolder>{

    List <ListNotif> items;
    Context context;
    private final String detail = "Ingin menambahkan Anda sebagai tentornya.";
    private CoordinatorLayout coordinatorLayout;

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
        holder.position = position;
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
        public Button btnTerima;
        public Button btnTolak;
        public ListNotif currentItem;
        public int position;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            txtNama = (TextView) itemView.findViewById(R.id.txtName);
            txtDetail = (TextView) itemView.findViewById(R.id.txtLes);
            txtRate = (TextView) itemView.findViewById(R.id.rate);
            btnTerima = (Button) itemView.findViewById(R.id.btnTerima);
            btnTolak = (Button) itemView.findViewById(R.id.btnTolak);
            txtRate.setEnabled(false);
            txtRate.setVisibility(View.INVISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnTerima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final String nama = (String) txtNama.getText();
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Terima");
                    builder.setMessage("Anda akan menerima "+nama+" ??");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            final ProgressDialog pg = new ProgressDialog(v.getContext());
                            pg.setMessage("Memproses..");
                            pg.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    items.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, getItemCount());
                                    pg.dismiss();
                                    Snackbar snackbar = Snackbar.make(v.getRootView().findViewById(R.id.recyclerView), "Anda telah berteman dengan "+nama, Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }}, 2500);

                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            });

            btnTolak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final String nama = (String) txtNama.getText();
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Tolak");
                    builder.setMessage("Anda akan menolak "+nama+" ??");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            final ProgressDialog pg = new ProgressDialog(v.getContext());
                            pg.setMessage("Memproses..");
                            pg.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    items.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, getItemCount());
                                    pg.dismiss();
                                    Snackbar snackbar = Snackbar.make(v.getRootView().findViewById(R.id.recyclerView), nama+" telah Anda tolak sebagai teman.", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }}, 2500);

                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            });
        }
    }
}