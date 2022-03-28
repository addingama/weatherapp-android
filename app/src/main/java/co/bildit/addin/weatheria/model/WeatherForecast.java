package co.bildit.addin.weatheria.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherForecast {

@SerializedName("cod")
@Expose
private String cod;
@SerializedName("message")
@Expose
private Integer message;
@SerializedName("cnt")
@Expose
private Integer cnt;
@SerializedName("list")
@Expose
private List<ForecastList> forecastList = null;
@SerializedName("city")
@Expose
private City city;

public String getCod() {
return cod;
}

public void setCod(String cod) {
this.cod = cod;
}

public Integer getMessage() {
return message;
}

public void setMessage(Integer message) {
this.message = message;
}

public Integer getCnt() {
return cnt;
}

public void setCnt(Integer cnt) {
this.cnt = cnt;
}

public List<ForecastList> getList() {
return forecastList;
}

public void setList(List<ForecastList> forecastList) {
this.forecastList = forecastList;
}

public City getCity() {
return city;
}

public void setCity(City city) {
this.city = city;
}

}