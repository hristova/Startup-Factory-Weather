package bg.startup.weather;

import android.graphics.drawable.Drawable;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        new LoadCitiesTask().execute();

    }

    private ArrayList<City> generateCities() {

        Drawable icon = getResources().getDrawable(R.mipmap.ic_launcher);

        ArrayList<City> listCities = new ArrayList<>();
        City city1 = new City();
        city1.setName("Sofia");
        city1.setIcon(icon);
        listCities.add(city1);

        City city2 = new City();
        city2.setName("London");
        city2.setIcon(icon);
        listCities.add(city2);

        City city3 = new City();
        city3.setName("Madrid");
        city3.setIcon(icon);
        listCities.add(city3);

        City city4 = new City();
        city4.setName("Paris");
        city4.setIcon(icon);
        listCities.add(city4);

        listCities.add(city1);
        listCities.add(city1);
        listCities.add(city2);
        listCities.add(city2);
        listCities.add(city2);
        listCities.add(city2);
        listCities.add(city2);
        listCities.add(city3);
        listCities.add(city3);
        listCities.add(city3);
        listCities.add(city3);
        listCities.add(city3);
        listCities.add(city4);
        listCities.add(city4);
        listCities.add(city4);
        listCities.add(city4);
        listCities.add(city4);
        listCities.add(city4);

        return listCities;

    }

    private void loadCities(ArrayList<City> listCities) {
        listView.setAdapter(new CityAdapter(this, listCities));
    }

    public class LoadCitiesTask extends AsyncTask<Void, Void, ArrayList<City>> {

        @Override
        protected ArrayList<City> doInBackground(Void... params) {
            return generateCities();
        }

        @Override
        protected void onPostExecute(ArrayList<City> cities) {
            super.onPostExecute(cities);
            loadCities(cities);
        }
    }

}
