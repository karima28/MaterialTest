package com.testphase.materialtest.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import com.testphase.materialtest.adapter.AdapterListThings;
import com.testphase.materialtest.extra.UrlEndpoints;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.network.VolleySingleton;
import com.testphase.materialtest.pojo.Thing;

import java.text.ParseException;
import java.util.ArrayList;

import static com.testphase.materialtest.extra.UrlEndpoints.*;
import static com.testphase.materialtest.extra.Keys.Endpoints.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private ArrayList<Thing> listThings = new ArrayList<>();
    private RecyclerView listFirstThings;
    private AdapterListThings adapterListThings;


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

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        //sendJsonRequest();

    }

    private void sendJsonRequest(){

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
            getRequestUrl(),
            (String) null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    L.m(response.toString());
                    listThings = parseJSONResponse(response);
                    adapterListThings.setListThings(listThings);
                }

            },

            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                L.m(error.toString());
                }
            }
    );

    requestQueue.add(request);

    }

    private ArrayList<Thing> parseJSONResponse(JSONObject response){
        ArrayList<Thing> listThings = new ArrayList<>();

        if(response != null || response.length() > 0){

            try{
                JSONArray arrayThings = response.getJSONArray(KEY_PRODUCT);

                for (int i = 0; i < arrayThings.length(); i++){

                    L.m(Integer.toString(arrayThings.length()));

                    JSONObject currentThing = arrayThings.getJSONObject(i);
                    String name = currentThing.getString(KEY_NAME);
                    //long id = currentThing.getLong(KEY_ID);
                    String description = currentThing.getString(KEY_DESCRIPTION);
                    //String colour = currentThing.getString(KEY_COLOUR);
                    //double price = currentThing.getLong(KEY_PRICE);
                    //String category = currentThing.getString(KEY_CATEGORY);

                    Thing thing = new Thing();
                    thing.setName(name);
                    //thing.setId(id);
                    thing.setDescription(description);
                    //thing.setColour(colour);
                    //thing.setPrice(price);
                    //thing.setCategory(category);

                    listThings.add(thing);

                    L.m(name+description);
                }


            } catch (JSONException e) {

            }
        }

        return listThings;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_search, container, false);

        listFirstThings = (RecyclerView) view.findViewById(R.id.listFThings);
        adapterListThings = new AdapterListThings(getActivity());
        listFirstThings.setAdapter(adapterListThings);

        listFirstThings.setLayoutManager(new LinearLayoutManager(getActivity()));

        sendJsonRequest();

        return view;

    }

}
