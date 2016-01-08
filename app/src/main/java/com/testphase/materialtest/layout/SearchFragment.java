package com.testphase.materialtest.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.testphase.materialtest.MyApplication;
import com.testphase.materialtest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.testphase.materialtest.extra.UrlEndpoints;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.network.VolleySingleton;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

import static com.testphase.materialtest.extra.UrlEndpoints.*;
import static com.testphase.materialtest.extra.Keys.Endpoints.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<Thing> listThings = new ArrayList<>();
    private RecyclerView listFirstThings;


    public SearchFragment() {
        // Required empty public constructor
    }


    public static String getRequestUrl() {
        return URL_PRODUCT;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

        volleySingleton = VolleySingleton.getsInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendJsonRequest();

    }

    private void sendJsonRequest(){

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
            getRequestUrl(),
            (String) null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(getActivity(),response.toString() +"", Toast.LENGTH_SHORT).show();
                }

            },

            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
    );

    requestQueue.add(request);

    }

    private void parseJSONResponse(JSONObject response){
        if(response == null || response.length() == 0){
            return;
        }

        try{
            JSONArray arrayThings = response.getJSONArray(KEY_THING);

            for (int i = 0; i < arrayThings.length(); i++){
                JSONObject currentThing = arrayThings.getJSONObject(i);
                String productThing = currentThing.getString(KEY_PRODUCT);
                JSONObject propertyThing = currentThing.getJSONObject(KEY_PROPERTIES);
                long id = propertyThing.getLong(KEY_ID);
                JSONObject descriptionThing = propertyThing.getJSONObject(KEY_DESCRIPTION);
                String colour = descriptionThing.getString(KEY_COLOUR);
                String type = descriptionThing.getString(KEY_TYPE);

                Thing thing = new Thing();
                thing.setId(id);
                thing.setColour(colour);
                thing.setType(type);

                listThings.add(thing);
            }

            L.T(getActivity(), listThings.toString());




        } catch (JSONException e){


        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_search, container, false);
        listFirstThings = (RecyclerView) view.findViewById(R.id.listFirstThings);
        listFirstThings.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Inflate the layout for this fragment
        return view;

    }

}
