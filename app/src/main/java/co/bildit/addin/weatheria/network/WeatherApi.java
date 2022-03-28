package co.bildit.addin.weatheria.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import co.bildit.addin.weatheria.model.WeatherForecast;

public class WeatherApi {
    private static String apiKey = "65d00499677e59496ca2f318eb68c049";
    private static String baseUrl = "https://api.openweathermap.org/data/2.5";

    public GsonRequest<WeatherForecast> getForecast(String city, Response.Listener successListener, Response.ErrorListener errorListener) {
        try {
            Log.e("Addin", "Called forecast");
            String url = baseUrl + "/forecast?appid=" + apiKey + "&q=" + URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
            GsonRequest gsonRequest = new GsonRequest(url, WeatherForecast.class, null, successListener, errorListener);

            return gsonRequest;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
