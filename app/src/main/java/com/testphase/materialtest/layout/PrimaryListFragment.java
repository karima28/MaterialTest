package com.testphase.materialtest.layout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.testphase.materialtest.R;
import com.testphase.materialtest.activity.AddActivity;
import com.testphase.materialtest.activity.ItemDisplayActivity;
import com.testphase.materialtest.activity.MainActivity;
import com.testphase.materialtest.adapter.AdapterProductList;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Product;

import java.util.ArrayList;
import java.util.Collections;


/**
 * PrimaryListFragment is the fragment used to display the products recyclerview list and handle the clicks on the items
 */
public class PrimaryListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArrayList<Product> listProducts = new ArrayList<>();
    private RecyclerView listPrimaryProducts;
    private AdapterProductList adapterProductList;

    ProductDatabase mProductDatabase;


    public PrimaryListFragment() {
        // Required empty public constructor
    }


    public static PrimaryListFragment newInstance() {
        PrimaryListFragment fragment = new PrimaryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private ArrayList<Product> getResults() {

        mProductDatabase = new ProductDatabase(getContext());

        return mProductDatabase.getAllProducts("primary");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_primary_list, container, false);
        listPrimaryProducts = (RecyclerView) view.findViewById(R.id.listPrimaryProducts);
        listPrimaryProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterProductList = new AdapterProductList(getActivity());
        listPrimaryProducts.setAdapter(adapterProductList);

        listProducts = getResults();
        Collections.sort(listProducts);
        adapterProductList.setListProducts(listProducts);

        listPrimaryProducts.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), listPrimaryProducts, new PrimaryListFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Product product = listProducts.get(position);

                long itemID = product.getId();

                Intent intent = new Intent(getActivity(), ItemDisplayActivity.class);
                intent.putExtra("KEY_EXTRA_PRODUCT_ID", itemID);

                //Clicking on the item is equivalent to liking it and thus the goodnessvalue is increased
                product.updateGoodnessValue(ItemDisplayActivity.GValueLikeAction);

                mProductDatabase.updateProductGValue(itemID, product.getGoodnessValue());

                L.m("New Goodness Value: " + Integer.toString(product.getGoodnessValue()));

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.deleteItem)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mProductDatabase.deleteProduct(listProducts.get(position).getId());
                                Toast.makeText(getContext(), "Successfully deleted item ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Delete Item?");
                d.show();
            }
        }));

        //Sets up the floating action button to add new items and the response when clicked
        android.support.design.widget.FloatingActionButton floatingActionButton = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }

        });

        return view;
    }

    /**
     *  Allows the application to intercept touch events in progress at the view hierarchy level of
     *  the RecyclerView before those touch events are considered for RecyclerView's own scrolling behavior.
     */
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child != null && clickListener != null){
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(),e.getY());

            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){

                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    /**
     * Listens for short clicks and long clicks
     */
    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }


}
