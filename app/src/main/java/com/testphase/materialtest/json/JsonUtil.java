package com.testphase.materialtest.json;

import com.testphase.materialtest.pojo.Thing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by deea on 12/01/16.
 */
public class JsonUtil {

    public static JSONObject toJson(Thing thing){

        try {

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", thing.getName());
            jsonObj.put("description", thing.getDescription());
            jsonObj.put("colour", thing.getColour());
            jsonObj.put("category", thing.getCategory());
            jsonObj.put("price", thing.getPrice());

            return jsonObj;

        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }


}
