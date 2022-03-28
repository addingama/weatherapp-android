package co.bildit.addin.weatheria.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.bildit.addin.weatheria.R;
import co.bildit.addin.weatheria.databinding.RviWeatherBinding;
import co.bildit.addin.weatheria.model.ForecastList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<ForecastList> localDataSet;
    private RviWeatherBinding binding;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvWeather;
        private final TextView tvTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWeather = (TextView) itemView.findViewById(R.id.weather);
            tvTemperature = (TextView) itemView.findViewById(R.id.temperature);
        }

        public TextView getWeatherTextView() {
            return tvWeather;
        }


        public TextView getTemperatureTextView() {
            return tvTemperature;
        }
    }

    public WeatherAdapter(List<ForecastList> dataSet) {
        localDataSet = dataSet;
    }

    public void setLocalDataSet(List<ForecastList> dataSet) {
        Log.e("Addin", dataSet.size() + "");
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
        holder.getWeatherTextView().setText(localDataSet.get(position).getWeather().get(0).getMain());
        holder.getTemperatureTextView().setText("Temp: " + localDataSet.get(position).getMain().getTemp());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
