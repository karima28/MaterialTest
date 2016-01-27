package com.testphase.materialtest.layout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import com.testphase.materialtest.activity.EditOrDelete;
import com.testphase.materialtest.activity.ItemDisplayActivity;
import com.testphase.materialtest.activity.MainActivity;
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

        listFirstThings.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), listFirstThings, new PrimaryListFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Thing thing = listThings.get(position);

                Intent intent = new Intent(getActivity(), ItemDisplayActivity.class);

                intent.putExtra("KEY_EXTRA_ID", thing.getId());
                intent.putExtra("KEY_EXTRA_NAME", thing.getName());
                intent.putExtra("KEY_EXTRA_SDESC", thing.getShortDescription());
                intent.putExtra("KEY_EXTRA_LDESC", thing.getLongdescription());
                intent.putExtra("KEY_EXTRA_GVALUE", thing.getGoodnessValue());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.deleteItem)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mProductDatabase.deleteThing(listThings.get(position).getId());
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

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }


}
