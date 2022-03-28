package co.bildit.addin.weatheria.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import co.bildit.addin.weatheria.R;
import co.bildit.addin.weatheria.activities.ForecastDetailActivity;
import co.bildit.addin.weatheria.activities.MainActivity;
import co.bildit.addin.weatheria.databinding.RviWeatherBinding;
import co.bildit.addin.weatheria.model.ForecastList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context ctx;
    private List<ForecastList> localDataSet;
    private String city;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvWeather;
        private final TextView tvTemperature;
        private final ConstraintLayout clRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWeather = (TextView) itemView.findViewById(R.id.weather);
            tvTemperature = (TextView) itemView.findViewById(R.id.temperature);
            clRoot = (ConstraintLayout) itemView.findViewById(R.id.weather_row);
        }

        public TextView getWeatherTextView() {
            return tvWeather;
        }


        public TextView getTemperatureTextView() {
            return tvTemperature;
        }

        public ConstraintLayout getWeatherRow() {
            return clRoot;
        }
    }

    public WeatherAdapter(Context context, List<ForecastList> dataSet, String city) {
        ctx = context;
        localDataSet = dataSet;
        this.city = city;
    }

    public void setLocalDataSet(List<ForecastList> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rvi_weather, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForecastList data = localDataSet.get(position);
        holder.getWeatherTextView().setText(data.getWeather().get(0).getMain());
        holder.getTemperatureTextView().setText("Temp: " + data.getMain().getTemp() + " C");
        holder.getWeatherRow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Intent intent = new Intent(ctx, ForecastDetailActivity.class);

                intent.putExtra(MainActivity.EXTRA_CITY, city);
                intent.putExtra(MainActivity.EXTRA_WEATHER, gson.toJson(data));
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
