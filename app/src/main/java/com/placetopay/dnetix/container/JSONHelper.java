package com.placetopay.dnetix.container;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 *
 */
public class JSONHelper {

    public static JSONObject parseMap(Map<String, Object> map) {
        JSONObject data = new JSONObject();

        try {
            // Iterate through the items
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Object item = map.get(key);

                if (item instanceof Map) {
                    data.put(key, JSONHelper.parseMap((Map<String, Object>) item));
                } else {
                    data.put(key, item);
                }
            }
        } catch (JSONException e) {
            Log.i("JSONHelper", "Failure on the JSON construction");
        }

        return data;
    }

}
