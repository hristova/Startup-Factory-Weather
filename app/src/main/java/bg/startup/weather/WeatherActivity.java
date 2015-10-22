package bg.startup.weather;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WeatherActivity extends AppCompatActivity {

    private static final String API = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String KEY = "&appid=bd82977b86bf27fb59a04b61b657fb6f";

    private WeatherResponse weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            String name = intent.getStringExtra("name");

            try {
                String encodedName = URLEncoder.encode(name, "utf-8");
                String url = API + encodedName + KEY;
                new WeatherTask() {
                    @Override
                    protected void onPostExecute(WeatherResponse weatherResponse) {
                        super.onPostExecute(weatherResponse);
                        if (weatherResponse != null) {
                            weather  = weatherResponse;
                            loadWeather(weatherResponse);
                        }
                    }
                }.execute(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    private void loadWeather(WeatherResponse weatherResponse) {
        String cityName = weatherResponse.getName();
        setCityName(cityName);

        //Icon

        double temperature = weatherResponse.getInfo().getTemperature();
        setTemperature(getTemperatureInCelsius(temperature));

        setupTempButtons();
        //temp min max
        // weather description

    }

    private void setCityName(String cityName) {
        TextView textViewCity = (TextView) findViewById(R.id.text_city_name);
        textViewCity.setText(cityName);
    }

    private void setTemperature(double temperature) {
        TextView textViewTemp = (TextView) findViewById(R.id.text_temperature);
        String formattedTemperature = String.valueOf(temperature) + getString(R.string.degree);
        textViewTemp.setText(formattedTemperature);
    }

    private void setupTempButtons() {
        final Button buttonCelsius = (Button) findViewById(R.id.button_celsius);
        final Button buttonFahrenheit = (Button) findViewById(R.id.button_fahrenheit);

        buttonCelsius.setTypeface(null, Typeface.BOLD_ITALIC);

        buttonCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCelsius.setTypeface(null, Typeface.BOLD_ITALIC);
                buttonFahrenheit.setTypeface(null, Typeface.NORMAL);
                double temp = weather.getInfo().getTemperature();
                setTemperature(getTemperatureInCelsius(temp));

            }
        });

        buttonFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFahrenheit.setTypeface(null, Typeface.BOLD_ITALIC);
                buttonCelsius.setTypeface(null, Typeface.NORMAL);
                double temp = weather.getInfo().getTemperature();
                setTemperature(getTemperatureInFahrenheit(temp));
            }
        });
    }

    private int getTemperatureInCelsius(double temperature) {
        return (int) (temperature - 273.15d);
    }

    private int getTemperatureInFahrenheit(double temperature) {
        return (int) ((temperature - 273.15d) * 1.8d + 32.0d);
    }

}
