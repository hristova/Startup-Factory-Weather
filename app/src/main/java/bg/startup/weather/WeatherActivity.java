package bg.startup.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WeatherActivity extends AppCompatActivity {

    private static final String API = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String KEY = "&appid=bd82977b86bf27fb59a04b61b657fb6f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather);

        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            String name = intent.getStringExtra("name");
            Log.i("Weather", "Intent extra = " + name);

            try {
                String encodedName = URLEncoder.encode(name, "utf-8");
                String url = API + encodedName + KEY;
                new WeatherTask().execute(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }
    }
}
