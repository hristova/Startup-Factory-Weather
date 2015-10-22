package bg.startup.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {

    double visibility;
    @SerializedName("coord")
    private Coords coords;
    @SerializedName("weather")
    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    @SerializedName("main")
    private WeatherInfo info;
    private Wind wind;
    private Clouds clouds;
    private int id;
    private String name;
    @SerializedName("cod")
    private int code;

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public Weather getWeather() {
        return (weatherArrayList.size() >= 1) ? weatherArrayList.get(0) : new Weather();
    }

    public WeatherInfo getInfo() {
        return info;
    }

    public void setInfo(WeatherInfo info) {
        this.info = info;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public ArrayList<Weather> getWeatherArrayList() {
        return weatherArrayList;
    }

    public void setWeatherArrayList(ArrayList<Weather> weatherArrayList) {
        this.weatherArrayList = weatherArrayList;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return getName() + "\n" +
                getCoords().toString() +
                getWeather().toString() +
                getInfo().toString() +
                getWind().toString();
    }


    private class Coords {
        double lon;
        double lat;

        @Override
        public String toString() {
            return "Coords:lon=" + lon + "; " + "lat=" + lat + "\n";
        }
    }

    public class Weather {
        int id;
        @SerializedName("main")
        String weather;
        String description;
        String icon;

        public String getWeather() {
            return weather;
        }

        public String getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return "Weather:weather=" + weather + "; " + "description=" + description + "\n";
        }
    }

    public class WeatherInfo {

        @SerializedName("temp")
        double temperature;
        double pressure;
        double humidity;
        @SerializedName("temp_min")
        double tempMin;
        @SerializedName("temp_max")
        double tempMax;

        public double getTemperature() {
            return temperature;
        }

        public double getTempMin() {
            return tempMin;
        }

        public double getTempMax() {
            return tempMax;
        }

        @Override
        public String toString() {
            return "WeatherInfo:temperature=" + temperature + "; " +
                    "temp min=" + tempMin + "; " +
                    "temp max=" + tempMax + "; " +
                    "pressure=" + pressure + "; " +
                    "humidity=" + humidity + "\n";
        }
    }

    private class Wind {
        double speed;
        double deg;

        @Override
        public String toString() {
            return "Wind:speed=" + speed + "; " + "deg=" + deg + "\n";
        }
    }

    private class Clouds {
        double all;
    }

}
