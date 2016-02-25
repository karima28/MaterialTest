package com.testphase.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testphase.materialtest.R;
import com.testphase.materialtest.pojo.Product;
import com.testphase.materialtest.pojo.Product;

import java.util.ArrayList;

/**
 * AdapterProductList is the recyclerview adapter that is used to populate the main products list
 */
public class AdapterProductList extends RecyclerView.Adapter<AdapterProductList.ViewHolderListProducts>{

    private LayoutInflater mInflater;
    private ArrayList<Product> mListProducts = new ArrayList<>();


    public AdapterProductList(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setListProducts(ArrayList<Product> listProducts){
        this.mListProducts = listProducts;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderListProducts onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_list_products, parent, false);
        ViewHolderListProducts viewHolder = new ViewHolderListProducts(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderListProducts holder, int position) {
        Product currentProduct  = mListProducts.get(position);
        holder.listProductName.setText(currentProduct.getName());
        holder.listProductShortDescription.setText(currentProduct.getShortDescription());
        holder.listProductGoodnessValue.setText(Integer.toString(currentProduct.getGoodnessValue()));
    }

    @Override
    public int getItemCount() {
        return mListProducts.size();
    }

    static class ViewHolderListProducts extends RecyclerView.ViewHolder{

        //private ImageView listProductsIcon;
        TextView listProductName;
        TextView listProductShortDescription;
        TextView listProductGoodnessValue;


        public ViewHolderListProducts(View itemView) {
            super(itemView);
            //listProductsIcon = (ImageView) itemView.findViewById(R.id.listProductsIcon);
            listProductName = (TextView) itemView.findViewById(R.id.listProductName);
            listProductShortDescription = (TextView) itemView.findViewById(R.id.listProductShortDescription);
            listProductGoodnessValue = (TextView) itemView.findViewById(R.id.listProductGoodnessValue);
        }
    }

}
