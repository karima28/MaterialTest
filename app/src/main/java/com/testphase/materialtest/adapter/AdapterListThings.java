package com.testphase.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.testphase.materialtest.R;
import com.testphase.materialtest.network.VolleySingleton;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

/**
 * Created by deea on 08/01/16.
 */
public class AdapterListThings extends RecyclerView.Adapter<AdapterListThings.ViewHolderListThings>{

    private LayoutInflater mInflater;
    private ArrayList<Thing> mListThings = new ArrayList<>();
    //private VolleySingleton mVolleySingleton;
    //private ImageLoader mImageLoader;

    public AdapterListThings(Context context){
        mInflater = LayoutInflater.from(context);
        //mVolleySingleton = VolleySingleton.getInstance();
        //mImageLoader = mVolleySingleton.getImageLoader();
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
        holder.listThingsDescription.setText(currentThing.getDescription());
    }

    @Override
    public int getItemCount() {
        return mListThings.size();
    }

    static class ViewHolderListThings extends RecyclerView.ViewHolder{

        //private ImageView listThingsIcon;
        TextView listThingsName;
        TextView listThingsDescription;


        public ViewHolderListThings(View itemView) {
            super(itemView);
            //listThingsIcon = (ImageView) itemView.findViewById(R.id.listThingsIcon);
            listThingsName = (TextView) itemView.findViewById(R.id.listThingsName);
            listThingsDescription = (TextView) itemView.findViewById(R.id.listThingsDescription);
        }
    }

}
