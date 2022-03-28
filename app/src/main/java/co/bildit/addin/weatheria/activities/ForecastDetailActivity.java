package co.bildit.addin.weatheria.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import co.bildit.addin.weatheria.R;
import co.bildit.addin.weatheria.databinding.ActivityForecastDetailBinding;

public class ForecastDetailActivity extends AppCompatActivity {

    private ActivityForecastDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForecastDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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