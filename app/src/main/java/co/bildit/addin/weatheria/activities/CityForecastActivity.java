package co.bildit.addin.weatheria.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import co.bildit.addin.weatheria.R;
import co.bildit.addin.weatheria.databinding.ActivityCityForecastBinding;
import co.bildit.addin.weatheria.databinding.ActivityMainBinding;
import co.bildit.addin.weatheria.network.NetworkSingleton;
import co.bildit.addin.weatheria.network.WeatherApi;

public class CityForecastActivity extends AppCompatActivity {

    private ActivityCityForecastBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityForecastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // TODO: get city name from bundle
        String city = getIntent().getExtras().getString(MainActivity.EXTRA_CITY);
        NetworkSingleton networkSingleton = NetworkSingleton.getInstance(this);
        WeatherApi api = new WeatherApi();

        networkSingleton.addToRequestQueue(api.getForecast(city));
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