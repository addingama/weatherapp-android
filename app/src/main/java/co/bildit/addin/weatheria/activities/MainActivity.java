package co.bildit.addin.weatheria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import co.bildit.addin.weatheria.R;
import co.bildit.addin.weatheria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "city";
    public static final String EXTRA_WEATHER = "weather";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnLookup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String city = binding.etCityName.getText().toString();
                if (city.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a City", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Pass down the name to the next screen
                    Intent intent = new Intent(MainActivity.this, CityForecastActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRA_CITY, city);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}