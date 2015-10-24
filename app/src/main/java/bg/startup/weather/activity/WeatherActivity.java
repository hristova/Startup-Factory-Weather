package bg.startup.weather.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import bg.startup.weather.R;
import bg.startup.weather.api.WeatherTask;
import bg.startup.weather.model.WeatherResponse;


public class WeatherActivity extends AppCompatActivity {

    private static final String API = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String KEY = "&appid=bd82977b86bf27fb59a04b61b657fb6f";

    private Typeface typeface;
    private WeatherResponse weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/Sunday-Regular.otf");

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
                            weather = weatherResponse;
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

        setIcon(weatherResponse.getWeather().getIcon());

        double temperature = weatherResponse.getInfo().getTemperature();
        setTemperature(getTemperatureInCelsius(temperature));

        setupTempButtons();
        //temp min max
        // weather description

    }

    private void setCityName(String cityName) {
        TextView textViewCity = (TextView) findViewById(R.id.text_city_name);
        textViewCity.setTypeface(typeface);
        textViewCity.setText(cityName);
    }

    private void setIcon(String iconName) {
        iconName = "i" + iconName;
        int iconId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.image_weather);
        imageView.setImageResource(iconId);
    }

    private void setTemperature(double temperature) {
        TextView textViewTemp = (TextView) findViewById(R.id.text_temperature);
        String formattedTemperature = String.valueOf(temperature) + getString(R.string.degree);
        textViewTemp.setTypeface(typeface);
        textViewTemp.setText(formattedTemperature);
    }

    private void setupTempButtons() {
        final Button buttonCelsius = (Button) findViewById(R.id.button_celsius);
        buttonCelsius.setTypeface(typeface);
        buttonCelsius.setText(R.string.degree_celsius);
        final Button buttonFahrenheit = (Button) findViewById(R.id.button_fahrenheit);
        buttonFahrenheit.setTypeface(typeface);
        buttonFahrenheit.setText(R.string.degree_fahrenheit);

        buttonCelsius.setTypeface(typeface, Typeface.BOLD);

        buttonCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCelsius.setTypeface(typeface, Typeface.BOLD);
                buttonFahrenheit.setTypeface(typeface, Typeface.NORMAL);
                double temp = weather.getInfo().getTemperature();
                setTemperature(getTemperatureInCelsius(temp));

            }
        });

        buttonFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFahrenheit.setTypeface(typeface, Typeface.BOLD);
                buttonCelsius.setTypeface(typeface, Typeface.NORMAL);
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
