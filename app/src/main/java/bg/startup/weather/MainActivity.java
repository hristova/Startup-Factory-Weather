package bg.startup.weather;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<City> listCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        generateCities();
        listView.setAdapter(new CityAdapter(this, listCities));
    }

    private void generateCities() {

        Drawable icon = getResources().getDrawable(R.mipmap.ic_launcher);

        listCities = new ArrayList<>();
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

    }

}
