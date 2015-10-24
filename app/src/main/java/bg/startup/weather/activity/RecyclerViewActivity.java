package bg.startup.weather.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;

import java.util.ArrayList;

import bg.startup.weather.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new RecyclerCityAdapter(this, initializeCities()));

    }

//    private ArrayList<City> initializeCities() {
//        String[] cities = {"London", "Sofia", "New York", "Athens", "Belgrade", "Bucharest", "Skopje",
//                "Berlin", "Helsinki", "Rome", "Madrid", "Lisbon", "Riga", "Kiev"};
//        Drawable icon = getResources().getDrawable(R.mipmap.ic_launcher);
//        ArrayList<City> listCities = new ArrayList<>();
//        for (String city : cities) {
//            City newCity = new City();
//            newCity.setName(city);
//            newCity.setIcon(icon);
//            listCities.add(newCity);
//        }
//
//        return listCities;
//    }
}
