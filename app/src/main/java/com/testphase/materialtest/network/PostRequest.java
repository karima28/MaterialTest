package com.testphase.materialtest.network;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.testphase.materialtest.json.JsonUtil;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Thing;

import org.json.JSONObject;

import static com.testphase.materialtest.layout.SearchFragment.getRequestUrl;

/**
 * Created by deea on 13/01/16.
 */
public class PostRequest {


    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public PostRequest newInstance(String name, String description, String colour, double price, String category) {

        PostRequest postRequest = new PostRequest();
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        Thing thing = new Thing(name, description, colour, price, category);

        L.m(JsonUtil.toJson(thing).toString());

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getRequestUrl(),
                JsonUtil.toJson(thing),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

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


    return postRequest;

    }



}
