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

    public void setListProducts(ArrayList<Product> listFavorites){
        this.mListFavorites = listFavorites;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderListFavorites onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_list_favorites, parent, false);
        ViewHolderListFavorites viewHolder = new ViewHolderListFavorites(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderListFavorites holder, int position) {
        Product currentProduct  = mListFavorites.get(position);
        holder.listProductName.setText(currentProduct.getName());
        holder.listProductShortDescription.setText(currentProduct.getShortDescription());
        holder.listProductGoodnessValue.setText(Integer.toString(currentProduct.getGoodnessValue()));
    }

    @Override
    public int getItemCount() {
        return mListFavorites.size();
    }

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
