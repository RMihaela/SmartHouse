package smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartWeatherSystem extends ASmartUnit {
    private int temperature = 30;
    private int windSpeed = 20;
    private int humidity = 20;
    private int precipitations = 0;

    public SmartWeatherSystem(String id, String name){
        super(id, name);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPrecipitations() {
        return precipitations;
    }

    public void setPrecipitations(int precipitations) {
        this.precipitations = precipitations;
    }
}
