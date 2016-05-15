package smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartPool extends ASmartUnit {
    private int ph = 7;
    private int alkalinity = 10;
    private int hardness = 20;
    private int waterTemperature = 23;

    SmartPool(String id, String name){
        super(id, name);
    }

    public int getPh() {
        return ph;
    }

    public void setPh(int ph) {
        this.ph = ph;
    }

    public int getAlkalinity() {
        return alkalinity;
    }

    public void setAlkalinity(int alkalinity) {
        this.alkalinity = alkalinity;
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public int getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(int waterTemperature) {
        this.waterTemperature = waterTemperature;
    }
}
