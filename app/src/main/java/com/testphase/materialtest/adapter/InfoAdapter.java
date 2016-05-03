package com.testphase.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.testphase.materialtest.R;
import com.testphase.materialtest.pojo.Information;

import java.util.Collections;
import java.util.List;

/**
 * InfoAdapter is the recyclerview adapter that is used to populate the navigation drawer fragment
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewholder> {

    private Context context;
    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();

    public InfoAdapter(Context context, List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * an item
     * @param parent The ViewGroup into which the new View will be added after it is bound to an
     *               adapter position
     * @param viewType The view type of the new View
     * @return The new corresponding viewholder
     */
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewholder holder = new MyViewholder(view);
        return holder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position, i.e. to update the
     * row
     * @param holder The ViewHolder which should be updated to represent the contents of the item at
     *               the given position in the data set
     * @param position The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconID);
    }

    /**
     * To get the number of items in the navigation drawer
     * @return the total number of options listed in the navigation drawer
     */
    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * Describes an item view from the Favorites list and about its place within the
     * RecyclerView
     */
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
