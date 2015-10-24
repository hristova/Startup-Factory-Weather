package bg.startup.weather.api;

import android.os.AsyncTask;

import org.json.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import bg.startup.weather.model.City;

public class GetCitiesTask extends AsyncTask<String, Void, ArrayList<City>> {
    @Override
    protected ArrayList<City> doInBackground(String... params) {

        ArrayList<City> list = new ArrayList<>();

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
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String country = jsonObject.getString("country");
                String capital = jsonObject.getString("capital");
                City city = new City();
                city.setName(capital);
                city.setCountry(country);
                list.add(city);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
