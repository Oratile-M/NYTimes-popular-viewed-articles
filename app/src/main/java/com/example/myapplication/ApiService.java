package com.example.myapplication;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiService {
    final String _baseUrl = "api.nytimes.com";
    static  String API_KEY = "<API_KEY>";
    View view;

//    private void ArticleDataPrepare() {
//        String url = "";
//        RequestQueue requestQueue = Volley.newRequestQueue(view.findViewById(R.id.parent));
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//        })
//    }
}
