package smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartWaterSystem extends ASmartUnit {
    private Integer temperature = 30;
    private Integer pressure = 1;

    public SmartWaterSystem(String id, String name){
        super(id, name);
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
