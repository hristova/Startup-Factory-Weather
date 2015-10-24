package bg.startup.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

import bg.startup.weather.R;
import bg.startup.weather.adapter.CityAdapter;
import bg.startup.weather.api.GetCitiesTask;
import bg.startup.weather.model.City;

public class MainActivity extends AppCompatActivity {

    private static final String CITIES_URL = "http://vmedia.bg:8888";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        new GetCitiesTask() {
            @Override
            protected void onPostExecute(ArrayList<City> cities) {
                super.onPostExecute(cities);
                loadCities(cities);
            }
        }.execute(CITIES_URL);

    }


    private void loadCities(final ArrayList<City> listCities) {
        listView.setAdapter(new CityAdapter(this, listCities));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city = listCities.get(position);

                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("name", city.getName());
                startActivity(intent);

            }
        });
    }

}
