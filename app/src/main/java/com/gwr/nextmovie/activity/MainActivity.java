package com.gwr.nextmovie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gwr.nextmovie.R;
import com.gwr.nextmovie.adapter.GridAdapter;
import com.gwr.nextmovie.domain.Movie;
import com.gwr.nextmovie.network.ConsURL;
import com.gwr.nextmovie.network.NetworkConnection;
import com.gwr.nextmovie.network.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Transaction {

    private GridView gridView;
    List<Movie> list;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        gridAdapter = new GridAdapter(this,list);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Intent it = new Intent(MainActivity.this,MovieDetailActivity.class);
                it.putExtra("Filme",movie);
                startActivity(it);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        getMoviesNetwork();
    }

    public void getMoviesNetwork(){

        NetworkConnection.getInstance(MainActivity.this).execute(this);
    }

    @Override
    public String doBefore() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String searchBy = preferences.getString(getString(R.string.pref_search_key),getString(R.string.pref_search_default));

        if (searchBy.equals(getString(R.string.pref_search_popular))){
            return ConsURL.byPopular;
        }else{
            return ConsURL.byRated;
        }

    }

    @Override
    public void doAfter(JSONObject jsonObject) {
        Log.i("JSON",jsonObject.toString());
        Movie movie;
        List<Movie> list = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONArray results = jsonObject.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                 movie =  gson.fromJson(results.get(i).toString(),Movie.class);
                 list.add(movie);
            }
            gridAdapter.updateResults(list);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
