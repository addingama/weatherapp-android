package co.bildit.addin.weatheria.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import co.bildit.addin.weatheria.R;
import co.bildit.addin.weatheria.databinding.ActivityForecastDetailBinding;
import co.bildit.addin.weatheria.model.ForecastList;

public class ForecastDetailActivity extends AppCompatActivity {

    private ActivityForecastDetailBinding binding;
    private ForecastList forecastData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForecastDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Gson gson = new Gson();
        String city = getIntent().getExtras().getString(MainActivity.EXTRA_CITY);
        forecastData =  gson.fromJson(getIntent().getExtras().getString(MainActivity.EXTRA_WEATHER), ForecastList.class);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(city);
        }

        // Update UI
        binding.temperature.setText(forecastData.getMain().getTemp().toString() + " C");
        binding.temperatureLike.setText("Feels like: " + forecastData.getMain().getFeelsLike().toString() + " C");
        binding.weather.setText(forecastData.getWeather().get(0).getMain());
        binding.description.setText(forecastData.getWeather().get(0).getDescription());

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