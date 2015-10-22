package bg.startup.weather;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class WeatherTask extends AsyncTask<String, Void, WeatherResponse> {
    @Override
    protected WeatherResponse doInBackground(String... params) {

        WeatherResponse weatherResponse = null;

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
            weatherResponse = gson.fromJson(data, WeatherResponse.class);
            Log.i("Weather", "Weather response: " + weatherResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherResponse;
    }

}
