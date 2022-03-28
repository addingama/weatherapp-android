package co.bildit.addin.weatheria.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import co.bildit.addin.weatheria.adapters.WeatherAdapter;
import co.bildit.addin.weatheria.databinding.ActivityCityForecastBinding;
import co.bildit.addin.weatheria.model.ForecastList;
import co.bildit.addin.weatheria.model.WeatherForecast;
import co.bildit.addin.weatheria.network.NetworkSingleton;
import co.bildit.addin.weatheria.network.WeatherApi;

public class CityForecastActivity extends AppCompatActivity {

    private ActivityCityForecastBinding binding;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityForecastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String city = getIntent().getExtras().getString(MainActivity.EXTRA_CITY);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(city);
        }

        List<ForecastList> tempData = new ArrayList<ForecastList>();
        adapter = new WeatherAdapter(this, tempData, city);

        binding.rvForecast.setAdapter(adapter);
        binding.rvForecast.setLayoutManager(new LinearLayoutManager(this));


        NetworkSingleton networkSingleton = NetworkSingleton.getInstance(this);
        WeatherApi api = new WeatherApi();

        networkSingleton.addToRequestQueue(api.getForecast(city, new Response.Listener<WeatherForecast>() {
            @Override
            public void onResponse(WeatherForecast response) {
                adapter.setLocalDataSet(response.getList());
                adapter.notifyDataSetChanged();

                if (response.getList().size() == 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CityForecastActivity.this, "Weather forecast not found", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CityForecastActivity.this, "Invalid city name", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}