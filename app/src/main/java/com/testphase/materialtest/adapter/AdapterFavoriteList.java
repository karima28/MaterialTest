package com.testphase.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testphase.materialtest.R;
import com.testphase.materialtest.pojo.Product;

import java.util.ArrayList;

/**
 * AdapterFavoriteList is the recyclerview adapter that is used to populate the favorites list
 */
public class AdapterFavoriteList extends RecyclerView.Adapter<AdapterFavoriteList.ViewHolderListFavorites>{

    private LayoutInflater mInflater;
    private ArrayList<Product> mListFavorites = new ArrayList<>();

    public AdapterFavoriteList(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setListFavorites(ArrayList<Product> listFavorites){
        this.mListFavorites = listFavorites;
        notifyDataSetChanged();
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
    public ViewHolderListFavorites onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_list_favorites, parent, false);
        ViewHolderListFavorites viewHolder = new ViewHolderListFavorites(view);
        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position i.e. to update the
     * row
     * @param holder The ViewHolder which should be updated to represent the contents of the item at
     *               the given position in the data set
     * @param position The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(ViewHolderListFavorites holder, int position) {
        Product currentProduct  = mListFavorites.get(position);
        holder.listProductName.setText(currentProduct.getName());
        holder.listProductShortDescription.setText(currentProduct.getShortDescription());
        holder.listProductGoodnessValue.setText(Integer.toString(currentProduct.getGoodnessValue()));
    }

    /**
     * To get the number of items in the Favorites list
     * @return the total number of items in the Favorites list
     */
    @Override
    public int getItemCount() {
        return mListFavorites.size();
    }

    /**
     * Describes an item view from the Favorites list and about its place within the
     * RecyclerView
     */
    static class ViewHolderListFavorites extends RecyclerView.ViewHolder{

        //private ImageView listProductsIcon;
        TextView listProductName;
        TextView listProductShortDescription;
        TextView listProductGoodnessValue;

        public ViewHolderListFavorites(View itemView) {
            super(itemView);
            //listProductsIcon = (ImageView) itemView.findViewById(R.id.listProductsIcon);
            listProductName = (TextView) itemView.findViewById(R.id.listProductName);
            listProductShortDescription = (TextView) itemView.findViewById(R.id.listProductShortDescription);
            listProductGoodnessValue = (TextView) itemView.findViewById(R.id.listProductGoodnessValue);
        }
    }

}
