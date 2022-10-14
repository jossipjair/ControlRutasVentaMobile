package com.hunter.controlrutasyaku.Modelo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyInstance {

    private static VolleyInstance instance;
    private RequestQueue queue;
    private static Context contexto;

    private VolleyInstance(Context context) {
        contexto = context;
        queue = getQueue();
    }

    public static synchronized VolleyInstance getInstanceVolley(Context context) {
        if (instance == null) {
            instance = new VolleyInstance(context);
        }
        return instance;
    }

    public RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(contexto.getApplicationContext());
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getQueue().add(request);
    }

}
