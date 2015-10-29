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
    private static final String KEY = "&appid=299a49f6c5c51d349aee863f7a27642f";

    private Typeface typeface;
    private WeatherResponse weather;

    private boolean isCelsius = true;

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
                            loadWeather();
                        }

                    }
                }.execute(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    private void loadWeather() {

        setCityName();

        setIcon();

        setTemperature();

        setupTempButtons();

        setTempMinMax();

        setDescription();

    }

    private void setCityName() {
        TextView textViewCity = (TextView) findViewById(R.id.text_city_name);
        textViewCity.setTypeface(typeface);
        textViewCity.setText(weather.getName());
    }

    private void setIcon() {
        String iconName = "i" + weather.getWeather().getIcon();
        int iconId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.image_weather);
        imageView.setImageResource(iconId);
    }

    private void setTemperature() {
        TextView textViewTemp = (TextView) findViewById(R.id.text_temperature);
        double temperature = weather.getInfo().getTemperature();
        String formattedTemperature = isCelsius ? (getTemperatureInCelsius(temperature) + getString(R.string.degree))
                : (getTemperatureInFahrenheit(temperature) + getString(R.string.degree));
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
                isCelsius = true;
                buttonCelsius.setTypeface(typeface, Typeface.BOLD);
                buttonFahrenheit.setTypeface(typeface, Typeface.NORMAL);
                setTemperature();
                setTempMinMax();
            }
        });

        buttonFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCelsius = false;
                buttonFahrenheit.setTypeface(typeface, Typeface.BOLD);
                buttonCelsius.setTypeface(typeface, Typeface.NORMAL);
                setTemperature();
                setTempMinMax();
            }
        });
    }

    private void setTempMinMax() {
        TextView textMinMax = (TextView) findViewById(R.id.text_temp_min_max);
        textMinMax.setTypeface(typeface);

        WeatherResponse.WeatherInfo weatherInfo = weather.getInfo();

        int formattedTempMin = isCelsius ? getTemperatureInCelsius(weatherInfo.getTempMin()) :
                getTemperatureInFahrenheit(weatherInfo.getTempMin());
        int formattedTempMax = isCelsius ? getTemperatureInCelsius(weatherInfo.getTempMax()) :
                getTemperatureInFahrenheit(weatherInfo.getTempMax());

        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append(formattedTempMin).append(getString(R.string.degree))
                .append(" - ").append(formattedTempMax).append(getString(R.string.degree));
        textMinMax.setText(tempBuilder.toString());
    }

    private void setDescription() {
        TextView textDescription = (TextView) findViewById(R.id.text_weather_description);
        textDescription.setTypeface(typeface);
        String weatherDescription = weather.getWeather().getWeather();
        textDescription.setText(weatherDescription);
    }

    private int getTemperatureInCelsius(double temperature) {
        return (int) (temperature - 273.15d);
    }

    private int getTemperatureInFahrenheit(double temperature) {
        return (int) ((temperature - 273.15d) * 1.8d + 32.0d);
    }

}
