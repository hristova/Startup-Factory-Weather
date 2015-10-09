package bg.startup.weather;

import android.graphics.drawable.Drawable;

public class City {

    private Drawable icon;
    private String name;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
