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
 * AdapterProductList is the recyclerview adapter that is used to populate the main products list
 */
public class AdapterProductList extends RecyclerView.Adapter<AdapterProductList.ViewHolderListProducts>{

    private LayoutInflater mInflater;
    private ArrayList<Product> mListProducts = new ArrayList<>();

    /**
     * Instantiates a layout XML file into its corresponding View objects
     * @param context
     */
    public AdapterProductList(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setListProducts(ArrayList<Product> listProducts){
        this.mListProducts = listProducts;
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
    public ViewHolderListProducts onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_list_products, parent, false);
        ViewHolderListProducts viewHolder = new ViewHolderListProducts(view);
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
    public void onBindViewHolder(ViewHolderListProducts holder, int position) {
        Product currentProduct  = mListProducts.get(position);
        holder.listProductName.setText(currentProduct.getName());
        holder.listProductShortDescription.setText(currentProduct.getShortDescription());
        holder.listProductGoodnessValue.setText(Integer.toString(currentProduct.getGoodnessValue()));
    }

    /**
     * To get the number of items in the primary list
     * @return the total number of items in the primary list
     */
    @Override
    public int getItemCount() {
        return mListProducts.size();
    }

    /**
     * Describes an item view from the products list and about its place within the
     * RecyclerView
     */
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
