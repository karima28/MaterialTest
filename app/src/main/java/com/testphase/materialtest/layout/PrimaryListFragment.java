package com.testphase.materialtest.layout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testphase.materialtest.R;
import com.testphase.materialtest.activity.AddActivity;
import com.testphase.materialtest.activity.EditOrDelete;
import com.testphase.materialtest.adapter.AdapterListThings;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;


/**
 * Created by deea on 15/01/16.
 */
public class PrimaryListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArrayList<Thing> listThings = new ArrayList<>();
    private RecyclerView listFirstThings;
    private AdapterListThings adapterListThings;

    ProductDatabase mProductDatabase;

    //public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    public PrimaryListFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrimaryListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrimaryListFragment newInstance(String param1, String param2) {
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


    private ArrayList<Thing> getResults() {

        mProductDatabase = new ProductDatabase(getContext());
        //dbHelper = new DbHelper(getContext());

        L.m("The getResults method executed");

        return mProductDatabase.readItems();


    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_primary_list, container, false);
        listFirstThings = (RecyclerView) view.findViewById(R.id.listFThings);
        listFirstThings.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterListThings = new AdapterListThings(getActivity());
        listFirstThings.setAdapter(adapterListThings);

        listThings = getResults();
        adapterListThings.setListThings(listThings);



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

}
