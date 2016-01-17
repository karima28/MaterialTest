package com.testphase.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.testphase.materialtest.pojo.Information;
import com.testphase.materialtest.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by deea on 06/01/16.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewholder> {

    private Context context;
    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();

    public InfoAdapter(Context context, List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    //
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewholder holder = new MyViewholder(view);
        return holder;
    }

    //Updates the info in the rows each time
    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconID);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //Viewholder once created to set what one row looks like and each time filled with data
    class MyViewholder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;

        public MyViewholder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);

        }


    }
}
