package com.testphase.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.testphase.materialtest.R;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

/**
 * Created by deea on 08/01/16.
 */
public class AdapterListThings extends RecyclerView.Adapter<AdapterListThings.ViewHolderListThings>{

    private LayoutInflater mInflater;
    private ArrayList<Thing> mListThings = new ArrayList<>();


    public AdapterListThings(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setListThings(ArrayList<Thing> listThings){
        this.mListThings = listThings;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderListThings onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_list_first_things, parent, false);
        ViewHolderListThings viewHolder = new ViewHolderListThings(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderListThings holder, int position) {
        Thing currentThing  = mListThings.get(position);
        holder.listThingsName.setText(currentThing.getName());
        holder.listThingsShortDescription.setText(currentThing.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mListThings.size();
    }

    static class ViewHolderListThings extends RecyclerView.ViewHolder{

        //private ImageView listThingsIcon;
        TextView listThingsName;
        TextView listThingsShortDescription;


        public ViewHolderListThings(View itemView) {
            super(itemView);
            //listThingsIcon = (ImageView) itemView.findViewById(R.id.listThingsIcon);
            listThingsName = (TextView) itemView.findViewById(R.id.listThingsName);
            listThingsShortDescription = (TextView) itemView.findViewById(R.id.listThingsShortDescription);
        }
    }

}
