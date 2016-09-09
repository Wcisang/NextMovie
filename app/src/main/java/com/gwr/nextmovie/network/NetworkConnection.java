package com.gwr.nextmovie.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by willi on 09/07/2016.
 */
public class NetworkConnection {
    private RequestQueue requestQueue;
    private static NetworkConnection instance;
    private  Context context;



    public NetworkConnection(Context c) {
        context = c;
        requestQueue = getRequestQueue();

    }

    public static NetworkConnection getInstance(Context c) {
        if (instance == null) {
            instance = new NetworkConnection(c.getApplicationContext());
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(this.context);
        }

        return requestQueue;
    }

    public void addRequestQueue(Request request){
        getRequestQueue().add(request);
    }

    public void execute( final Transaction transaction){
        String url = transaction.doBefore();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                transaction.doAfter(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                transaction.doAfter(null);
            }
        });

        addRequestQueue(jsonObjectRequest);
    }


}
