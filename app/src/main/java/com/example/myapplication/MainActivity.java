package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArticleAdapter articleAdapter;
    private List<Article> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ArticleDataPrepare();
    }

    private void ArticleDataPrepare() {
        final String API_KEY = "lWCCEarZ3KboGE802JgSgYp0RKeBsfE8";
        final String _baseUrl = "https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=" + API_KEY;

        //URL url = new URL(_baseUrl);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, _baseUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
                recyclerView = findViewById(R.id.recycler_view);
                imageView = findViewById(R.id.imageView);
                articleAdapter = new ArticleAdapter(articleList);

                RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(articleAdapter);

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("results");
                    System.out.println("jsonArray Size: " + jsonArray.length());
                    Article article = new Article(jsonArray.getJSONObject(0).get("title").toString(),
                            jsonArray.getJSONObject(0).get("byline").toString(),
                            jsonArray.getJSONObject(0).get("url").toString(),
                            jsonArray.getJSONObject(0).getJSONArray("media").getJSONObject(0).getJSONArray("media-metadata").getJSONObject(2).get("url").toString(),
                            jsonArray.getJSONObject(0).get("published_date").toString(),
                            jsonArray.getJSONObject(0).get("abstract").toString(),
                            imageView);
                        for (int i = 0, size = jsonArray.length(); i < size; i++) {
                            JSONObject jsonObjectInArray = jsonArray.getJSONObject(i);
                            article = new Article(jsonObjectInArray.get("title").toString(),
                                    jsonObjectInArray.get("byline").toString(),
                                    jsonObjectInArray.get("url").toString(),
                                    jsonObjectInArray.getJSONArray("media").getJSONObject(0).getJSONArray("media-metadata").getJSONObject(2).get("url").toString(),
                                    jsonObjectInArray.get("published_date").toString(),
                                    jsonObjectInArray.get("abstract").toString(),
                                    imageView);
                            articleList.add(article);
                        }
                    articleList.add(article);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                System.out.println("Error: " + error.getCause());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}