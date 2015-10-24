package bg.startup.weather.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import bg.startup.weather.R;
import bg.startup.weather.model.City;

public class CityAdapter extends ArrayAdapter<City> {

    private Context context;
    private ArrayList<City> cities;
    private Typeface typeface;

    public CityAdapter(Context context, ArrayList<City> listCities) {
        super(context, R.layout.item_list_view);
        this.context = context;
        cities = listCities;
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Sunday-Regular.otf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view, null, true);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.text);
            holder.name.setTypeface(typeface);
            holder.country = (TextView) convertView.findViewById(R.id.text_country);
            holder.country.setTypeface(typeface);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        City city = cities.get(position);
        holder.name.setText(city.getName());
        holder.country.setText(city.getCountry());

        return convertView;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    public static class ViewHolder {
        TextView name;
        TextView country;
    }
}
