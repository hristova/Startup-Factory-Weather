package bg.startup.weather;

import android.content.Context;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {

    private Context context;
    private ArrayList<City> cities;

    public CityAdapter(Context context, ArrayList<City> listCities) {
        super(context, R.layout.item_list_view);
        this.context = context;
        cities = listCities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view, null, true);
            holder = new ViewHolder();
            Log.i("Weather", "Adapter: Inflate, position=" + position);

            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.name = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        City city = cities.get(position);
        holder.icon.setImageDrawable(city.getIcon());
        holder.name.setText(city.getName());

        return convertView;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    public static class ViewHolder {
        ImageView icon;
        TextView name;
    }
}
