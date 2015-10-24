package bg.startup.weather.api;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

import bg.startup.weather.model.WeatherResponse;

public class SecondTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream);
            BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String data = stringBuilder.toString();

            Gson gson = new Gson();

            WeatherResponse weatherResponse = gson.fromJson(data, WeatherResponse.class);
            Log.i("Weather", "Weather response: " + weatherResponse.getName());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
