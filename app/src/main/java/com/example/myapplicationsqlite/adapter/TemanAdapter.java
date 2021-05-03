package com.example.myapplicationsqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationsqlite.MainActivity;
import com.example.myapplicationsqlite.R;
import com.example.myapplicationsqlite.database.DBController;
import com.example.myapplicationsqlite.database.Teman;
import com.example.myapplicationsqlite.DetailData;
import com.example.myapplicationsqlite.EditTeman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listdata;
    private Context c;
    public TemanAdapter(ArrayList<Teman> listdata) {
        this.listdata = listdata;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        LayoutInflater layoutinf = LayoutInflater.from(parent.getContext());
        View view =layoutinf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm, tlp, id;

        nm = listdata.get(position).getNama();
        tlp = listdata.get(position).getTelepon();
        id = listdata.get(position).getId();
        DBController db = new DBController(c);

        holder.namaTxt.setText(nm);
        holder.teleponTxt.setText(tlp);

        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, DetailData.class);
                i.putExtra("id", id);
                i.putExtra("nama", nm);
                i.putExtra("telepon", tlp);
                c.startActivity(i);
            }
        });

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pop = new PopupMenu(c,holder.cardku);
                pop.inflate(R.menu.popup);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edData:
                                Intent i = new Intent(c, EditTeman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telepon",tlp);
                                c.startActivity(i);
                                break;

                            case R.id.hpsData:
                                HashMap<String,String> val = new HashMap<>();
                                val.put("nama",nm);
                                db.deleteData(val);
                                Intent in = new Intent(c, MainActivity.class);
                                c.startActivity(in);
                                break;
                        }
                        return true;
                    }
                });
                pop.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listdata != null)?listdata.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,teleponTxt;
        public TemanViewHolder(View view){
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textnama);
            teleponTxt = (TextView) view.findViewById(R.id.texttelpon);
        }

    }
}
