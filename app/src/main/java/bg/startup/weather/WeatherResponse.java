package bg.startup.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("coord")
    private Coords coords;

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

    private class Coords {
        double lon;
        double lat;

        @Override
        public String toString() {
            String s = "lon=" + lon + "\n" + "lat=" + lat;
            return s;
        }
    }
}
