package bg.startup.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import bg.startup.weather.R;
import bg.startup.weather.model.City;

public class RecyclerCityAdapter extends RecyclerView.Adapter<RecyclerCityAdapter.CityViewHolder> {

    private Context mContext;
    private ArrayList<City> mCities;

    public RecyclerCityAdapter(Context context, ArrayList<City> listCities) {
        mContext = context;
        mCities = listCities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.i("Weather", "RecyclerView Adapter: onCreateViewHolder;");
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_view, viewGroup, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityViewHolder cityViewHolder, int i) {
        Log.i("Weather", "RecyclerView Adapter: onBindViewHolder; position=" + i);

        City city = mCities.get(i);
        cityViewHolder.textView.setText(city.getName());
        cityViewHolder.imageView.setImageDrawable(city.getIcon());

    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public CityViewHolder(View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.icon);
            this.textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
