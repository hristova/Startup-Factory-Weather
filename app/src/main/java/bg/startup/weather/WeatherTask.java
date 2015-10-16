package bg.startup.weather;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class WeatherTask extends AsyncTask<String, Void, Void> {
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
            Log.i("Weather", "Coords = " + weatherResponse.getCoords());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
